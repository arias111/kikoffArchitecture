package com.itis.kikoff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private LocalDateTime creationDate;
}
