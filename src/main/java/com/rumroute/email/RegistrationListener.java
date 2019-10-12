package com.rumroute.email;

import com.rumroute.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<EmailEventHandler.OnRegistrationCompleteEvent> {

    @Autowired
    private EmailHandler mail;

    @Override
    public void onApplicationEvent(EmailEventHandler.OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(EmailEventHandler.OnRegistrationCompleteEvent event) throws Exception {
        User user = event.getUser();
        mail.registrationHandler(user);

    }


}