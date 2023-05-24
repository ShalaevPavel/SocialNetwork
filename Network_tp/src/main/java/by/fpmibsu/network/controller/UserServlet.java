package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.UserDAOImpl;
import by.fpmibsu.network.model.User;
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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserController userController;

    @Override
    public void init() throws ServletException {
        // Initialize the userController (Dependency Injection can be used here)
        try {
            userController = new UserController(new UserDAOImpl(DBUtil.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Retrieve users from the database using the userController
        List<User> users = userController.getAllUsers();

        // Set the retrieved users as an attribute in the request
        request.setAttribute("users", users);

        // Forward the request to the user.jsp view for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user.jsp");
        dispatcher.forward(request, response);
    }
}
