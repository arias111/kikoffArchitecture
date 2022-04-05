package com.itis.kikoff.models.payments;

import com.itis.kikoff.models.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "personal_account_id", referencedColumnName = "id")
    private PersonalAccount personalAccount;
    private Integer sum;
    private LocalDateTime transactionDate;
}
