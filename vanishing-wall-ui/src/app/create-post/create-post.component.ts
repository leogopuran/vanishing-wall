import { Component, OnInit, EventEmitter } from '@angular/core';
import { PostDetails } from '../interface/post-details';
import { CreateNewPostService } from '../service/create-new-post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  postDetails!: PostDetails;
  newlyCreatedPost!: PostDetails;
  newPostCreatedEvent: EventEmitter<PostDetails> = new EventEmitter();


  constructor(
    private createPostService: CreateNewPostService
  ) { }

  ngOnInit(): void {

  }

  async onFormSubmit(newPostData : any){
    this.postDetails = newPostData;
    this.newlyCreatedPost = await this.createPostService.callCreateNewPostApi(newPostData as PostDetails);
    this.newPostCreatedEvent.emit(this.newlyCreatedPost);
  }

}
