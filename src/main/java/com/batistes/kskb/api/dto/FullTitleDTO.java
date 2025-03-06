package com.batistes.kskb.api.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullTitleDTO {
    private static final Logger logger = LoggerFactory.getLogger(FullTitleDTO.class);

    private String player;
    
    private String valueString;

    private String title;

    private String description;

    private String icon;

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
