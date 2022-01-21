package com.demo.bs.jwt.service;

import com.demo.bs.jwt.model.CustomAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<CustomAuthority> authorities = new ArrayList<>();
        authorities.add(new CustomAuthority("ADMIN"));
        return new User("admin", "pass", authorities);
    }
}
