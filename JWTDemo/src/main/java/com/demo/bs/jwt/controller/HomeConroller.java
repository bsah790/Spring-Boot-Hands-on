package com.demo.bs.jwt.controller;

import com.demo.bs.jwt.model.JwtRequest;
import com.demo.bs.jwt.model.JwtResponse;
import com.demo.bs.jwt.service.UserService;
import com.demo.bs.jwt.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeConroller {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/greet")
    public String greetings() {
        return  "Welcome to Spring Security implementation!!";
    }

    @GetMapping("/")
    public String sayHello() {
        return  "Hello Mr Guru !!";
    }
    @GetMapping("/home")
    public String home() {
        return  "Welcome to Spring Security implementation!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()));
        } catch(BadCredentialsException e) {
            throw new Exception(("INVALID_CREDENTIAL"));
        }

        final UserDetails userDetails = userService.loadUserByUsername(request.getUserName());
        String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

}
