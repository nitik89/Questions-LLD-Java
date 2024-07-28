package socialNetworking;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocialNetworkingService {
    private static SocialNetworkingService instance;
    private final Map<String,User>users;
    private final Map<String,Post>posts;
    private final Map<String, List<Notification>> notifications;

    private SocialNetworkingService() {
        this.users = new ConcurrentHashMap<>();
        this.posts = new ConcurrentHashMap<>();
        this.notifications = new ConcurrentHashMap<>();
    }

    public static synchronized SocialNetworkingService getInstance() {
        if (instance == null) {
            instance = new SocialNetworkingService();
        }
        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User loginUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user){
        users.put(user.getId(),user);
    }

   public void sendRequest(String senderId, String recieverId){
    User reciever=users.get(recieverId);

    if(reciever!=null){
        Notification notification=new Notification(generateNotificationId(),recieverId,
        NotificationType.FRIEND_REQUEST,"Friend request from "+senderId,new Timestamp(System.currentTimeMillis()));
        addNotification(recieverId,notification);
    }
   }
   public void acceptNotificationRequest(String senderId,String recieverId){
    User user1=users.get(recieverId);
    User user2=users.get(senderId);
    if(user1!=null && user2!=null){
        user1.getFriends().add(senderId);
        user2.getFriends().add(recieverId);
        Notification notification = new Notification(generateNotificationId(), senderId,
        NotificationType.FRIEND_REQUEST_ACCEPTED, "Friend request accepted by " + recieverId,
        new Timestamp(System.currentTimeMillis()));
        addNotification(recieverId,notification);
    }
   }

public void createPost(Post post){
    posts.put(post.getId(),post);
    User user=users.get(post.getUserId());
    if(user!=null){
        user.getPosts().add(post);
    }
}
public List<Post> getNewsFeed(String userId){
    List<Post>newsFeed=new ArrayList<>();
    User user=users.get(userId);
    if(user!=null){
        List<String> friendIds = user.getFriends();
        for (String friendId : friendIds) {
            User friend = users.get(friendId);
            if (friend != null) {
                newsFeed.addAll(friend.getPosts());
            }
        }
        newsFeed.addAll(user.getPosts());
        newsFeed.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()));
    }
    return newsFeed;
}

public void likePost(String userId, String postId){
    Post post=posts.get(postId);
    if(post!=null && !post.getLikes().contains(userId)){
        post.getLikes().add(userId);
        Notification notification = new Notification(generateNotificationId(),post.getUserId(),NotificationType.LIKE, "Your post was liked by " + userId,new Timestamp(System.currentTimeMillis()));
        addNotification(post.getUserId(), notification);
    }
}

public void commentOnPost(Comment comment){
    Post post=posts.get(comment.getPostId());
    if(post!=null){
        post.getComments().add(comment);
        Notification notification = new Notification(generateNotificationId(),post.getUserId(),NotificationType.COMMENT, "Your post was commented by " + comment.getUserId(),new Timestamp(System.currentTimeMillis()));
        addNotification(post.getUserId(),notification);
    }
}
private void addNotification(String userId, Notification notification){
    notifications.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(notification);
}
   public List<Notification> getNotifications(String userId) {
        return notifications.getOrDefault(userId, new ArrayList<>());
    }

    private String generateNotificationId() {
        return UUID.randomUUID().toString();
    }


}
