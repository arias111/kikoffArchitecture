package com.itis.kikoff.models.shop;

import com.itis.kikoff.models.auth.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "basket")
    private List<BasketProduct> basketProducts;
}
