package com.casterlabs.vanishingwallapi.service;

import com.casterlabs.vanishingwallapi.modal.PostDetail;

public interface IPixabayDataFeedService {
    PostDetail getPixabayVideoFeeds();
    PostDetail getPixabayImageFeeds();
}
