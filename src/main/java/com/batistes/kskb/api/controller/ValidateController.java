package com.batistes.kskb.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validation")
public class ValidateController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/token")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> validateToken() {
        
        logger.info("Validando token");

        return ResponseEntity.ok("Token");
    }
    
}
