package com.itis.kikoff.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SignUpForm {

    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;
    private LocalDate birthday;

    private LocalDateTime creationDate;
}

