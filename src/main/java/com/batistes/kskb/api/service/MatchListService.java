package com.batistes.kskb.api.service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.batistes.kskb.api.dto.ChatMessageDTO;
import com.batistes.kskb.api.repository.ChatMessagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.dto.MatchDataDTO;
import com.batistes.kskb.api.repository.MatchesRepository;
import com.batistes.kskb.api.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatchListService {

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private ChatMessagesRepository chatMessagesRepository;

    public String getMatchListData(Date startDate, Date endDate){
        final Logger logger = LoggerFactory.getLogger(MatchListService.class);
        final List<MatchDataDTO> matchList = matchesRepository.getMatchList(Utils.convertUtilDateToSqlDate(startDate), Utils.convertUtilDateToSqlDate(endDate));

        calculateWeekDay(matchList);
        calculateDuration(matchList);
        getChatMessages(matchList);

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

    private void getChatMessages(List<MatchDataDTO> matchList) {

        List<String> matchChecksumList = matchList.stream().map(MatchDataDTO::getChecksum).toList();
        List<ChatMessageDTO> chatMessagesList = chatMessagesRepository.findMessagesByMatches(matchChecksumList);

        // Agrupa en un Mapa por matchChecksum y además ordena los mensajes por Tick
        Map<String, List<ChatMessageDTO>> groupedMessages = chatMessagesList.stream()
                .collect(Collectors.groupingBy(
                        ChatMessageDTO::getMatchChecksum,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(ChatMessageDTO::getTick))
                                        .collect(Collectors.toList())
                        )
                ));

        matchList.forEach(match -> {
            List<ChatMessageDTO> messages = groupedMessages.get(match.getChecksum());
            if(messages != null) {
                match.setChatMessages(messages);
            }
        });
    }
}
