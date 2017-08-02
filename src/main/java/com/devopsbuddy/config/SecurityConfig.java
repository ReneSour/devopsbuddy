package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rene on 15/07/2017.
 */
//WebSecurityConfigurerAdapter para aprovechar las funcionalidades de security
@Configuration
@EnableWebSecurity     //para activar spring security en nuestra aplicación MVC como x ejemplo ataques cross site (CSRF)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    //Spring hace esta variable disponible por defecto
    @Autowired
    private Environment env;

    /**
     * Una vez definido nunca debe ser cambiado
     * tmbien si consideras usar este proyecto en producción es importante que SALT no sea compartido con naide
     * en un negocio comercial esto seguro es almacenado en una DB
     */
    private static final String SALT = "SDkjoaisjdfoi120fff";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // strenght es por defecto 10, en este caso le estamos poniendo 12
        //mientras mas largo el strength mas trabajo se tiene q hacer exponencialmente para Hashear el password!
        //hay q encontrar el balance entre velocidad y strength

        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

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
            "/error/**/*",   //cualquier error que pueda ocurrir
            "/console/**"   //para habilitar la consola de H2 que ya viene out of the box!
    };

    //Configuramos el módulo de seguridad
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles()); //lsita de todos los profiles activos
        if (activeProfiles.contains("dev")) {
            http.csrf().disable(); //para q funcione H2 correctamente
            http.headers().frameOptions().disable();
        }

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
//                .inMemoryAuthentication()
//                .withUser("user").password("password") //configuramos los valores para el usuario y la contraseña
//                .roles("USER");
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }


}
