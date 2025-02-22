package com.batistes.kskb.api.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.controller.AuthController;
import com.batistes.kskb.api.dto.MatchDataDTO;
import com.batistes.kskb.api.repository.MatchesRepository;
import com.batistes.kskb.api.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatchListService {

    @Autowired
    private MatchesRepository matchesRepository;

    public String getMatchListData(Date startDate, Date endDate){
        final Logger logger = LoggerFactory.getLogger(AuthController.class);
        final List<MatchDataDTO> matchList = matchesRepository.getMatchList(Utils.convertUtilDateToSqlDate(startDate), Utils.convertUtilDateToSqlDate(endDate));

        calculateWeekDay(matchList);
        calculateDuration(matchList);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(matchList);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            return null;
        }
    }

    private static void calculateWeekDay(List<MatchDataDTO> matchList) {
        Locale locale = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", locale);
        
        matchList.forEach(match -> {
            Date date = match.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -3);
            Date dateMinus3Hours = cal.getTime();

            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Madrid")); // Ajustar a la zona horaria adecuada si es necesario
            String weekDay = sdf.format(dateMinus3Hours);

            match.setWeekDay(weekDay);
        });
    }

    private static void calculateDuration(List<MatchDataDTO> matchList) {
        matchList.forEach(match -> {
            Double duration = match.getDuration();
            if (duration != null) {
                int minutes = (int) Math.floor(duration / 60);
                int seconds = (int) Math.floor(duration % 60);
                String minutesString = String.format("%02d", minutes);
                String secondsString = String.format("%02d", seconds);
                match.setDurationString(minutesString + ":" + secondsString);
            }
        });
    }
}
