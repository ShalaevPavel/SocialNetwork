package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.FriendRequestDAO;
import by.fpmibsu.network.model.FriendRequest;

import java.util.List;

public class FriendRequestController {
    private FriendRequestDAO friendRequestDAO;

    public FriendRequestController(FriendRequestDAO friendRequestDAO) {
        this.friendRequestDAO = friendRequestDAO;
    }


    public FriendRequest getFriendRequestById(int friendRequestId) {
        return friendRequestDAO.getFriendRequestById(friendRequestId);
    }

    public void createFriendRequest(FriendRequest friendRequest) {
        friendRequestDAO.sendFriendRequest(friendRequest);
    }

    public void updateFriendRequest(FriendRequest friendRequest) {
        friendRequestDAO.acceptFriendRequest(friendRequest.getId());
    }

    public void deleteFriendRequest(int friendRequestId) {
        friendRequestDAO.cancelFriendRequest(friendRequestId);
    }

    public List<FriendRequest> getFriendRequestsByUserId(int userId) {
        return friendRequestDAO.getSentRequestsByUser(userId);
    }
}


