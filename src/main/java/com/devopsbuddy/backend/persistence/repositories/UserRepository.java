package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rene on 16/07/2017.
 */
//CrudRepository es un genérico, x eso el Key Value es el tipo de entidad, y el value es el tipo del Key de la entidad
@Repository //para q Spring traduzca las excepciones de "resources" a Spring data access exceptions
public interface UserRepository extends CrudRepository <User, Long>{
    /**
     * Retorna un usuario según un username o nulo si no lo encuentra
     * @param username
     * @return
     */
    public User findByUsername(String username);

}
