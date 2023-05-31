import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FriendRequestServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private FriendRequestController friendRequestController;

    private FriendRequestServlet friendRequestServlet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        friendRequestServlet = new FriendRequestServlet();
        friendRequestServlet.friendRequestController = friendRequestController;
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Arrange
        List<FriendRequest> friendRequests = new ArrayList<>();
        when(friendRequestController.getFriendRequestsByUserId(0)).thenReturn(friendRequests);
        when(request.getRequestDispatcher("/views/friendrequest.jsp")).thenReturn(dispatcher);

        // Act
        friendRequestServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("friendRequests", friendRequests);
        verify(dispatcher).forward(request, response);
    }
}