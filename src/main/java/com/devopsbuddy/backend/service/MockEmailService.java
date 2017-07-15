package com.devopsbuddy.backend.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Rene on 15/07/2017.
 */
public class MockEmailService extends AbstractEmailService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage simpleMessage) {
        LOG.debug("Simulando un servicio de correo...");
        LOG.info(simpleMessage.toString());
        LOG.debug("Correo enviado.");
    }
}
