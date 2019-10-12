package com.rumroute.email;

import com.rumroute.model.voucher.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VoucherListener implements ApplicationListener<EmailEventHandler.OnVoucherRedeemCompleteEvent> {

    @Autowired
    private EmailHandler mail;


    @Override
    public void onApplicationEvent(EmailEventHandler.OnVoucherRedeemCompleteEvent event)  {

        try {
            this.validateVoucher(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validateVoucher(EmailEventHandler.OnVoucherRedeemCompleteEvent event) throws Exception{

        Voucher voucher = event.getVoucher();
        mail.voucherHandler(voucher);



    }




}
