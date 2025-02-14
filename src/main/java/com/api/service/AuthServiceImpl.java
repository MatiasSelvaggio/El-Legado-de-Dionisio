package com.api.service;

import com.api.exception.ApiException;
import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;
import com.api.model.entity.User;
import com.api.repository.UserRepository;
import com.api.service.inter.AuthService;
import com.api.util.Jwt;
import com.api.util.Roles;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    public SessionOut registerUser(RegisterUserIn input) {
        Optional<User> userOptional = this.userRepository.findByEmail(input.getEmail());

        if (userOptional.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Email is already in use");
        }

        User user = new User(Roles.ROLE_USER, input.getPassword(), input.getEmail(), input.getName(), input.getLastName());
        user = this.userRepository.save(user);

        return generateSession(user, "Success in create user");
    }


    private SessionOut generateSession(User user, String message) {
        Dotenv dotenv = Dotenv.load();
        String jwtExpirationInHoursStr = dotenv.get("JWTExpirationInHours");
        long jwtExpirationInHours = Long.parseLong(jwtExpirationInHoursStr);
        String jwt = Jwt.INSTANCE.getJWT(
                user.getIdUser().toString(),
                user.getEmail(),
                dotenv.get("UserIssuer"),
                user.getRole());
        Long expiration =  Date.from(LocalDateTime.now().plusHours(jwtExpirationInHours)
                .atZone(ZoneId.systemDefault()).toInstant()).getTime();
        return new SessionOut(message, jwt, expiration);
    }
}
