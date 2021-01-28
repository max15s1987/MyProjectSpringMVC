package org.example.service;

import org.example.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    void update(int id, User user);
    void remove(int id);
    User getUserById(int id);

}
