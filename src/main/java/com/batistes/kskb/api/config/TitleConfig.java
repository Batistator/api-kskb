package com.batistes.kskb.api.config;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "kskb.data")
@PropertySource(value = "classpath:config/titles.properties", encoding = "UTF-8")
public class TitleConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(TitleConfig.class);

    private Map<String, Title> titlesMap;

    public Map<String, Title> getTitlesMap() {
        return titlesMap;
    }

    public void setTitlesMap(Map<String, Title> titlesMap) {
        this.titlesMap = titlesMap;
    }

    @Data
    public static class Title {
        private String title;
        private String description;
        private String icon;
        private String unit;

        @Override
        public String toString() {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this);
            } catch (Exception e) {
                logger.error("Title - Error parsing JSON payload", e);
                return null;
            }
        }
    }
}
