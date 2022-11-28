package com.example.backend.security;

import com.example.backend.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
public class ApplicationUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    private String roleName;
    @ManyToMany
    @JoinTable(
            name = "tbl_role_granted_authorities",
            joinColumns = @JoinColumn(name = "permissionId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<ApplicationUserPermission> rolePermissions;

    @ManyToMany(mappedBy = "roles")
    private List<User> usersWithRole;
}
