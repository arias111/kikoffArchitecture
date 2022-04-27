package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BillDto;
import com.itis.kikoff.models.dto.BillIdDto;

public interface BillService {
    BillIdDto createBill(BillDto billDto);
    BillIdDto successBill(BillIdDto billIdDto);
}
