package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.LoginIn;
import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;
import com.api.model.entity.User;
import com.api.repository.UserRepository;
import com.api.service.inter.AuthService;
import com.api.util.Jwt;
import com.api.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final String jwtExpirationInHoursStr;
    private final String userIssuer;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           @Value("${jwt.expiration}") String jwtExpirationInHoursStr,
                           @Value("${jwt.issuer.user}") String userIssuer) {

        this.userRepository = userRepository;
        this.jwtExpirationInHoursStr = jwtExpirationInHoursStr;
        this.userIssuer = userIssuer;
    }

    public SessionOut registerUser(RegisterUserIn input) {
        Optional<User> userOptional = this.userRepository.findByEmail(input.getEmail());

        if (userOptional.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Email is already in use");
        }

        // TODO: update to use Crypt in password

        User user = new User(Roles.ROLE_USER, input.getPassword(), input.getEmail(), input.getName(), input.getLastName());
        user = this.userRepository.save(user);

        return generateSession(user, "Success in create user");
    }


    public SessionOut loginUser(LoginIn input) {
        // TODO: update to use Crypt in password
        return this.userRepository.findByEmail(input.getEmail()).filter(user -> user.getPassword().equals(input.getPassword()) )
                .map(user -> generateSession(user, "Login Success"))
                .orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "User with that mail not found")
        );

    }


    private SessionOut generateSession(User user, String message) {
        long jwtExpirationInHours = Long.parseLong(jwtExpirationInHoursStr);
        String jwt = Jwt.INSTANCE.getJWT(
                user.getIdUser().toString(),
                user.getEmail(),
                userIssuer,
                jwtExpirationInHoursStr
                ,
                user.getRole());
        Long expiration =  Date.from(LocalDateTime.now().plusHours(jwtExpirationInHours)
                .atZone(ZoneId.systemDefault()).toInstant()).getTime();
        return new SessionOut(message, jwt, expiration);
    }
}
