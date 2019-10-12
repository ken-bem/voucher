package com.rumroute.voucher;

import com.rumroute.model.voucher.VoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public void getAllVouchers(){}
    public void createVoucher(){}
    public void updateVoucher(){}
    public void redeemVoucher(){}
    public void deleteVoucher(){}




}
