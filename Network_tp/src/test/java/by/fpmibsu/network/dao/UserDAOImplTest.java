package by.fpmibsu.network.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import by.fpmibsu.network.model.User;

public class UserDAOImplTest {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private UserDAOImpl userDAO;

    @Before
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        statement = mock(Statement.class);
        userDAO = new UserDAOImpl(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetUserById() throws SQLException {
        int userId = 1;
        String username = "TestUser";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(userId);
        when(resultSet.getString("username")).thenReturn(username);

        User user = userDAO.getUserById(userId);

        assertEquals(userId, user.getId());
        assertEquals(username, user.getUsername());

        verify(connection).prepareStatement("SELECT * FROM users WHERE id = ?");
        verify(preparedStatement).setInt(1, userId);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("username");
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testGetUserByUsername() throws SQLException {
        String username = "TestUser";
        int userId = 1;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(userId);
        when(resultSet.getString("username")).thenReturn(username);

        User user = userDAO.getUserByUsername(username);

        assertEquals(userId, user.getId());
        assertEquals(username, user.getUsername());

        verify(connection).prepareStatement("SELECT * FROM users WHERE username = ?");
        verify(preparedStatement).setString(1, username);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("username");
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testCreateUser() throws SQLException {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("TestPassword");

        userDAO.createUser(user);

        verify(connection).prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        verify(preparedStatement).setString(1, user.getUsername());
        verify(preparedStatement).setString(2, user.getPassword());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = new User();
        user.setId(1);
        user.setUsername("UpdatedUser");
        user.setPassword("UpdatedPassword");

        userDAO.updateUser(user);

        verify(connection).prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?");
        verify(preparedStatement).setString(1, user.getUsername());
        verify(preparedStatement).setString(2, user.getPassword());
        verify(preparedStatement).setInt(3, user.getId());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        int userId = 1;

        userDAO.deleteUser(userId);

        verify(connection).prepareStatement("DELETE FROM users WHERE id = ?");
        verify(preparedStatement).setInt(1, userId);
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        int userId1 = 1;
        int userId2 = 2;
        String username1 = "User1";
        String username2 = "User2";

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(userId1, userId2);
        when(resultSet.getString("username")).thenReturn(username1, username2);

        List<User> users = userDAO.getAllUsers();

        assertEquals(2, users.size());

        User user1 = users.get(0);
        assertEquals(userId1, user1.getId());
        assertEquals(username1, user1.getUsername());

        User user2 = users.get(1);
        assertEquals(userId2, user2.getId());
        assertEquals(username2, user2.getUsername());

        verify(connection).createStatement();
        verify(statement).executeQuery("SELECT * FROM users");
        verify(resultSet, times(2)).next();
        verify(resultSet).getInt("id");
        verify(resultSet, times(2)).getString("username");
        verify(resultSet).close();
        verify(statement).close();
    }
}