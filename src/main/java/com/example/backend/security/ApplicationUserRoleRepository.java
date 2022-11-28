package com.example.backend.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {
    ApplicationUserRole findApplicationUserRoleByRoleName(String roleName);
}
