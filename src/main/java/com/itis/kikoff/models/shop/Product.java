package com.itis.kikoff.models.shop;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer countOfProducts;
    @NotNull
    private Integer priceOfOne;
    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categories;
    @OneToMany(mappedBy = "product")
    private List<BillProduct> billProductList;
    @OneToMany(mappedBy = "product")
    //@ToString.Exclude
    private List<BasketProduct> basketProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
