package com.itis.kikoff.models.shop;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String houseNumber;
    private Integer flat;
    @OneToMany(mappedBy = "address")
    private List<Bill> bills;

    public boolean equals(Address address1, Address address2) {
        return (Objects.equals(address1.street, address2.street)) && (Objects.equals(address1.houseNumber, address2.houseNumber)) &&
                (Objects.equals(address1.flat, address2.flat));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
