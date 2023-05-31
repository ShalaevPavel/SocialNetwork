package by.fpmibsu.network.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import by.fpmibsu.network.model.Post;

public class PostDAOImplTest {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private PostDAOImpl postDAO;

    @Before
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        statement = mock(Statement.class);
        postDAO = new PostDAOImpl(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetPostById() throws SQLException {
        int postId = 1;
        String title = "Test Post";
        String content = "Test Content";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(postId);
        when(resultSet.getString("title")).thenReturn(title);
        when(resultSet.getString("content")).thenReturn(content);

        Post post = postDAO.getPostById(postId);

        assertEquals(postId, post.getId());
        assertEquals(title, post.getTitle());
        assertEquals(content, post.getContent());

        verify(connection).prepareStatement("SELECT * FROM posts WHERE id = ?");
        verify(preparedStatement).setInt(1, postId);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("title");
        verify(resultSet).getString("content");
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testCreatePost() throws SQLException {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");

        postDAO.createPost(post);

        verify(connection).prepareStatement("INSERT INTO posts (title, content) VALUES (?, ?)");
        verify(preparedStatement).setString(1, post.getTitle());
        verify(preparedStatement).setString(2, post.getContent());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testUpdatePost() throws SQLException {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Updated Post");
        post.setContent("Updated Content");

        postDAO.updatePost(post);

        verify(connection).prepareStatement("UPDATE posts SET title = ?, content = ? WHERE id = ?");
        verify(preparedStatement).setString(1, post.getTitle());
        verify(preparedStatement).setString(2, post.getContent());
        verify(preparedStatement).setInt(3, post.getId());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testDeletePost() throws SQLException {
        int postId = 1;

        postDAO.deletePost(postId);

        verify(connection).prepareStatement("DELETE FROM posts WHERE id = ?");
        verify(preparedStatement).setInt(1, postId);
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testGetPostsByUser() throws SQLException {
        int userId = 1;
        int postId1 = 1;
        int postId2 = 2;
        String title1 = "Post 1";
        String title2 = "Post 2";

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(postId1, postId2);
        when(resultSet.getString("title")).thenReturn(title1, title2);

        List<Post> posts = postDAO.getPostsByUser(userId);

        assertEquals(2, posts.size());

        Post post1 = posts.get(0);
        assertEquals(postId1, post1.getId());
        assertEquals(title1, post1.getTitle());

        Post post2 = posts.get(1);
        assertEquals(postId2, post2.getId());
        assertEquals(title2, post2.getTitle());

        verify(connection).prepareStatement("SELECT * FROM posts WHERE user_id = ?");
        verify(preparedStatement).setInt(1, userId);
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next();
        verify(resultSet).getInt("id");
        verify(resultSet, times(2)).getString("title");
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testGetAllPosts() throws SQLException {
        int postId1 = 1;
        int postId2 = 2;
        String title1 = "Post 1";
        String title2 = "Post 2";

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(postId1, postId2);
        when(resultSet.getString("title")).thenReturn(title1, title2);

        List<Post> posts = postDAO.getAllPosts();

        assertEquals(2, posts.size());

        Post post1 = posts.get(0);
        assertEquals(postId1, post1.getId());
        assertEquals(title1, post1.getTitle());

        Post post2 = posts.get(1);
        assertEquals(postId2, post2.getId());
        assertEquals(title2, post2.getTitle());

        verify(connection).createStatement();
        verify(statement).executeQuery("SELECT * FROM posts");
        verify(resultSet, times(2)).next();
        verify(resultSet).getInt("id");
        verify(resultSet, times(2)).getString("title");
        verify(resultSet).close();
        verify(statement).close();
    }
}