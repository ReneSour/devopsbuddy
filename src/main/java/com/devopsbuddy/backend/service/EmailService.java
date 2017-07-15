package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;


/**
 * Created by Rene on 15/07/2017.
 */
public interface EmailService {
    /**
     * Envía un correo con el contenido en un Pojo Feedback
     * @param feedbackPojo el Pojo feedback
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /**
     * Envía un email con el contenido de un objeto de correo simple
     * @param simpleMessage El objecto conteniendo el contenido del correo
     */
    public void sendGenericEmailMessage(SimpleMailMessage simpleMessage);
}
