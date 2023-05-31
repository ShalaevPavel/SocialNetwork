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

public class CommentDAOImplTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;

    private CommentDAOImpl commentDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commentDAO = new CommentDAOImpl(connection);
    }

    @Test
    public void testGetCommentById() throws SQLException {
        // Arrange
        Comment expectedComment = new Comment();
        expectedComment.setId(1);
        expectedComment.setPostId(1);
        expectedComment.setUserId(1);
        expectedComment.setContent("Test comment");
        // Set other comment properties

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(expectedComment.getId());
        when(resultSet.getInt("post_id")).thenReturn(expectedComment.getPostId());
        when(resultSet.getInt("user_id")).thenReturn(expectedComment.getUserId());
        when(resultSet.getString("content")).thenReturn(expectedComment.getContent());
        // Set other comment properties

        // Act
        Comment actualComment = commentDAO.getCommentById(1);

        // Assert
        assertEquals(expectedComment, actualComment);
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(1));
        verify(statement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getInt("post_id");
        verify(resultSet).getInt("user_id");
        verify(resultSet).getString("content");
        // Verify other comment properties
        verify(resultSet).close();
        verify(statement).close();
    }

    @Test
    public void testCreateComment() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setUserId(1);
        comment.setContent("Test comment");
        // Set other comment properties

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Act
        commentDAO.createComment(comment);

        // Assert
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(comment.getPostId()));
        verify(statement).setInt(eq(2), eq(comment.getUserId()));
        verify(statement).setString(eq(3), eq(comment.getContent()));
        // Set other comment properties
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testUpdateComment() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setId(1);
        comment.setPostId(1);
        comment.setUserId(1);
        comment.setContent("Updated comment");
        // Set other comment properties

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Act
        commentDAO.updateComment(comment);

        // Assert
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(comment.getPostId()));
        verify(statement).setInt(eq(2), eq(comment.getUserId()));
        verify(statement).setString(eq(3), eq(comment.getContent()));
        // Set other comment properties
        verify(statement).setInt(eq(4), eq(comment.getId()));
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testDeleteComment() throws SQLException {
        // Arrange
        int commentId = 1;

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Act
        commentDAO.deleteComment(commentId);

        // Assert
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(eq(1), eq(commentId));
        verify(statement).executeUpdate();
        verify(statement).close();
    }

    @Test
    public void testGetCommentsByPost() throws SQLException {
        // Arrange
        int postId = 1;
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setPostId(postId);
        comment1.setUserId(1);
        comment1.setContent("Comment 1");
        // Set other comment properties

        Comment comment2 = new Comment();
        comment2.setId(2);
        comment2.setPostId(postId);
        comment2.setUserId(2);
        comment2.setContent("Comment 2");
        // Set other comment properties

        List<Comment> expectedComments = new ArrayList<>();
        expectedComments.add(comment1);
        expectedComments.add(comment2);

        String expectedQuery = "SELECT * FROM comments WHERE post_id = ?";
        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(comment1.getId(), comment2.getId());
        when(resultSet.getInt("post_id")).thenReturn(comment1.getPostId(), comment2.getPostId());
        when(resultSet.getInt("user_id")).thenReturn(comment1.getUserId(), comment2.getUserId());
        when(resultSet.getString("content")).thenReturn(comment1.getContent(), comment2.getContent());
        // Set other comment properties

        // Act
        List<Comment> actualComments = commentDAO.getCommentsByPost(postId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(postId));
        verify(statement).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(2)).getInt("id");
        verify(resultSet, times(2)).getInt("post_id");
        verify(resultSet, times(2)).getInt("user_id");
        verify(resultSet, times(2)).getString("content");
        // Verify other comment properties
        verify(resultSet).close();
        verify(statement).close();

        assertEquals(expectedComments, actualComments);
    }

    @Test
    public void testGetCommentsByUser() throws SQLException {
        // Arrange
        int userId = 1;
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setPostId(1);
        comment1.setUserId(userId);
        comment1.setContent("Comment 1");
        // Set other comment properties

        Comment comment2 = new Comment();
        comment2.setId(2);
        comment2.setPostId(2);
        comment2.setUserId(userId);
        comment2.setContent("Comment 2");
        // Set other comment properties

        List<Comment> expectedComments = new ArrayList<>();
        expectedComments.add(comment1);
        expectedComments.add(comment2);

        String expectedQuery = "SELECT * FROM comments WHERE user_id = ?";
        when(connection.prepareStatement(expectedQuery)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(comment1.getId(), comment2.getId());
        when(resultSet.getInt("post_id")).thenReturn(comment1.getPostId(), comment2.getPostId());
        when(resultSet.getInt("user_id")).thenReturn(comment1.getUserId(), comment2.getUserId());
        when(resultSet.getString("content")).thenReturn(comment1.getContent(), comment2.getContent());
        // Set other comment properties

        // Act
        List<Comment> actualComments = commentDAO.getCommentsByUser(userId);

        // Assert
        verify(connection).prepareStatement(expectedQuery);
        verify(statement).setInt(eq(1), eq(userId));
        verify(statement).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(2)).getInt("id");
        verify(resultSet, times(2)).getInt("post_id");
        verify(resultSet, times(2)).getInt("user_id");
        verify(resultSet, times(2)).getString("content");
        // Verify other comment properties
        verify(resultSet).close();
        verify(statement).close();

        assertEquals(expectedComments, actualComments);
    }
}