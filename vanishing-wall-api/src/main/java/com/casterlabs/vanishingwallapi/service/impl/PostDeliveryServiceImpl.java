package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.modal.FootballPostDetail;
import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.IFootballDataFeedService;
import com.casterlabs.vanishingwallapi.service.IPixabayDataFeedService;
import com.casterlabs.vanishingwallapi.service.IPostDeliveryService;
import com.casterlabs.vanishingwallapi.service.IRandomPictureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostDeliveryServiceImpl implements IPostDeliveryService {

    @Autowired
    private IFootballDataFeedService footballDataFeedService;
    @Autowired
    private IRandomPictureDataService randomPictureDataService;
    @Autowired
    private IPixabayDataFeedService pixabayDataFeedService;

    private static final int BROADCAST_LIMIT = 20;
    private static final List<String> TOPIC_LIST = new ArrayList<>();

    @Override
    public ArrayList<PostDetail> deliverNewPost() {
        return generateMixedPosts(BROADCAST_LIMIT);
    }

    private ArrayList<PostDetail> generateMixedPosts(int broadcastLimit) {

        TOPIC_LIST.add("RANDOM_PICS");
        TOPIC_LIST.add("FOOTBALL");
        TOPIC_LIST.add("PIXABAY_IMAGES");
        TOPIC_LIST.add("PIXABAY_VIDEOS");

        ArrayList<PostDetail> postDetails = new ArrayList<>();
        int count =0;
        while(count < broadcastLimit){
            String uniquePostId = UUID.randomUUID().toString();

            //Random topic selection
            int randomTopicIndex = (int)(Math.random()*(TOPIC_LIST.size()));
            String randomTopic = TOPIC_LIST.get(randomTopicIndex);

            if(randomTopic.equals("FOOTBALL")){
                FootballPostDetail footballFeedData = footballDataFeedService.getRandomFootballData();
                footballFeedData.setId(uniquePostId);
                footballFeedData.setState("show");
                postDetails.add(footballFeedData);
            }
            else if(randomTopic.equals("RANDOM_PICS")){
                PostDetail postDetail = randomPictureDataService.getRandomPicturePost();
                postDetail.setContentType("image");
                postDetail.setState("show");
                postDetails.add(postDetail);
            }
            else if(randomTopic.equals("PIXABAY_IMAGES")){
                PostDetail postDetail = pixabayDataFeedService.getPixabayImageFeeds();
                postDetail.setId(uniquePostId);
                postDetail.setState("show");
                postDetails.add(postDetail);
            }
            else if(randomTopic.equals("PIXABAY_VIDEOS")){
                PostDetail postDetail = pixabayDataFeedService.getPixabayVideoFeeds();
                postDetail.setId(uniquePostId);
                postDetail.setState("show");
                postDetails.add(postDetail);
            }
            count ++;
        }

        return postDetails;
    }
}
