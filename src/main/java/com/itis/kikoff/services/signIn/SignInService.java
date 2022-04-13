package com.itis.kikoff.services.signIn;


import com.itis.kikoff.dto.SignInForm;
import com.itis.kikoff.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInForm form);
}
