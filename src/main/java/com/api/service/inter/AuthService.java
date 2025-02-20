package com.api.service.inter;

import com.api.model.dto.LoginIn;
import com.api.model.dto.RegisterUserIn;
import com.api.model.dto.SessionOut;

public interface AuthService {
    SessionOut registerUser(RegisterUserIn input);

    SessionOut loginUser(LoginIn loginIn);
}
