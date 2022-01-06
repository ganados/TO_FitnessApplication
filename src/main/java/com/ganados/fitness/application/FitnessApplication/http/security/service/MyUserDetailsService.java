package com.ganados.fitness.application.FitnessApplication.http.security.service;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final DatabaseService databaseService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = databaseService.getUser(username);
        if (user == null) {
            log.warning("User " + username + " not found");
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
