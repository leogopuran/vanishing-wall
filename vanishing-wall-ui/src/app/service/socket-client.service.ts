import { Injectable, OnDestroy, EventEmitter} from '@angular/core';
import { StompSubscription } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter, first, switchMap } from 'rxjs/operators';
import * as SockJS from 'sockjs-client';
import { environment } from 'src/environments/environment';
import { Client, Message, over } from 'stompjs';
import { PostDetails } from '../interface/post-details';

export enum SocketClientState {
  ATTEMPTING, CONNECTED
}

@Injectable({
  providedIn: 'root'
})

export class SocketClientService  implements OnDestroy{

  private client!: Client;
  private state!: BehaviorSubject<SocketClientState>;
  public postReserve: PostDetails[] = [];
  private max_reserve_size: number = environment.max_reserve_size;

  private websocket_api_url = environment.socket_api_url;
  private postsDefaultTopic = environment.default_topic;

  constructor() {
    this.client = over(new SockJS(this.websocket_api_url));
    this.client.debug = () => {};
    this.state = new BehaviorSubject<SocketClientState>(SocketClientState.ATTEMPTING);
    this.client.connect({}, () => {
      this.state.next(SocketClientState.CONNECTED);
      this.client.subscribe(this.postsDefaultTopic, responseData => {
        console.log("Total Posts in reserve - " + this.postReserve.length);
        let broadcastedPosts: PostDetails[] = JSON.parse(responseData.body);
        if(this.postReserve.length < this.max_reserve_size){
          broadcastedPosts.forEach((element: PostDetails) => {
            if(this.postReserve.length < this.max_reserve_size){
              this.postReserve.push(element);
            }
          });
        }
      });
    });
  }

  static jsonHandler(message: Message): any {
    return JSON.parse(message.body);
  }
  
  static textHandler(message: Message): string {
    return message.body;
  }
  
  ngOnDestroy(): void {
    this.connect().pipe(first()).subscribe(client => client.disconnect(() => null));
  }

  onMessage(topic: string, handler = SocketClientService.jsonHandler): Observable<any> {
    return this.connect().pipe(first(), switchMap(client => {
      return new Observable<any>(observer => {
        const subscription: StompSubscription = client.subscribe(topic, message => {
          observer.next(handler(message));
        });
        return () => client.unsubscribe(subscription .id);
      });
    }));
  }

  onPlainMessage(topic: string): Observable<string> {
    return this.onMessage(topic, SocketClientService.textHandler);
  }

  send(topic: string, payload: any): void {
    this.connect()
      .pipe(first())
      .subscribe(client => client.send(topic, {}, JSON.stringify(payload)));
  }

  private connect(): Observable<Client> {
    return new Observable<Client>(observer => {
      this.state.pipe(filter((state: SocketClientState) => state === SocketClientState.CONNECTED)).subscribe(() => {
        observer.next(this.client);
      });
    });
  }
}
