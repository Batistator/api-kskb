package com.batistes.kskb.api.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class ChatMessageDTO {
    
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageDTO.class);

    private String matchChecksum;

    private Integer tick;

    private String message;

    private String playerName;

    private boolean isPlayerAlive;

    private String playerSide;

    public ChatMessageDTO(String matchChecksum, Integer tick, String message, String playerName, boolean isPlayerAlive, String playerSide) {
        this.matchChecksum = matchChecksum;
        this.tick = tick;
        this.message = message;
        this.playerName = playerName;
        this.isPlayerAlive = isPlayerAlive;
        this.playerSide = playerSide;
    }

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
