package com.casterlabs.vanishingwallapi.service;

import com.casterlabs.vanishingwallapi.modal.PostDetail;

public interface IPostCreateService {
    PostDetail createNewPost(PostDetail newPostData);
}
