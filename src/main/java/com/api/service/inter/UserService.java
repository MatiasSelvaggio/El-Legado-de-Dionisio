package com.api.service.inter;

import com.api.model.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
