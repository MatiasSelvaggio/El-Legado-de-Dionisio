package com.api.controller;

import com.api.controller.swagger.UserSwagger;
import com.api.exception.ApiException;
import com.api.model.dto.UpdateUserIn;
import com.api.model.dto.UserOut;
import com.api.service.UserServiceImpl;
import com.api.util.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserOut> getUserById(@PathVariable("userId") Long userId) {
        return new ResponseEntity<UserOut>(this.userService.getUserById(userId), HttpStatus.OK);
    }


    @GetMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserOut>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/rol/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserOut> updateRolUser(@PathVariable("userId")  Long userId, @Param("rol") String rol){
        if (Validator.isNullOrBlank(rol)) throw new ApiException(HttpStatus.BAD_REQUEST, "You must send a rol");

        return new ResponseEntity<>(this.userService.updateRolUser(userId,rol), HttpStatus.OK);
    }

    @GetMapping("/rol")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<String>> getRoles(){
        return new ResponseEntity<>(this.userService.getRoles(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserOut> updateUser(@PathVariable("userId")  Long userId,@RequestBody UpdateUserIn user) {
        if (Validator.isNullOrBlank(user.getName())   &&
                Validator.isNullOrBlank(user.getLastName()) &&
                Validator.isNullOrBlank(user.getPassword()) &&
                Validator.isNullOrBlank(user.getEmail()))
            throw new ApiException(HttpStatus.BAD_REQUEST, "You must send at least one field not blank to updated");
        if (!Validator.isNullOrBlank(user.getPassword()) && user.getPassword().length() < 8)
            throw new ApiException(HttpStatus.BAD_REQUEST, "You must send and password with 8 characters");
        if (!Validator.isNullOrBlank(user.getEmail()) && !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "email must be a valid email address");
        }
        return new ResponseEntity<>(this.userService.updateUser(userId, user), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
