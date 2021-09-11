package com.casterlabs.vanishingwallapi.service.impl;

import com.casterlabs.vanishingwallapi.component.FootballDataFeedComponent;
import com.casterlabs.vanishingwallapi.modal.FootballPostDetail;
import com.casterlabs.vanishingwallapi.modal.scorebat_api.ScorebatApiResponseModal;
import com.casterlabs.vanishingwallapi.modal.scorebat_api.ScorebatVideo;
import com.casterlabs.vanishingwallapi.service.IFootballDataFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballDataFeedServiceImpl implements IFootballDataFeedService {

    private static final int FOOTBALL_FEED_DURATON = 3600;

    @Autowired
    private FootballDataFeedComponent footballDataFeedComponent;

    @Override
    public FootballPostDetail getRandomFootballData() {
        List<ScorebatApiResponseModal> footballFeeds = footballDataFeedComponent.getFootballFeeds();

        int max_available_feeds = footballFeeds.size();
        int randomFeedIndex = (int) (Math.random()*(max_available_feeds));

        FootballPostDetail footballPostDetail = new FootballPostDetail();
        ScorebatApiResponseModal randomFootballFeed = footballFeeds.get(randomFeedIndex);

        footballPostDetail.setTitle(randomFootballFeed.getTitle());
        footballPostDetail.setDescription(randomFootballFeed.getTitle());
        footballPostDetail.setImageUrl(randomFootballFeed.getThumbnail());

        if(null != randomFootballFeed.getVideos() && !randomFootballFeed.getVideos().isEmpty()){
            List<ScorebatVideo> videos = randomFootballFeed.getVideos();
            if(randomFootballFeed.getVideos().size() > 1){
                int randomVideoIndex = (int) (Math.random()*(videos.size()));
                footballPostDetail.setCodeToEmbed(videos.get(randomVideoIndex).getEmbed());
            }
            else{
                footballPostDetail.setCodeToEmbed(videos.get(0).getEmbed());
            }
        }

        footballPostDetail.setDuration(FOOTBALL_FEED_DURATON);
        footballPostDetail.setContentType("video");

        return footballPostDetail;
    }


}
