package com.devopsbuddy.backend.persistence.domain.backend;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rene on 16/07/2017.
 */
@Entity
@Table(name="user_role")
public class UserRole implements Serializable{
    /** UID para clases serializables */
    private static final long serialVersionUID = 1L;

    public UserRole() {

    }

    public UserRole(User user, Role role) {
        this.user=user;
        this.role=role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //si le pones EAGER y quieres borrar un usuario sale error de FOREIGN KEY CONSTRAINT
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //si le pones EAGER y quieres borrar un usuario sale error de FOREIGN KEY CONSTRAINT
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return id == userRole.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
