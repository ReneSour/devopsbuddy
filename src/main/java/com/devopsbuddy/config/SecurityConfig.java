package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Rene on 15/07/2017.
 */
//WebSecurityConfigurerAdapter para aprovechar las funcionalidades de security
@Configuration
@EnableWebSecurity     //para activar spring security en nuestra aplicación MVC como x ejemplo ataques cross site (CSRF)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * URLs públicos
     */
    private static final String[] PUBLIC_MATCHERS = { //constant array que contiene todos los urls publicos
            "/webjars/**", //Este contiene las librerias de jquery y bootstrap que usamos en las lecciones pasadas
            "/css/**", //
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*"   //cualquier error que pueda ocurrir
    };

    //Configuramos el módulo de seguridad
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //autoriza todos los http requests
                .antMatchers(PUBLIC_MATCHERS).permitAll() //no es requerida la autenticación para PUBLIC_MATCHERS
                .anyRequest().authenticated() //autentica to do lo demas
                .and()
                 .formLogin().loginPage("/login").defaultSuccessUrl("/payload") // utiliza form login para autenticación, especificamente el que hemos creado nosotros
                .failureUrl("/login?error").permitAll() //ante una autenticación fallida hacer un request a/login con el parámetro error
                .and()
                .logout().permitAll(); //permite a cualquiera que haga logout sin pedir autenticación
    }

    //AuthenticationManagerBuilder ya es un Bean creado por Spring security
    //aquí lo configuramos convenientemente
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password") //configuramos los valores para el usuario y la contraseña
                .roles("USER");
    }


}
