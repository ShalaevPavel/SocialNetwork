package by.fpmibsu.network.util;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/socialnetwork";
    private static final String USERNAME = "user1";
    private static final String PASSWORD = "12345678";
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connection successful!");

                // Perform a simple query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    System.out.println("Query result: " + result);
                }

                // Close the connection
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}


