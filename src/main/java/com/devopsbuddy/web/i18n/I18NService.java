package com.devopsbuddy.web.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Rene on 1/04/2017.
 */
@Service
public class I18NService {
    
    /** El logger de la aplicación */
    private static final Logger LOG = LoggerFactory.getLogger(I18NService.class);
    
    
    @Autowired
    private MessageSource messageSource;

    /*
    * Retorna un mensaje para el id del mensaje como parametor y la locación por defecto en
    * la sesion de contexto
    * */
    public String getMessage(String messageId){
        LOG.info("Devolviendo el texto i18n para messageId {} ", messageId);
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(messageId , locale );
    }

    /*
    * Retorna un mensaje para el id del mensaje como parametro
    * */
    public String getMessage(String messageId, Locale locale){
        return messageSource.getMessage(messageId,null,locale);
    }
}
