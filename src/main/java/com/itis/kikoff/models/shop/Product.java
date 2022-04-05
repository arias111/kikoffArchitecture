package com.itis.kikoff.models.shop;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer countOfProducts;
    private Integer priceOfOne;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Categories categories;
    @OneToMany(mappedBy = "product")
    private List<BillProduct> billProductList;
    @OneToMany(mappedBy = "product")
    private List<BasketProduct> basketProducts;
}
