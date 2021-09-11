package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.IRandomPictureDataService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomPictureDataServiceImpl implements IRandomPictureDataService {
    @Override
    public PostDetail getRandomPicturePost() {
        PostDetail postDetail = new PostDetail();
        String uniqueId = UUID.randomUUID().toString();
        postDetail.setId(uniqueId);
        postDetail.setTitle("Post__" + uniqueId.substring(0, 7));
        postDetail.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        int max = 500;
        int min = 300;

        int imageWidth = (int)(Math.random()*(max-min+1)+min);
        int imageHeight = (int)(Math.random()*(max-min+1)+min);

        postDetail.setImageUrl("https://picsum.photos/" + imageWidth + "/" + imageHeight);
        postDetail.setDuration((int)(Math.random()*(180-60+1)+60)); // max=3min, min=1min
        postDetail.setState("hide");

        return postDetail;
    }
}
