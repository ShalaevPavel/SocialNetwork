package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.FriendRequest;

import java.util.List;

public interface FriendRequestDAO {
    FriendRequest getFriendRequestById(int requestId);
    void sendFriendRequest(FriendRequest friendRequest);
    void acceptFriendRequest(int requestId);
    void rejectFriendRequest(int requestId);
    void cancelFriendRequest(int requestId);
    List<FriendRequest> getSentRequestsByUser(int userId);
    List<FriendRequest> getReceivedRequestsByUser(int userId);
    // Other methods as needed
}

