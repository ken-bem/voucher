package com.rumroute.email;

import com.rumroute.model.voucher.Voucher;
import com.rumroute.model.user.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class EmailEventHandler {


    public static class OnRegistrationCompleteEvent extends ApplicationEvent{

        private final User user;

        public OnRegistrationCompleteEvent(User user) {
            super(user);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class OnVoucherRedeemCompleteEvent extends ApplicationEvent{

        private final Voucher voucher;

        public OnVoucherRedeemCompleteEvent(Voucher voucher) {
            super(voucher);
            this.voucher = voucher;
        }

        public Voucher getVoucher(){
            return voucher;
        }
    }

    public static class OnUnusedVouchersCompleteEvent extends ApplicationEvent{

        public OnUnusedVouchersCompleteEvent(Object source) {
            super(source);
        }
    }

    public static class OnFeedbackCompleteEvent extends ApplicationEvent{

        public OnFeedbackCompleteEvent(Object source) {
            super(source);
        }
    }

    public static class OnRateCompleteEvent extends ApplicationEvent{

        public OnRateCompleteEvent(String json) {
            super(json);
        }
    }

    public static class OnTroubleSubmitCompleteEvent extends ApplicationEvent{

        public OnTroubleSubmitCompleteEvent(Object source) {
            super(source);
        }
    }

    public static class OnAllVouchersUsedCompleteEvent extends ApplicationEvent{

        private final User user;

        public OnAllVouchersUsedCompleteEvent(User user) {
            super(user);
            this.user = user;
        }

        public User getUser(){ return user;}
    }



}
