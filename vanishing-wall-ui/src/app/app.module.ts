import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { LazyLoadImageModule, LAZYLOAD_IMAGE_HOOKS, ScrollHooks } from 'ng-lazyload-image';
import { CountdownModule } from 'ngx-countdown';
import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PostPreviewComponent } from './modal/post-preview/post-preview.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreatePostComponent } from './create-post/create-post.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    PostPreviewComponent,
    CreatePostComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    LazyLoadImageModule,
    CountdownModule,
    HttpClientModule,
    NgbModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ],
  providers: [
    { provide: LAZYLOAD_IMAGE_HOOKS, useClass: ScrollHooks }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
