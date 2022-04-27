package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Role role;
    private LocalDateTime birthday;
    private LocalDateTime creationDate;
    private Long personalAccountId;
    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .role(user.getRole())
                .birthday(user.getBirthday())
                .creationDate(user.getCreationDate())
                .personalAccountId(user.getPersonalAccount().getId()).build();
    }
    public static User to(UserDto userDto, PersonalAccount personalAccount) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .patronymic(userDto.getPatronymic())
                .role(userDto.getRole())
                .birthday(userDto.getBirthday())
                .creationDate(userDto.getCreationDate())
                .personalAccount(personalAccount)
                .build();
    }
}
