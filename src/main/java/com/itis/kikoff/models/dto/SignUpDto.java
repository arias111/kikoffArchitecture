package com.itis.kikoff.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDateTime birthday;
}
