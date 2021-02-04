package org.example.service;

import org.example.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    void update(Long id, User user);
    void remove(Long id);
    User getUserById(Long id);

}
