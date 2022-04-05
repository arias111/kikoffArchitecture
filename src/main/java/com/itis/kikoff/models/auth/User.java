package com.itis.kikoff.models.auth;

import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Basket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private String patronymic;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDateTime birthday;
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "user")
    private List<Basket> baskets;
    @OneToOne(mappedBy = "user")
    private PersonalAccount personalAccount;
}
