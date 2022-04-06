package com.itis.kikoff.dto;

import lombok.Data;

@Data
public class SignUpForm {

    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;
}

