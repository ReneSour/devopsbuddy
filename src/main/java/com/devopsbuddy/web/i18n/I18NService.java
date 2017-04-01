package com.devopsbuddy.web.i18n;

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
    @Autowired
    private MessageSource messageSource;

    /*
    * Retorna un mensaje para el id del mensaje como parametor y la locación por defecto en
    * la sesion de contexto
    * */
    public String getMessage(String messageId){
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
