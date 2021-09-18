import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostDetails } from '../interface/post-details';
import { SocketClientService } from './socket-client.service';

export interface Message {
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  sendPostEndpoint: string = "/app/vw/createPost";
  recievePostEndpoint: string = "/topic/messages";
  maxBufferSize = environment.max_post_count;

  constructor(
    private socketClient: SocketClientService,
    private httpClient: HttpClient
  ) {}

  public sendPost(payLoad?:any){
    let postDetails ={
      "title" : "testTitle",
      "description" : "testDescription"
    };
    console.log("Sending message - " + JSON.stringify(postDetails));
    this.socketClient.send(this.sendPostEndpoint,postDetails)
  }

  public recievePosts(){
    console.log("Recieveing message - ");
    this.socketClient.onMessage(this.recievePostEndpoint).subscribe(
      (res) => {
        // console.log(JSON.stringify(res));
      }
    );
  }

  // public recieveBroadcastedPosts(): PostDetails[]{
  //   return this.socketClient.newPosts;
  // }

  loadPostOnDemand(): Observable<PostDetails[]> {
    let apiUrl = environment.posts_on_demand_url;
    return this.httpClient.get(apiUrl) as Observable<PostDetails[]>;
  }
}
