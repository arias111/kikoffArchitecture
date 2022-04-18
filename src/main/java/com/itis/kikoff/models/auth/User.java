package com.itis.kikoff.models.auth;

import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.enums.State;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {

    private static final long serialVersionUID = 9132222312087392904L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private LocalDateTime creationDate;

    //    private List<String> tokens;
    private Boolean isDeleted;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "user")
    private List<Basket> baskets;
    @OneToOne(mappedBy = "user")
    private PersonalAccount personalAccount;

    private String confirmCode;
}
