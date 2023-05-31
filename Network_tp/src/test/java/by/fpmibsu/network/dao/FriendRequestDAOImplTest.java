import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FriendRequestDAOImplTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;

    private FriendRequestDAOImpl friendRequestDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        friendRequestDAO = new FriendRequestDAOImpl(connection);
    }

    @Test
    public void testGetFriendRequestById() throws SQLException {
        // Arrange
        FriendRequest expectedFriendRequest = new FriendRequest();
        expectedFriendRequest.setId(1);
        expectedFriendRequest.setSenderId(1);
        expectedFriendRequest.setReceiverId(2);
        expectedFriendRequest.setStatus("pending");
        // Set other friend request properties

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(expectedFriendRequest.getId());
        when(resultSet.getInt("sender_id")).thenReturn(expectedFriendRequest.getSenderId());
        when(resultSet.getInt("receiver_id")).thenReturn(expectedFriendRequest.getReceiverId());
        when(resultSet.getString("status")).thenReturn(expectedFriendRequest.getStatus());
        // Set other friend request properties

        // Act
        FriendRequest actualFriendRequest = friendRequestDAO.getFriendRequestById(1);

        // Assert
        assertEquals(expectedFriendRequest, actualFriendRequest);
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(1));
        verify(statement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getInt("sender_id");
        verify(resultSet).getInt("receiver_id");
        verify(resultSet).getString("status");
        // Verify other friend request properties
        verify(resultSet).close();
        verify(statement).close();
    }

    @Test
    public void testSendFriendRequest() throws SQLException {
        // Arrange
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderId(1);
        friendRequest.setReceiverId(2);
        friendRequest.setStatus("pending");
        // Set other friend request properties

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Act
        friendRequestDAO.sendFriendRequest(friendRequest);

        // Assert
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(friendRequest.getSenderId()));
        verify(statement).setInt(eq(2), eq(friendRequest.getReceiverId()));
        verify(statement).setString(eq(3), eq(friendRequest.getStatus()));
        // Set other friend request properties
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testAcceptFriendRequest() throws SQLException {
        // Arrange
        int requestId = 1;
        String expectedQuery = "UPDATE friend_requests SET status = 'accepted' WHERE id = ?";

        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);

        // Act
        friendRequestDAO.acceptFriendRequest(requestId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(requestId));
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testRejectFriendRequest() throws SQLException {
        // Arrange
        int requestId = 1;
        String expectedQuery = "UPDATE friend_requests SET status = 'rejected' WHERE id = ?";

        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);

        // Act
        friendRequestDAO.rejectFriendRequest(requestId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(requestId));
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testCancelFriendRequest() throws SQLException {
        // Arrange
        int requestId = 1;
        String expectedQuery = "DELETE FROM friend_requests WHERE id = ?";

        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);

        // Act
        friendRequestDAO.cancelFriendRequest(requestId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(requestId));
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testGetSentRequestsByUser() throws SQLException {
        // Arrange
        int userId = 1;
        String expectedQuery = "SELECT * FROM friend_requests WHERE sender_id = ?";

        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);

        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getInt("sender_id")).thenReturn(userId);
        when(resultSet.getInt("receiver_id")).thenReturn(2, 3);
        when(resultSet.getString("status")).thenReturn("pending");
        // Set other friend request properties

        // Act
        List<FriendRequest> actualFriendRequests = friendRequestDAO.getSentRequestsByUser(userId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(userId));
        verify(statement).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(2)).getInt("id");
        verify(resultSet, times(2)).getInt("sender_id");
        verify(resultSet, times(2)).getInt("receiver_id");
        verify(resultSet, times(2)).getString("status");
        // Verify other friend request properties
        verify(resultSet).close();
        verify(statement).close();

        // Assert the size of the returned list
        assertEquals(2, actualFriendRequests.size());

        // Assert the properties of the first friend request
        FriendRequest firstFriendRequest = actualFriendRequests.get(0);
        assertEquals(1, firstFriendRequest.getId());
        assertEquals(userId, firstFriendRequest.getSenderId());
        assertEquals(2, firstFriendRequest.getReceiverId());
        assertEquals("pending", firstFriendRequest.getStatus());
        // Assert other friend request properties

        // Assert the properties of the second friend request
        FriendRequest secondFriendRequest = actualFriendRequests.get(1);
        assertEquals(2, secondFriendRequest.getId());
        assertEquals(userId, secondFriendRequest.getSenderId());
        assertEquals(3, secondFriendRequest.getReceiverId());
        assertEquals("pending", secondFriendRequest.getStatus());
        // Assert other friend request properties
    }

    @Test
    public void testGetReceivedRequestsByUser() throws SQLException {
        // Arrange
        int userId = 1;
        String expectedQuery = "SELECT * FROM friend_requests WHERE receiver_id = ?";

        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);

        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getInt("sender_id")).thenReturn(2, 3);
        when(resultSet.getInt("receiver_id")).thenReturn(userId);
        when(resultSet.getString("status")).thenReturn("pending");
        // Set other friend request properties

        // Act
        List<FriendRequest> actualFriendRequests = friendRequestDAO.getReceivedRequestsByUser(userId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(userId));
        verify(statement).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(2)).getInt("id");
        verify(resultSet, times(2)).getInt("sender_id");
        verify(resultSet, times(2)).getInt("receiver_id");
        verify(resultSet, times(2)).getString("status");
        // Verify other friend request properties
        verify(resultSet).close();
        verify(statement).close();

        // Assert the size of the returned list
        assertEquals(2, actualFriendRequests.size());

        // Assert the properties of the first friend request
        FriendRequest firstFriendRequest = actualFriendRequests.get(0);
        assertEquals(1, firstFriendRequest.getId());
        assertEquals(2, firstFriendRequest.getSenderId());
        assertEquals(userId, firstFriendRequest.getReceiverId());
        assertEquals("pending", firstFriendRequest.getStatus());
        // Assert other friend request properties

        // Assert the properties of the second friend request
        FriendRequest secondFriendRequest = actualFriendRequests.get(1);
        assertEquals(2, secondFriendRequest.getId());
        assertEquals(3, secondFriendRequest.getSenderId());
        assertEquals(userId, secondFriendRequest.getReceiverId());
        assertEquals("pending", secondFriendRequest.getStatus());
        // Assert other friend request properties
    }
}