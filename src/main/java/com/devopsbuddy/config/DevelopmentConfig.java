package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.MockEmailService;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.ServletRegistration;

/**
 * Created by Rene on 15/07/2017.
 */
@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/devopsbuddy/application-dev.properties.txt")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }
}
