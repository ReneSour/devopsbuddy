package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication

public class DevopsbuddyApplication implements CommandLineRunner{
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		User user = UserUtils.createBasicUser();
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.BASIC)));
		LOG.debug("Creando un usuario con username {}",user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("Usuario {} creado" + user.getUsername());
	}
}
