package com.example.backend.security;

import com.example.backend.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_application_user_permission")
@Getter
@Setter
public class ApplicationUserPermission {
    @Id
    private Long applicationPermissionId;
    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private List<User> user = new ArrayList<>();

    @ManyToMany(mappedBy = "rolePermissions")
    private List<ApplicationUserRole> role = new ArrayList<>();

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public ApplicationUserPermission() {

    }

    public String getPermission() {
        return permission;
    }

    public void setId(Long id) {
        this.applicationPermissionId = id;
    }

    public Long getId() {
        return applicationPermissionId;
    }

}
