package com.batistes.kskb.api.service;

import com.batistes.kskb.api.entity.User;
import com.batistes.kskb.api.repository.UserRepository;
import com.batistes.kskb.api.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public Map<String, Object> authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }

    public Map<String, Object> registerUser(String username, String email, String password) {
        Map<String, Object> response = new HashMap<>();
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.put("Error", "All fields are required!");
            return response;
        }
        
        if (userRepository.existsByUsername(username)) {
            response.put("Error", "Username already in use!");
            return response;
        }

        if (userRepository.existsByEmail(email)) {
            response.put("Error", "Email already in use!");
            return response;
        }

        if (!username.equals("Nene") && !username.equals("ShinChan") && !username.equals("The Mafios") && !username.equals("Kazama") && !username.equals("Swagchan")) {
            response.put("Error", "User no recognized!");
            return response;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encoder.encode(password)); // Encriptar la contraseña

        userRepository.save(user);
        
        response.put("message", "User registered successfully!");
        return response;
    }
}