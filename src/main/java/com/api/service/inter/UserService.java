package com.api.service.inter;

import com.api.model.dto.UpdateUserIn;
import com.api.model.dto.UserOut;
import com.api.model.entity.User;

import java.util.List;

public interface UserService {
    UserOut getUserById(Long userId);
    List<UserOut> getAllUsers();
    UserOut updateUser(Long userId, UpdateUserIn input);
    void deleteUser(Long userId);

    UserOut updateRolUser(Long userId, String rol);
}
