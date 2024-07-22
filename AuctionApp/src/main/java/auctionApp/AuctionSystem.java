package stackOverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionSystem  {
    private static AuctionSystem instance;
    private final Map<String,User>users;
    private final Map<String,AuctionListing> auctionListings;

    private AuctionSystem() {
        this.users = new ConcurrentHashMap<String, User>();
        this.auctionListings = new ConcurrentHashMap<String, AuctionListing>();
    }

    public static synchronized AuctionSystem getInstance(){
        if(instance == null){
            instance = new AuctionSystem();
        }
        return instance;
    }


    public void createUser(User user){
        users.put(user.getId(), user);
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public void createAuctionListing(AuctionListing auctionListing){
        auctionListings.put(auctionListing.getId(), auctionListing);
        startAuctionListingTimer(auctionListing);
    }

    public List<AuctionListing> searchAuctionListings(String keyword){
        List<AuctionListing> matchingListings = new ArrayList<>();
        for (AuctionListing auctionListing : auctionListings.values()) {
            if (auctionListing.getItemName().contains(keyword) || auctionListing.getDescription().contains(keyword)) {
                matchingListings.add(auctionListing);
            }
        }
        return matchingListings;
    }

    public void placeBid(String auctionListingId,Bid bid) {
        AuctionListing auctionListing = auctionListings.get(auctionListingId);
        if (auctionListing!= null) {
            auctionListing.placeBid(bid);
        }
    }
    private void startAuctionListingTimer(AuctionListing auctionListing) {
         Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                auctionListing.closeAuction();
            }
        }, auctionListing.getDuration());
    }




}
