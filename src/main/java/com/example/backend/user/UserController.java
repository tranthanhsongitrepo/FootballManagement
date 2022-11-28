package com.example.backend.user;

import com.example.backend.security.ApplicationUserPermission;
import com.example.backend.security.ApplicationUserRole;
import com.example.backend.security.ApplicationUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api/v1/auth/users")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private ApplicationUserRoleService applicationUserRoleService;
    public UserController() {
    }

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, ApplicationUserRoleService applicationUserRoleService) {
        this.authenticationManager = authenticationManager;
        this.applicationUserRoleService = applicationUserRoleService;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Boolean> login(HttpServletRequest req, @RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication auth = authenticationManager.authenticate(authenticationToken);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody User user) {
        // TODO: Verify the user objects
        ApplicationUserRole role = applicationUserRoleService.findApplicationUserRoleByRoleName("role:user");
        List<ApplicationUserPermission> applicationUserPermissions = role.getRolePermissions();

        user.addRole(role);
        user.setAuthorities(new ArrayList<>(applicationUserPermissions));
        userService.save(user);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
