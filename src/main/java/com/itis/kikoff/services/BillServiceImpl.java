package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BillDto;
import com.itis.kikoff.models.dto.BillIdDto;
import com.itis.kikoff.models.dto.ProductIdDto;
import com.itis.kikoff.models.enums.Status;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Address;
import com.itis.kikoff.models.shop.Bill;
import com.itis.kikoff.models.shop.BillProduct;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillProductRepository billProductRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @Override
    public BillIdDto createBill(BillDto billDto) {
        Optional<Address> optionalAddress = addressRepository.findById(billDto.getAddressId());
        Optional<PersonalAccount> optionalPersonalAccount = personalAccountRepository.findById(billDto.getPersonalAccountId());
        if (optionalAddress.isPresent() && optionalPersonalAccount.isPresent()) {
            Bill bill = BillDto.to(billDto, optionalAddress.get(), optionalPersonalAccount.get());
            billRepository.save(bill);
            List<Bill> bills = billRepository.findAllByPersonalAccount_Id(optionalPersonalAccount.get().getId());
            if (!bills.isEmpty()) {
                return BillIdDto.builder()
                        .id(bills.get(bills.size() - 1).getId())
                        .build();
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public BillIdDto successBill(BillIdDto billIdDto) {
        Bill bill = billRepository.getById(billIdDto.getId());
        PersonalAccount personalAccount = personalAccountRepository.getById(bill.getPersonalAccount().getId());
        bill.setStatus(Status.SUCCESS);
        billRepository.save(bill);
        int balance = personalAccount.getBalance() - bill.getPrice();
        personalAccount.setBalance(balance);
        personalAccountRepository.save(personalAccount);
        return billIdDto;
    }

}
