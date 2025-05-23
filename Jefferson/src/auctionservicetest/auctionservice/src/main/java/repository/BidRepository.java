package repository;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findTop3ByAuctionIdOrderByBidDateDesc(Long auctionId);
}
