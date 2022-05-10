package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.shop.Address;
import com.itis.kikoff.models.shop.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String street;
    private String houseNumber;
    private Integer flat;
    public static Address to(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .flat(addressDto.getFlat())
                .bills(new ArrayList<>())
                .build();
    }
}
