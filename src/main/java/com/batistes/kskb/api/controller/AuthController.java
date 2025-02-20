package com.batistes.kskb.api.controller;

import com.batistes.kskb.api.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String payload, HttpServletResponse response) {
    // Extraer los datos del cuerpo de la petición
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, String> requestBody;
    try {
        requestBody = objectMapper.readValue(payload, new TypeReference<Map<String, String>>() {});
    } catch (Exception e) {
        logger.error("Error parsing JSON payload", e);
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid JSON payload"));
    }
    
    String username = requestBody.get("username");
    String password = requestBody.get("password");
    
    logger.info("Login user: " + username);
    Map<String, Object> authResponse = authService.authenticateUser(username, password);
    
    // Verificar si la autenticación fue exitosa
    if (authResponse.containsKey("error")) {
        return ResponseEntity.status(401).body(authResponse);
    }
    
    String jwtToken = (String) authResponse.get("token"); // Obtenemos el token JWT

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("message", "Login successful");
    responseBody.put("token", jwtToken);
    
    // Opcionalmente, puedes incluir información adicional del usuario
    if (authResponse.containsKey("user")) {
        responseBody.put("user", authResponse.get("user"));
    }
    
    return ResponseEntity.ok(responseBody);
}

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        logger.info("Registering user: " + username);
        Map<String, Object> response = new HashMap<>();
        try {
            response = authService.registerUser(username, email, password);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            response.put("Error", "Registration failed.");
            return ResponseEntity.internalServerError().body(response);
        }
        
        if(response.containsKey("Error")) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
    
}
