package com.itis.kikoff.models.shop;

import com.itis.kikoff.models.auth.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "basket")
//    @ToString.Exclude
    private List<BasketProduct> basketProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Basket basket = (Basket) o;
        return id != null && Objects.equals(id, basket.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
