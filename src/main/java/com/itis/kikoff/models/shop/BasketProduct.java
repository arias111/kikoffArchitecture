package com.itis.kikoff.models.shop;

import javax.persistence.*;

@Entity
@Table(name = "basket_product")
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
