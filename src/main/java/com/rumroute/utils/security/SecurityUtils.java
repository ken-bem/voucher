package com.rumroute.utils.security;
import com.rumroute.model.role.Role;
import com.rumroute.model.role.RoleRepository;
import com.rumroute.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public final class SecurityUtils {

    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;

    public static Optional<User> getCurrentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(context.getAuthentication())
                .map(authentication -> {
                    if(authentication.getPrincipal() instanceof UserDetails){
                        return (User) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    public static Optional<String> getCurrentUserLogin(){
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(context.getAuthentication().getPrincipal())
                .map(principal -> {
                    if(principal instanceof UserDetails){
                        UserDetails springSecurityUser = (UserDetails) principal;
                        return Optional.of(springSecurityUser.getUsername());
                    }else if (principal instanceof String){
                        return Optional.of(principal.toString());
                    }
                    return Optional.<String>empty();
                })
                .orElse(Optional.empty());
    }

    public static boolean isAuthenticated(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> authentication.getAuthorities().stream()
                        .noneMatch(
                                grantedAuthority -> grantedAuthority
                                        .getAuthority()
                                        .equals(AuthenticationConstants.ANONYMOUS)
                        ))
                .orElse(false);
    }

    public static boolean isCurrentUserInRole(String authority){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(securityContext.getAuthentication())
                .map(authentication -> authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(grantedAuthority -> grantedAuthority
                                .getAuthority()
                                .equals(authority)))
                .orElse(false);
    }

    public List<String> getAuthorities(){
        return roleRepository
                .findAll()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }



}