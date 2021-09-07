package com.casterlabs.vanishingwallapi.controller;

import com.casterlabs.vanishingwallapi.modal.PostDetail;
import com.casterlabs.vanishingwallapi.service.IPostDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class WebSocketController {

    @Autowired
    private IPostDeliveryService postDeliveryService;

    @Autowired
    SimpMessagingTemplate template;

    private final int POST_BROADCAST_INTERVAL = 15000;
    private final String POST_DEFAULT_TOPIC = "/topic/posts/default";
    private final String POST_REQUEST_CHANNEL = "/chat";
    private final String POST_CREATE_RESPONSE_TOPIC = "/chat";

    @MessageMapping(POST_REQUEST_CHANNEL)
    @SendTo(POST_CREATE_RESPONSE_TOPIC)
    public ResponseEntity<Object> postController(PostDetail postRequest) throws Exception {
        log.info("Recieving POST");

        if(null != postRequest){
            log.info("Recieved request for POST -> ", postRequest);
        }
        else{
            log.info("Delivering new Posts.");
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Scheduled(fixedRate = POST_BROADCAST_INTERVAL)
    @SendTo(POST_DEFAULT_TOPIC)
    public void broadcastPosts() throws Exception {
        ArrayList<PostDetail> postDetails = postDeliveryService.deliverNewPost();
        template.convertAndSend(POST_DEFAULT_TOPIC, postDetails);
//        log.info("Broadcasted {} new POST's", postDetails.size());
    }

    @GetMapping(value = "/api/getPostsOndemand")
    public ResponseEntity<Object> deliverPostsOnDemand(){
        ArrayList<PostDetail> postDetails = postDeliveryService.deliverNewPost();
        return new ResponseEntity<>(postDetails, HttpStatus.OK);
    }
}
