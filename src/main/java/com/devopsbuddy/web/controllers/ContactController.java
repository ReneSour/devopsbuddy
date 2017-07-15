package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Created by Rene on 15/07/2017.
 */
@Controller
public class ContactController {
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
    
    public static final String FEEDBACK_MODEL_KEY="feedback";

    //folder / view name
    private static final String CONTACT_US_VIEW_NAME="contact/contact";

    @Autowired
    private EmailService emailService;


    @GetMapping (value="/contact")
    public String contactGet(ModelMap model){
        FeedbackPojo feedbackPojo= new FeedbackPojo();
        model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @PostMapping(value = "/contact")
    public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedback) {
        LOG.debug("Feedback POJO content:{}",feedback);
        emailService.sendFeedbackEmail(feedback);
        return ContactController.CONTACT_US_VIEW_NAME;
    }
}
