package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.PostDAOImpl;
import by.fpmibsu.network.model.Post;
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

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    private PostController postController;

    @Override
    public void init() throws ServletException {
        // Initialize the postController (Dependency Injection can be used here)
        try {
            postController = new PostController(new PostDAOImpl(DBUtil.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Retrieve posts from the database using the postController
        List<Post> posts = postController.getAllPosts();

        // Set the retrieved posts as an attribute in the request
        request.setAttribute("posts", posts);

        // Forward the request to the post.jsp view for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/post.jsp");
        dispatcher.forward(request, response);
    }
}
