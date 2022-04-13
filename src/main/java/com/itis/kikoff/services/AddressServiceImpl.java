package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.AddressDto;
import com.itis.kikoff.models.shop.Address;
import com.itis.kikoff.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void saveAddress(AddressDto addressDto) {
        addressRepository.save(AddressDto.to(addressDto));
    }
}
