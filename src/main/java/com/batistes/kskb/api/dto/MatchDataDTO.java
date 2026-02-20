package com.batistes.kskb.api.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class MatchDataDTO {
    
    private static final Logger logger = LoggerFactory.getLogger(MatchDataDTO.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private String checksum;
    
    private Date date;
    
    private String weekDay;
    
    private String map;
    
    private Double duration;

    private String durationString;
    
    private Integer teamAScore;
    
    private Integer teamBScore;
    
    private String result;
    
    private boolean overtime;

    private String demoFileName;

    private List<ChatMessageDTO> chatMessages;

    public MatchDataDTO(String checksum, Instant date, String map, Double duration, Integer teamAScore, Integer teamBScore, String result, boolean overtime, String demoFileName) {
        this.checksum = checksum;
        this.date = Date.from(date);
        this.map = map;
        this.duration = duration;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.result = result;
        this.overtime = overtime;
        this.demoFileName = demoFileName;
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
