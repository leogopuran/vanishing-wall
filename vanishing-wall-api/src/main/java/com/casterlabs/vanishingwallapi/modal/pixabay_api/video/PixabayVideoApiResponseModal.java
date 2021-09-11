package com.casterlabs.vanishingwallapi.modal.pixabay_api.video;

public class PixabayVideoApiResponseModal {
    private String type;
    private String tags;
    private int duration;
    private String picture_id;
    private PixabayVideos videos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public PixabayVideos getVideos() {
        return videos;
    }

    public void setVideos(PixabayVideos videos) {
        this.videos = videos;
    }
}
