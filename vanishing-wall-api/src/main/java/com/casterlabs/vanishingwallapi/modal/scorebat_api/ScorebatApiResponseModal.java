package com.casterlabs.vanishingwallapi.modal.scorebat_api;

import java.util.List;

public class ScorebatApiResponseModal {

    private String title;
    private String competition;
    private String thumbnail;
    private List<ScorebatVideo> videos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<ScorebatVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<ScorebatVideo> videos) {
        this.videos = videos;
    }
}
