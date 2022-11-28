package com.example.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserRoleService {
    private final ApplicationUserRoleRepository applicationUserRoleRepository;

    @Autowired
    public ApplicationUserRoleService(ApplicationUserRoleRepository applicationUserRoleRepository) {
        this.applicationUserRoleRepository = applicationUserRoleRepository;
    }

    public ApplicationUserRole findApplicationUserRoleByRoleName(String roleName) {
        return this.applicationUserRoleRepository.findApplicationUserRoleByRoleName(roleName);
    }
}
