package com.casterlabs.vanishingwallapi.modal.pixabay_api.video;

public class PixabayVideos {
    private PixabayVideo large;
    private PixabayVideo medium;
    private PixabayVideo small;

    public PixabayVideo getLarge() {
        return large;
    }

    public void setLarge(PixabayVideo large) {
        this.large = large;
    }

    public PixabayVideo getMedium() {
        return medium;
    }

    public void setMedium(PixabayVideo medium) {
        this.medium = medium;
    }

    public PixabayVideo getSmall() {
        return small;
    }

    public void setSmall(PixabayVideo small) {
        this.small = small;
    }
}
