package com.casterlabs.vanishingwallapi.modal;

public class PixabayPostDetail extends PostDetail{
    private String videoUrlLarge;
    private String videoUrlMedium;
    private String videoUrlSmall;

    public String getVideoUrlLarge() {
        return videoUrlLarge;
    }

    public void setVideoUrlLarge(String videoUrlLarge) {
        this.videoUrlLarge = videoUrlLarge;
    }

    public String getVideoUrlMedium() {
        return videoUrlMedium;
    }

    public void setVideoUrlMedium(String videoUrlMedium) {
        this.videoUrlMedium = videoUrlMedium;
    }

    public String getVideoUrlSmall() {
        return videoUrlSmall;
    }

    public void setVideoUrlSmall(String videoUrlSmall) {
        this.videoUrlSmall = videoUrlSmall;
    }
}
