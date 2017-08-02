package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.enums.RolesEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rene on 16/07/2017.
 */
@Entity
public class Role implements Serializable {
    /** UID para clases serializables */
    private static final long serialVersionUID = 1L;

    public Role() {

    }

    /**
     * Full constructor
     * @param rolesEnum
     */
    public Role(RolesEnum rolesEnum) {
        this.id=rolesEnum.getId();
        this.name = rolesEnum.getRoleName();
    }
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
