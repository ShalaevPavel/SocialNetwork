package by.fpmibsu.network.model;

import java.time.LocalDateTime;

public class FriendRequest {
    private int id;
    private int senderId; // User ID of the sender
    private int receiverId; // User ID of the receiver
    private LocalDateTime requestTime;
    private boolean accepted;

    // Constructors
    public FriendRequest() {
    }

    public FriendRequest(int id, int senderId, int receiverId, LocalDateTime requestTime, boolean accepted) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.requestTime = requestTime;
        this.accepted = accepted;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getStatus() {
         if (this.accepted){
             return "accepted";
        }
         else {
             return "not accepted";
         }
    }
}


