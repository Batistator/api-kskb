package com.batistes.kskb.api.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.batistes.kskb.api.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TitleDTO {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private String player;
    
    private Object value;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            logger.error("Error parsing JSON payload", e);
            return null;
        }
    }
}
