package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    private Connection connection; // JDBC connection object

    // Constructor to initialize the connection
    public PostDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Post getPostById(int postId) {
        Post post = null;
        try {
            String query = "SELECT * FROM posts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                // Set other post properties
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
        try {
            String query = "INSERT INTO posts (title, content) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            // Set other post properties

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void updatePost(Post post) {
        try {
            String query = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            // Set other post properties
            statement.setInt(3, post.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void deletePost(int postId) {
        try {
            String query = "DELETE FROM posts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, postId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public List<Post> getPostsByUser(int userId) {
        List<Post> posts = new ArrayList<>();
        try {
            String query = "SELECT * FROM posts WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                // Set other post properties

                posts.add(post);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return posts;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            String query = "SELECT * FROM posts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                // Set other post properties

                posts.add(post);
            }

            resultSet.close();
            ((Statement) statement).close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return posts;
    }
}

