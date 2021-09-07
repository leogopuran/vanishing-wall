import { trigger, state, style, transition, animate } from '@angular/animations';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostDetails } from '../interface/post-details';
import { HomeService } from '../service/home.service';
import { SocketClientService } from '../service/socket-client.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('popOverState', [
      state('show', style({
        opacity: 1
      })),
      state('hide',   style({
        opacity: 0
      })),
      transition('show => hide', animate('500ms  ease-out')),
      transition('hide => show', animate('500ms  ease-in'))
    ])
  ],
  providers: [HomeService]
})
export class HomeComponent implements OnInit {

  displayingQueue!: PostDetails[];
  freeSlots: number[] = [];
  showDescription:boolean = false;
  defaultImage: string = "../assets/images/placeholder-img.png";
  isCardUpdate = true;
  MAX_POST_LIMIT = environment.max_post_count;
  TRANSITION_SPEED = 300;

  public stompClient: any;
  public msg = [];
  newPostData!: PostDetails[];

  constructor(
    private homeService:HomeService,
    private socketClient: SocketClientService
  ) {}

  ngOnInit(): void {

    // Load posts in the begining
    this.homeService.loadPostOnDemand().subscribe(
      data => {
        if(data){
          // this.displayingQueue = data.slice(0, this.MAX_POST_LIMIT);
          this.displayingQueue = data;
          this.displayingQueue = this.displayingQueue.map((post) => {
            post.state = 'show';
            return post;
          });
        }
      }
    );
  }

  async updateUnfilledSlots() {
    if(this.socketClient.postReserve && this.socketClient.postReserve.length > 0){
      while(this.freeSlots && this.freeSlots.length > 0 && this.socketClient.postReserve.length > 0){
        let slotToBeFilled = this.freeSlots.shift()!;
        this.displayingQueue[slotToBeFilled] = this.socketClient.postReserve.pop()!;
        
        await delay(this.TRANSITION_SPEED);
        this.displayingQueue[slotToBeFilled].state="show";
      }
    }
  }

  async updatePosts(event:any, index: number) {
    if(event.action === 'notify'){
      await delay(2000);
      console.log("replacing post..!");
      this.displayingQueue[index].state = "hide";
      await delay(this.TRANSITION_SPEED);
      this.freeSlots.push(index);
      await this.updateUnfilledSlots();
    }
  }

  sendPost(){
    this.homeService.sendPost();
  }

}

async function delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

