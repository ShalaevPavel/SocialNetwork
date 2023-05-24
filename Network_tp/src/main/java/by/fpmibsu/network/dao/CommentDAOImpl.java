package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private Connection connection;

    public CommentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Comment getCommentById(int commentId) {
        Comment comment = null;
        try {
            String query = "SELECT * FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setContent(resultSet.getString("content"));
                // Set other comment properties
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return comment;
    }

    @Override
    public void createComment(Comment comment) {
        try {
            String query = "INSERT INTO comments (post_id, user_id, content) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getPostId());
            statement.setInt(2, comment.getUserId());
            statement.setString(3, comment.getContent());
            // Set other comment properties

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            String query = "UPDATE comments SET post_id = ?, user_id = ?, content = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getPostId());
            statement.setInt(2, comment.getUserId());
            statement.setString(3, comment.getContent());
            // Set other comment properties
            statement.setInt(4, comment.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public void deleteComment(int commentId) {
        try {
            String query = "DELETE FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, commentId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    @Override
    public List<Comment> getCommentsByPost(int postId) {
        List<Comment> comments = new ArrayList<>();
        try {
            String query = "SELECT * FROM comments WHERE post_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setContent(resultSet.getString("content"));
                // Set other comment properties

                comments.add(comment);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByUser(int userId) {
        List<Comment> comments = new ArrayList<>();
        try {
            String query = "SELECT * FROM comments WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setContent(resultSet.getString("content"));
                // Set other comment properties

                comments.add(comment);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return comments;
    }
}

