package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.User;

import java.util.List;

public interface UserDAO {
    User getUserById(int userId);
    User getUserByUsername(String username);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
}

