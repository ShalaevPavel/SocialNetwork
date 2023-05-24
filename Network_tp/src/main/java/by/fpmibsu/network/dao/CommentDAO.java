package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.Comment;

import java.util.List;

public interface CommentDAO {
    Comment getCommentById(int commentId);
    void createComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(int commentId);
    List<Comment> getCommentsByPost(int postId);
    List<Comment> getCommentsByUser(int userId);
    // Other methods as needed
}

