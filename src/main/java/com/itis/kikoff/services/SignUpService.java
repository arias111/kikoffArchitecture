package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.SignUpDto;
import com.itis.kikoff.models.dto.TokenDto;

public interface SignUpService {
    TokenDto signUp(SignUpDto signUpDto);
}
