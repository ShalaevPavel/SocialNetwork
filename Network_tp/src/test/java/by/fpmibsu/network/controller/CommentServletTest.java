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

public class CommentServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private CommentController commentController;

    private CommentServlet commentServlet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commentServlet = new CommentServlet();
        commentServlet.commentController = commentController;
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Arrange
        List<Comment> comments = new ArrayList<>();
        when(commentController.getCommentById(0)).thenReturn(comments);
        when(request.getRequestDispatcher("/views/comment.jsp")).thenReturn(dispatcher);

        // Act
        commentServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("comments", comments);
        verify(dispatcher).forward(request, response);
    }
}