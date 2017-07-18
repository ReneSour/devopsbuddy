package com.devopsbuddy.enums;

/**
 * Created by Rene on 16/07/2017.
 */
public enum RolesEnum {

    //En Spring Security cada role va por defecto con el prefijo ROLE
    BASIC(1, "ROLE_BASIC"),
    PRO(2, "ROLE_PRO"),
    ADMIN(3,"ROLE_ADMIN");

    private final int id;

    private final String roleName;

    RolesEnum(int id, String roleName) {
        this.id=id;
        this.roleName=roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
