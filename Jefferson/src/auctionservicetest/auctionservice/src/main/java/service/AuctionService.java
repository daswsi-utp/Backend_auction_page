package service;

import model.Auction;
import model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuctionRepository;
import repository.BidRepository;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

    public Auction getAuctionDetails(Long auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));
    }

    public List<Bid> getLatestBids(Long auctionId) {
        return bidRepository.findTop3ByAuctionIdOrderByBidDateDesc(auctionId);
    }
}

