package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.IPostDeliveryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostDeliveryServiceImpl implements IPostDeliveryService {
    @Override
    public ArrayList<PostDetail> deliverNewPost() {
        return generateDummyPosts(20);
    }

    public ArrayList<PostDetail> generateDummyPosts(int count) {

        ArrayList<PostDetail> postDetails = new ArrayList<>();

        for(int i=0; i<count; i++){
            PostDetail postDetail = new PostDetail();
            String uniqueId = UUID.randomUUID().toString();
            postDetail.setId(uniqueId);
            postDetail.setTitle("Post__" + uniqueId);
            postDetail.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

            int max = 700;
            int min = 300;

            int imageWidth = (int)(Math.random()*(max-min+1)+min);
            int imageHeight = (int)(Math.random()*(max-min+1)+min);

//            imageWidth = 250;
//            imageHeight = 200;

            postDetail.setImageUrl("https://picsum.photos/" + imageWidth + "/" + imageHeight);
            postDetail.setDuration((int)(Math.random()*(180-60+1)+60));
            postDetail.setState("hide");

            postDetails.add(postDetail);
        }

        return postDetails;
    }
}
