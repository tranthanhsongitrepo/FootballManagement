package com.example.backend.user;

import com.example.backend.security.ApplicationUserPermission;
import com.example.backend.security.ApplicationUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "tbl_application_user_granted_role",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<ApplicationUserRole> roles = new ArrayList<>();
//    @OneToMany(cascade= {CascadeType.ALL})

    @ManyToMany
    @JoinTable(
            name = "tbl_application_user_granted_authority",
            joinColumns = @JoinColumn(name = "permissionId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    @ToString.Exclude
    private List<ApplicationUserPermission> authorities = new ArrayList<>();

    public User() {}

    public User(String username,
                String password,
                List<ApplicationUserPermission> authorities,
                List<ApplicationUserRole> roles) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map((authority) -> new SimpleGrantedAuthority(authority.getPermission())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRole(ApplicationUserRole role) {
        this.getRoles().add(role);
    }
}
