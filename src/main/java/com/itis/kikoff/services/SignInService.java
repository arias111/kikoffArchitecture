package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(EmailPasswordDto emailPasswordDto);
}
