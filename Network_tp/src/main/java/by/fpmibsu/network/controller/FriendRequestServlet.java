package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.FriendRequestDAOImpl;
import by.fpmibsu.network.model.FriendRequest;
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

@WebServlet("/friendrequest")
public class FriendRequestServlet extends HttpServlet {
    private FriendRequestController friendRequestController;

    @Override
    public void init() throws ServletException {
        // Initialize the friendRequestController (Dependency Injection can be used here)
        try {
            friendRequestController = new FriendRequestController(new FriendRequestDAOImpl(DBUtil.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Retrieve friend requests from the database using the friendRequestController
        List<FriendRequest> friendRequests = friendRequestController.getFriendRequestsByUserId(0);

        // Set the retrieved friend requests as an attribute in the request
        request.setAttribute("friendRequests", friendRequests);

        // Forward the request to the friendrequest.jsp view for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/friendrequest.jsp");
        dispatcher.forward(request, response);
    }
}
