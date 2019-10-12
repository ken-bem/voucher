package com.rumroute.email;

import com.rumroute.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VouchersCompleteListener implements ApplicationListener<EmailEventHandler.OnAllVouchersUsedCompleteEvent> {

    @Autowired
    private EmailHandler mail;
    
    @Override
    public void onApplicationEvent(EmailEventHandler.OnAllVouchersUsedCompleteEvent event) {

        try {
            this.sendThankYou(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendThankYou(EmailEventHandler.OnAllVouchersUsedCompleteEvent event) throws Exception {

        User user = event.getUser();
        mail.thankEmail(user);

    }


}
