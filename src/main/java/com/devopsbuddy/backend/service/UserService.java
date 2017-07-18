package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Rene on 17/07/2017.
 */

//mejor practica cuando usas JPA, x defecto debemos usar readOnly para minimizar login, y para metodos q actualizan o crean
//usamos transactional sin argumentos q define una operación transaccional
//cuando colocamos transactional en el service, la transaccionalidad del service sobreescribe al del JPA repository
@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {

        Plan plan = new Plan(plansEnum);
        //verificamos que el plan existe en la BD!
        if (!planRepository.exists(plansEnum.getId())) {
            planRepository.save(plan);
        }

        //verificamos que el rol existan?
        for (UserRole userRole : userRoles) {
            if (!roleRepository.exists(userRole.getRole().getId())) {
                roleRepository.save(userRole.getRole());
            }
        }

        user.setPlan(plan);
        user.getUserRoles().addAll(userRoles);
        userRepository.save(user);

        return user;
    }

}
