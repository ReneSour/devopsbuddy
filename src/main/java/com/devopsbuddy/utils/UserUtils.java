package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

/**
 * Created by Rene on 17/07/2017.
 */
public class UserUtils {

    //No queremos que esta clase sea instanciable
    private UserUtils() {
        throw new AssertionError("Clase no instanciable");
    }

    public static User createBasicUser() {
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("changeit");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }
}
