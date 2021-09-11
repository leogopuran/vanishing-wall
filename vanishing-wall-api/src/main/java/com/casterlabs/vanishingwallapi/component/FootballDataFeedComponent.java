package com.casterlabs.vanishingwallapi.component;

import com.casterlabs.vanishingwallapi.modal.scorebat_api.ScorebatApiResponseModal;
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

import static java.rmi.server.LogStream.log;

@Component
@Slf4j
public class FootballDataFeedComponent {

    private static final String scorebatApiUrl = "https://www.scorebat.com/video-api/v3/";
    public static List<ScorebatApiResponseModal> footballFeeds;

    @Autowired
    public FootballDataFeedComponent(){
        FootballDataFeedComponent.footballFeeds = generateFootballFeed();
    }

    public void reloadFootballFeeds(){
        this.footballFeeds = generateFootballFeed();
    }

    public List<ScorebatApiResponseModal> getFootballFeeds() {
        return footballFeeds;
    }

    public static void setFootballFeeds(List<ScorebatApiResponseModal> footballFeeds) {
        FootballDataFeedComponent.footballFeeds = footballFeeds;
    }

    private List<ScorebatApiResponseModal> generateFootballFeed(){
        try {
            System.out.println("generating football feeds");
            String apiResponse = getFootballFeedsFromScorebat();
            List<ScorebatApiResponseModal> footballFeeds = fetchPostDetailsFromApi(apiResponse);
            return footballFeeds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<ScorebatApiResponseModal> fetchPostDetailsFromApi(String apiResponse) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(apiResponse);
        String scorebatResponseString = ((JsonObject)jsonElement).get("response").toString();
        List<ScorebatApiResponseModal> scorebatFeeds = Arrays.asList(gson.fromJson(scorebatResponseString, ScorebatApiResponseModal[].class));

        return scorebatFeeds;

    }

    private String getFootballFeedsFromScorebat() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(scorebatApiUrl));

        HttpResponse httpResponse = httpClient.execute(httpGet);

        String responseJson = EntityUtils.toString(httpResponse.getEntity());
        return responseJson;
    }
}
