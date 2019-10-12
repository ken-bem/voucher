package com.rumroute.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RateListener implements ApplicationListener<EmailEventHandler.OnRateCompleteEvent> {

    @Autowired
    private EmailHandler mail;



    @Override
    public void onApplicationEvent(EmailEventHandler.OnRateCompleteEvent event) {
        this.sendRating(event);
    }

    private void sendRating(EmailEventHandler.OnRateCompleteEvent event) {

        String json = event.toString();

    }


}
