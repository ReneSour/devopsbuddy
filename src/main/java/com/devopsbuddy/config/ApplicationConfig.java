package com.devopsbuddy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Rene on 17/07/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.backend.persistence.repositories")
@EntityScan(basePackages = "com.devopsbuddy.backend.persistence.domain.backend") //para q Spring no busque en todos los paquetes por Entidades JPA
@EnableTransactionManagement //Habilita transacciones, en los repositorios JPA, las tx son gestionadas por defecto peeeerooo si tenemos un
//servicio que usa más de un repositorio y queremos q ejecute varias operaciones en una sola transacción entonces necesitamos utilizar anotaciones
//orientadas a la transaccionalidad
public class ApplicationConfig {

}
