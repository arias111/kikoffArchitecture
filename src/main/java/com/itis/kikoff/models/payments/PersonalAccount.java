package com.itis.kikoff.models.payments;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.shop.Bill;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "personal_account")
public class PersonalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private Integer balance;
    @OneToMany(mappedBy = "personalAccount")
    private List<Payment> payments;
    @OneToMany(mappedBy = "personalAccount")
    private List<Bill> bills;
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
