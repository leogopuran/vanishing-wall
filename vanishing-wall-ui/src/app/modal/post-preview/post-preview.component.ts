import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PostDetails } from 'src/app/interface/post-details';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-post-preview',
  templateUrl: './post-preview.component.html',
  styleUrls: ['./post-preview.component.css']
})
export class PostPreviewComponent implements OnInit {

  defaultImage = environment.defaultImage;
  videoEncodeHtml!: any;

  @Input() postDetail!: PostDetails;

  constructor(
    public activeModal: NgbActiveModal,
    private sanitized: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.videoEncodeHtml = this.sanitized.bypassSecurityTrustHtml(this.postDetail.codeToEmbed)
  }

}
