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

public class UserServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private UserController userController;

    private UserServlet userServlet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userServlet = new UserServlet();
        userServlet.userController = userController;
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Arrange
        List<User> users = new ArrayList<>();
        when(userController.getAllUsers()).thenReturn(users);
        when(request.getRequestDispatcher("/views/user.jsp")).thenReturn(dispatcher);

        // Act
        userServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("users", users);
        verify(dispatcher).forward(request, response);
    }
}