package com.batistes.kskb.api.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.batistes.kskb.api.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class TotalDataDTO {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    private String data;
    
    private String icon;
    
    private String shinchan;

    private String kazama;
    
    private String nene;
    
    private String swagchan;
    
    private String mafios;

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
