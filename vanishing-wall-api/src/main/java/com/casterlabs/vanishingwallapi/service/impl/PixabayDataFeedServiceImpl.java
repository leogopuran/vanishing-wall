package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.component.PixabayDataFeedComponent;
import com.casterlabs.vanishingwallapi.modal.PixabayPostDetail;
import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.modal.pixabay_api.image.PixabayImageApiResponseModal;
import com.casterlabs.vanishingwallapi.modal.pixabay_api.video.PixabayVideoApiResponseModal;
import com.casterlabs.vanishingwallapi.service.IPixabayDataFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixabayDataFeedServiceImpl implements IPixabayDataFeedService {

    private static final int PIXABAY_VIDEO_FEED_DURATON = 120;
    private static final int PIXABAY_IMAGE_FEED_DURATON = 90;

    @Override
    public PostDetail getPixabayVideoFeeds() {
        List<PixabayVideoApiResponseModal> pixabayVideoFeeds = PixabayDataFeedComponent.pixabayVideoFeeds;

        int max_available_feeds = pixabayVideoFeeds.size();
        int randomFeedIndex = (int) (Math.random()*(max_available_feeds));

        PixabayPostDetail pixabayPostDetail = new PixabayPostDetail();
        PixabayVideoApiResponseModal pixabayVideoFeed = pixabayVideoFeeds.get(randomFeedIndex);

        pixabayPostDetail.setContentType("pixabay_video");
        pixabayPostDetail.setTitle("Pixabay video");
        pixabayPostDetail.setDescription("This video was prvided by Pixabay");
        pixabayPostDetail.setImageUrl("https://i.vimeocdn.com/video/" + pixabayVideoFeed.getPicture_id() + "_640x360.jpg");
        pixabayPostDetail.setDuration(PIXABAY_VIDEO_FEED_DURATON);
        pixabayPostDetail.setVideoUrlLarge(pixabayVideoFeed.getVideos().getLarge().getUrl());
        pixabayPostDetail.setVideoUrlMedium(pixabayVideoFeed.getVideos().getMedium().getUrl());
        pixabayPostDetail.setVideoUrlSmall(pixabayVideoFeed.getVideos().getSmall().getUrl());

        return pixabayPostDetail;
    }

    @Override
    public PostDetail getPixabayImageFeeds() {
        List<PixabayImageApiResponseModal> pixabayImageFeeds = PixabayDataFeedComponent.pixabayImageFeeds;

        int max_available_feeds = pixabayImageFeeds.size();
        int randomFeedIndex = (int) (Math.random()*(max_available_feeds));

        PixabayPostDetail pixabayPostDetail = new PixabayPostDetail();
        PixabayImageApiResponseModal pixabayImageFeed = pixabayImageFeeds.get(randomFeedIndex);

        pixabayPostDetail.setContentType("image");
        pixabayPostDetail.setTitle("Pixabay image");
        pixabayPostDetail.setDescription("This image was prvided by Pixabay");
        pixabayPostDetail.setDuration(PIXABAY_IMAGE_FEED_DURATON);
        pixabayPostDetail.setImageUrl(pixabayImageFeed.getWebformatURL());

        return pixabayPostDetail;
    }


}
