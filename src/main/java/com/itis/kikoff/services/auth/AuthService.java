package com.itis.kikoff.services.auth;


import com.itis.kikoff.dto.SignUpDto;
import com.itis.kikoff.dto.TokenDto;

public interface AuthService {
    TokenDto userRegistration(SignUpDto signUpDto);
}
