package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static com.devopsbuddy.utils.UserUtils.createBasicUser;

/**
 * Created by Rene on 16/07/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= DevopsbuddyApplication.class)
public class RepositoriesIntegrationTest {
    @Autowired
    private PlanRepository planRepository; // no sé x q marc como no encontrado pero al ejecutar los TESTS si funciona y no devuelve nulo

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    //private static final int BASIC_PLAN_ID = 1;
    //private static final int BASIC_ROLE_ID = 1;

    @Before
    public void init() {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);

    }

    @Test
    public void testCreateNewPlan()throws Exception {
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole()throws Exception {
        Role basicRole = createBasicRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);
        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }

    //@Test
    public void testCreateNewUserWithOneRole() throws Exception{

        Role basicRole = createBasicRole(RolesEnum.BASIC);
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);

        planRepository.save(basicPlan);
        roleRepository.save(basicRole);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        UserRole userRole = new UserRole( basicUser,basicRole);
//        userRole.setRole(basicRole);
//        userRole.setUser(basicUser);


        basicUser.getUserRoles().add(userRole);

        userRepository.save(basicUser);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> rolesFromNewlyUser = newlyCreatedUser.getUserRoles();
        for (UserRole ur :rolesFromNewlyUser ) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }
    }

    @Test
    public void testCreateNewUser() throws Exception{
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole(RolesEnum.ADMIN);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser,basicRole);
//        userRole.setUser(basicUser);
//        userRole.setRole(basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole u : userRoles) {
            roleRepository.save(u.getRole());
        }

        userRepository.save(basicUser);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> rolesFromNewlyUser = newlyCreatedUser.getUserRoles();
        for (UserRole ur :rolesFromNewlyUser ) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }


    }

    // Métodos privados
    private Plan createBasicPlan(PlansEnum plansEnum) {
        return new Plan(plansEnum);
    }

    private Role createBasicRole(RolesEnum rolesEnum) {
        return new Role(rolesEnum);
    }


}
