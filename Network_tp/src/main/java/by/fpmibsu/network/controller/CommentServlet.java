package by.fpmibsu.network.controller;


import by.fpmibsu.network.dao.CommentDAOImpl;
import by.fpmibsu.network.model.Comment;
import by.fpmibsu.network.util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private CommentController commentController;

    @Override
    public void init() throws ServletException {
        // Initialize the commentController (Dependency Injection can be used here)
        try {
            commentController = new CommentController(new CommentDAOImpl(DBUtil.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
//        // Retrieve comments from the database using the commentController
//        List<Comment> comments = commentController.getCommentById(0);
//
//        // Set the retrieved comments as an attribute in the request
//        request.setAttribute("comments", comments);
//
//        // Forward the request to the comment.jsp view for rendering
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/comment.jsp");
//        dispatcher.forward(request, response);
//    }
}

