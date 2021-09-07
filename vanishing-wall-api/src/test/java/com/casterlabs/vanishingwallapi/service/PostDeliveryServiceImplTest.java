package com.casterlabs.vanishingwallapi.service;

import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.impl.PostDeliveryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PostDeliveryServiceImplTest {

    PostDeliveryServiceImpl postDeliveryService = new PostDeliveryServiceImpl();

    @Test
    public void generateDummyPostsTest(){
        ArrayList<PostDetail> postDetails = postDeliveryService.generateDummyPosts(5);
    }
}
