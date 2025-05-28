package com.auction.auctionservice.service;

import com.auction.auctionservice.model.Auction;
import com.auction.auctionservice.model.Bid;
import com.auction.auctionservice.repository.AuctionRepository;
import com.auction.auctionservice.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepo;

    @Autowired
    private BidRepository bidRepo;

    public Auction getAuction(Long id) {
        return auctionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Subasta no encontrada"));
    }

    public List<Bid> getLatestBids(Long auctionId) {
        return bidRepo.findTop3ByAuctionIdOrderByBidDateDesc(auctionId);
    }
}
