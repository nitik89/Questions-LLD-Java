package stackOverflow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionListing {
    private final String id;
    private final String itemName;
    private final String description;

    private final long duration;
    private final User seller;
    private  AuctionStatus status;
    private User currentHighestBidder;
    private double currentHighestBid;
    private final Map<User,Bid>bids;

    public AuctionListing(String id, String itemName, String description, double startingPrice, long duration, User seller) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.duration = duration;
        this.seller = seller;
        this.currentHighestBid = startingPrice;
        this.status = AuctionStatus.ACTIVE;
        this.currentHighestBidder = null;
        this.bids = new ConcurrentHashMap<>();
    }

    public synchronized void placeBid(Bid bid){
        if(status==AuctionStatus.ACTIVE && bid.getAmount() > currentHighestBid){
            currentHighestBidder = bid.getBidder();
            currentHighestBid = bid.getAmount();
            bids.put(bid.getBidder(), bid);
            notifyBidders("Item "+itemName+" is placed at a higher bid ");
            //notify function call here
        }
    }
    public synchronized void closeAuction(){
        if(status == AuctionStatus.ACTIVE ){
            this.status = AuctionStatus.CLOSED;
        }
        notifyBidders("Item "+itemName+" is sold");
        //notify function call here
    }
    // getters and setters
    public String getId() {
        return id;
    }
    public AuctionStatus getStatus() {
        return status;
    }
    public long getDuration() {
        return duration;
    }
    public String getItemName(){
        return itemName;
    }

    public String getDescription(){
        return description;
    }
    public User getCurrentHighestBidder() {
        return currentHighestBidder;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    //notify bidders function i need

    public void notifyBidders(String message){
        // implementation here
        // for example:
        for(User bidder : bids.keySet()){
            bidder.setNotification(message);
            bidder.displayNotification();
        }
    }
}
