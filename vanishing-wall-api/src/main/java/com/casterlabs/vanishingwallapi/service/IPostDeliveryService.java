package com.casterlabs.vanishingwallapi.service;

import com.casterlabs.vanishingwallapi.modal.PostDetail;

import java.util.ArrayList;

public interface IPostDeliveryService {
    ArrayList<PostDetail> deliverNewPost();
}
