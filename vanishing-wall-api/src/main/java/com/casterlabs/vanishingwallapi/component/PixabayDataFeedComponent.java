package com.casterlabs.vanishingwallapi.component;

import com.casterlabs.vanishingwallapi.modal.pixabay_api.image.PixabayImageApiResponseModal;
import com.casterlabs.vanishingwallapi.modal.pixabay_api.video.PixabayVideoApiResponseModal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class PixabayDataFeedComponent {
    private static final String pixabayVideoApiUrl = "https://pixabay.com/api/videos/?key=23334949-676e079a1b7e9eb7970ca5fec&pretty=true&safesearch=true&order=latest&editors_choice=true";
    private static final String pixabayImageApiUrl = "https://pixabay.com/api/?key=23334949-676e079a1b7e9eb7970ca5fec&image_type=photo&safesearch=true&order=latest&editors_choice=true";
    public static List<PixabayVideoApiResponseModal> pixabayVideoFeeds;
    public static List<PixabayImageApiResponseModal> pixabayImageFeeds;

    @Autowired
    public PixabayDataFeedComponent (){
        System.out.println("Generating video feeds from Pixabay");
        this.pixabayVideoFeeds = generateVideoFeeds();

        System.out.println("Generating image feeds from Pixabay");
        this.pixabayImageFeeds = generateimageFeeds();
    }

    private List<PixabayImageApiResponseModal> generateimageFeeds() {
        List<PixabayImageApiResponseModal> imageFeeds = null;
        try {
            String apiResponse = getFeedsFromPixabay(pixabayImageApiUrl);
            imageFeeds = fetchImageDataFromApiResponse(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFeeds;
    }

    private List<PixabayImageApiResponseModal> fetchImageDataFromApiResponse(String apiResponse) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(apiResponse);
        String imageFeedResponseString = ((JsonObject)jsonElement).get("hits").toString();
        List<PixabayImageApiResponseModal> imageFeeds = Arrays.asList(gson.fromJson(imageFeedResponseString, PixabayImageApiResponseModal[].class));

        return imageFeeds;
    }

    private List<PixabayVideoApiResponseModal> generateVideoFeeds() {
        List<PixabayVideoApiResponseModal> videoFeeds = null;
        try {
            String apiResponse = getFeedsFromPixabay(pixabayVideoApiUrl);
            videoFeeds = fetchVideoDataFromApiResponse(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoFeeds;
    }

    private List<PixabayVideoApiResponseModal> fetchVideoDataFromApiResponse(String apiResponse) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(apiResponse);
        String videoFeedResponseString = ((JsonObject)jsonElement).get("hits").toString();
        List<PixabayVideoApiResponseModal> videoFeeds = Arrays.asList(gson.fromJson(videoFeedResponseString, PixabayVideoApiResponseModal[].class));

        return videoFeeds;
    }

    private String getFeedsFromPixabay(String apiUrl) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(apiUrl));

        HttpResponse httpResponse = httpClient.execute(httpGet);

        String responseJson = EntityUtils.toString(httpResponse.getEntity());
        return responseJson;
    }
}
