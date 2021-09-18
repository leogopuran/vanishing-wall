package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.IPostCreateService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class PostCreateServiceImpl implements IPostCreateService {
    @Override
    public PostDetail createNewPost(PostDetail newPostData) {
        String uniquePostId = UUID.randomUUID().toString();
        newPostData.setId(uniquePostId);
        if (newPostData.getContentType().equals("image")){
            newPostData.setImageUrl(newPostData.getMediaUrl());
        }
        newPostData.setState("show");
        return newPostData;
    }
}
