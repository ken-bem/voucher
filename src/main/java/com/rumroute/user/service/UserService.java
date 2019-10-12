package com.rumroute.user.service;

import com.rumroute.model.user.User;
import com.rumroute.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getConfirmPassword()));

        return userRepository.save(user);
    }

    public User save(User user){

        user.setPassword(passwordEncoder.encode(user.getConfirmPassword()));

        return userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
