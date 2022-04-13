package com.itis.kikoff.services.signUp;

import com.itis.kikoff.dto.SignUpFormDto;

public interface SignUpService {
    void signUpUser(SignUpFormDto form);

//    boolean userWithSuchEmailExists(String email);

//    void signUp(SignUpFormDto userForm);
}
