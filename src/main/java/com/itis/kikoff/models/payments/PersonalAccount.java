package com.itis.kikoff.models.payments;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.shop.Bill;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "personal_account")
public class PersonalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private Integer balance;
    @OneToMany(mappedBy = "personalAccount")
    @ToString.Exclude
    private List<Payment> payments;
    @OneToMany(mappedBy = "personalAccount")
    @ToString.Exclude
    private List<Bill> bills;
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonalAccount that = (PersonalAccount) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
