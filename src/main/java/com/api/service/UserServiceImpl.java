package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.UpdateUserIn;
import com.api.model.dto.UserOut;
import com.api.model.entity.User;
import com.api.repository.UserRepository;
import com.api.service.inter.UserService;
import com.api.util.Roles;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserOut getUserById(Long userId) {
        return this.userRepository.findById(userId)
                .map(UserOut::new)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("User with id %d not found.", userId)));
    }

    public List<UserOut> getAllUsers() {
        return this.userRepository.findAll().stream().map(UserOut::new).toList();
    }

    public UserOut updateUser(Long userId, UpdateUserIn input) {
        return this.userRepository.findById(userId)
                .map(user -> {
                    if (input.getName() != null && !input.getName().isBlank()) user.setName(input.getName());
                    if (input.getLastName() != null  && !input.getLastName().isBlank()) user.setLastName(input.getLastName());
                    if (input.getPassword() != null  && !input.getPassword().isBlank()) user.setPassword(input.getPassword());
                    if (input.getEmail() != null  && !input.getEmail().isBlank()) {
                        Optional<User> oldUser = this.userRepository.findByEmail(input.getEmail());
                        if (oldUser.isPresent() && !oldUser.get().getIdUser().equals(userId)){
                            throw new ApiException(HttpStatus.CONFLICT, String.format("Another user has this email %s in use.", input.getEmail()));
                        }
                        user.setLastName(input.getLastName());
                    }
                    this.userRepository.save(user);
                    return new UserOut(user);
                })
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("User with id %d not found.", userId)));
    }

    public void deleteUser(Long userId) {
        this.userRepository.findById(userId)
                .map(user -> {
                    user.setDeleted(LocalDateTime.now());
                    this.userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("User with id %d not found.", userId)));
    }


    public UserOut updateRolUser(Long userId, String rol) {
        return this.userRepository.findById(userId)
                .map(user -> {
                    try {
                        user.setRole(Roles.valueOf(rol));
                    } catch (IllegalArgumentException e) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid user rol");
                    }
                    return new UserOut(user);
                })
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("User with id %d not found.", userId)));
    }

    public List<String> getRoles(){
        return Arrays.stream(Roles.values()).map(Objects::toString).toList();
    }
}
