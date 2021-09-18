import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PostDetails } from '../interface/post-details';

@Injectable({
  providedIn: 'root'
})
export class CreateNewPostService {

  newlyCreatedPost: any;

  constructor(
      private httpClient: HttpClient
  ) { }

  async callCreateNewPostApi(postDetails: PostDetails) :Promise<PostDetails>{
    let createPostApiUrl = environment.post_create_url;
    let header: HttpHeaders = new HttpHeaders;
    header.append("Content-Type", "application/json");
    await this.httpClient.post(createPostApiUrl, postDetails, {headers: header, observe: 'response'}).toPromise()
    .catch((error) => {
      retry(1);
      console.log("Api error -> " + error);
      this.newlyCreatedPost = null;
    })
    .then(
      (res) => {
        if(res && res.status && res.status === 200 && res.body){
          let newPost: PostDetails = res.body as PostDetails;
          this.newlyCreatedPost = newPost as PostDetails;
        }
        else{
          console.log("Api call failed");
          this.newlyCreatedPost = null;
        }
      }
    );

    console.log("Api call finished");
    return this.newlyCreatedPost;
  }
}
