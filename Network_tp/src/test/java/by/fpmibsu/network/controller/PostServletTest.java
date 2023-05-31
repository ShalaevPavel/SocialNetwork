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

public class PostServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private PostController postController;

    private PostServlet postServlet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        postServlet = new PostServlet();
        postServlet.postController = postController;
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Arrange
        List<Post> posts = new ArrayList<>();
        when(postController.getAllPosts()).thenReturn(posts);
        when(request.getRequestDispatcher("/views/post.jsp")).thenReturn(dispatcher);

        // Act
        postServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("posts", posts);
        verify(dispatcher).forward(request, response);
    }
}