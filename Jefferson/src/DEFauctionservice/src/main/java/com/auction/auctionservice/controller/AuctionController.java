package com.auction.auctionservice.controller;

import com.auction.auctionservice.model.Auction;
import com.auction.auctionservice.model.Bid;
import com.auction.auctionservice.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuctionInfo(@PathVariable Long id) {
        Auction auction = service.getAuction(id);
        List<Bid> latestBids = service.getLatestBids(id);

        Map<String, Object> response = new HashMap<>();
        response.put("productName", auction.getProduct().getName());
        response.put("description", auction.getProduct().getDescription());
        response.put("imageUrl", auction.getProduct().getImageUrl());
        response.put("currentPrice", auction.getCurrentPrice());
        response.put("endTime", auction.getEndDate());
        response.put("latestBids", latestBids);

        return ResponseEntity.ok(response);
    }
}
