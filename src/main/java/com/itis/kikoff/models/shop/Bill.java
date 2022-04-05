package com.itis.kikoff.models.shop;

import com.itis.kikoff.models.enums.Status;
import com.itis.kikoff.models.payments.PersonalAccount;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "personal_account_id", referencedColumnName = "id")
    private PersonalAccount personalAccount;
    private Integer price;
    private LocalDateTime buyingDate;
    @OneToMany(mappedBy = "bill")
    private List<BillProduct> billProduct;
}
