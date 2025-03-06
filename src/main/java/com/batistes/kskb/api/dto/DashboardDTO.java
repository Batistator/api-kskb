package com.batistes.kskb.api.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class DashboardDTO {

    private static final Logger logger = LoggerFactory.getLogger(DashboardDTO.class);

    private Integer wins;
    private Integer losses;
    private Integer ties;
    private Double winRate;
    private Double noLosseRate;
    private String bestMap;
    private String worstMap;
    private String FavouriteMap;
    private Integer allWinsDays;
    private Integer allTiesDays;
    private Integer allLosesDays;
    private Integer maxWinStreak;
    private Integer maxLoseStreak;
    private Integer ActualWinStreak;
    private Integer noBestShinchanMatches;
    private Double noBestShinchanRate;
    private List<MapResultsDTO> resultsPerMap;

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
