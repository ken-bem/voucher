package com.rumroute.user.service;

import com.rumroute.model.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Load user by email also.
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(curr ->{
                    Set<GrantedAuthority> authorities = new HashSet<>();

                    authorities.add(new SimpleGrantedAuthority(curr.getRole().getName()));

                    return new org.springframework
                            .security
                            .core
                            .userdetails
                            .User(curr.getUsername(), curr.getPassword(), authorities);
                })
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }

}
