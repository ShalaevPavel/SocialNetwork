package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.FriendRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAOImpl implements FriendRequestDAO {
    private Connection connection;

    public FriendRequestDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FriendRequest getFriendRequestById(int requestId) {
        FriendRequest friendRequest = null;
        try {
            String query = "SELECT * FROM friend_requests WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getInt("id"));
                friendRequest.setSenderId(resultSet.getInt("sender_id"));
                friendRequest.setReceiverId(resultSet.getInt("receiver_id"));
                friendRequest.setStatus(resultSet.getString("status"));
                // Set other friend request properties
            }

            ((ResultSet) resultSet).close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return friendRequest;
    }

    @Override
    public void sendFriendRequest(FriendRequest friendRequest) {
        try {
            String query = "INSERT INTO friend_requests (sender_id, receiver_id, status) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, friendRequest.getSenderId());
            statement.setInt(2, friendRequest.getReceiverId());
            statement.setString(3, friendRequest.getStatus());
            // Set other friend request properties

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void acceptFriendRequest(int requestId) {
        try {
            String query = "UPDATE friend_requests SET status = 'accepted' WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void rejectFriendRequest(int requestId) {
        try {
            String query = "UPDATE friend_requests SET status = 'rejected' WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void cancelFriendRequest(int requestId) {
        try {
            String query = "DELETE FROM friend_requests WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, requestId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public List<FriendRequest> getSentRequestsByUser(int userId) {
        List<FriendRequest> friendRequests = new ArrayList<>();
        try {
            String query = "SELECT * FROM friend_requests WHERE sender_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getInt("id"));
                friendRequest.setSenderId(resultSet.getInt("sender_id"));
                friendRequest.setReceiverId(resultSet.getInt("receiver_id"));
                friendRequest.setStatus(resultSet.getString("status"));
                // Set other friend request properties

                friendRequests.add(friendRequest);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return friendRequests;
    }

    @Override
    public List<FriendRequest> getReceivedRequestsByUser(int userId) {
        List<FriendRequest> friendRequests = new ArrayList<>();
        try {
            String query = "SELECT * FROM friend_requests WHERE receiver_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getInt("id"));
                friendRequest.setSenderId(resultSet.getInt("sender_id"));
                friendRequest.setReceiverId(resultSet.getInt("receiver_id"));
                friendRequest.setStatus(resultSet.getString("status"));
                // Set other friend request properties

                friendRequests.add(friendRequest);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return friendRequests;
    }
}
