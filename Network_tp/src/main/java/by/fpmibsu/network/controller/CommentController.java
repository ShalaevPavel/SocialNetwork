package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.CommentDAO;
import by.fpmibsu.network.model.Comment;

import java.util.List;

public class CommentController {
    private CommentDAO commentDAO;

    public CommentController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }


    public Comment getCommentById(int commentId) {
        return commentDAO.getCommentById(commentId);
    }

    public void createComment(Comment comment) {
        commentDAO.createComment(comment);
    }

    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }

    public void deleteComment(int commentId) {
        commentDAO.deleteComment(commentId);
    }

    public List<Comment> getCommentsForPost(int postId) {
        return commentDAO.getCommentsByPost(postId);
    }
}
