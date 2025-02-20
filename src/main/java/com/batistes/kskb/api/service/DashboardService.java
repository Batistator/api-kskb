package com.batistes.kskb.api.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.repository.MatchesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.batistes.kskb.api.controller.AuthController;
import com.batistes.kskb.api.dto.DashboardDTO;
import com.batistes.kskb.api.dto.MapResultsDTO;
import com.batistes.kskb.api.dto.MatchDataDTO;

@Service
public class DashboardService {

    @Autowired
    private MatchesRepository matchesRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public String getDashboardData(Date startDate, Date endDate){

        final List<MatchDataDTO> matchList = matchesRepository.getMatchList(convertUtilDateToSqlDate(startDate), convertUtilDateToSqlDate(endDate));
        
        final Integer wins = countMatchResult(matchList, "Victoria");
        final Integer loses = countMatchResult(matchList, "Derrota");
        final Integer ties = countMatchResult(matchList, "Empate");
        final Integer noBestShinchanMatches = matchesRepository.findMatchesNoFirst(convertUtilDateToSqlDate(startDate), convertUtilDateToSqlDate(endDate), "ShinChan");
        final Integer totalMatches = matchList.size();
        Double winRate = 0.0;
        Double noLoseRate = 0.0;
        Double noBestShinchanRate = 0.0;

        if(totalMatches > 0)    {
            winRate = 0.0 + (wins * 100) / totalMatches;
            noLoseRate = 0.0 + ((wins + ties) * 100) / totalMatches;
            noBestShinchanRate = 0.0 + (noBestShinchanMatches * 100) / totalMatches;
        }

        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.setWins(wins);
        dashboardDTO.setLosses(loses);
        dashboardDTO.setTies(ties);
        dashboardDTO.setWinRate(winRate);
        dashboardDTO.setNoLosseRate(noLoseRate);
        dashboardDTO.setFavouriteMap(getFavouriteMap(matchList));
        dashboardDTO.setBestMap(getBestMap(matchList));
        dashboardDTO.setWorstMap(getWorstMap(matchList));
        dashboardDTO.setAllWinsDays(getDaysOf(matchList, "Victoria"));
        dashboardDTO.setAllTiesDays(getDaysOf(matchList, "Empate"));
        dashboardDTO.setAllLosesDays(getDaysOf(matchList, "Derrota"));
        dashboardDTO.setMaxWinStreak(getMaxStreakOf(matchList, "Victoria"));
        dashboardDTO.setMaxLoseStreak(getMaxStreakOf(matchList, "Derrota"));
        dashboardDTO.setActualWinStreak(getCurrentStreakOf(matchList, "Victoria"));
        dashboardDTO.setNoBestShinchanMatches(noBestShinchanMatches);
        dashboardDTO.setNoBestShinchanRate(noBestShinchanRate);

        dashboardDTO.setResultsPerMap(classifyResultsByMap(matchList));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dashboardDTO);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            return null;
        }
    }

    public String getMatchListData(Date startDate, Date endDate){
        final List<MatchDataDTO> matchList = matchesRepository.getMatchList(convertUtilDateToSqlDate(startDate), convertUtilDateToSqlDate(endDate));

        calculateWeekDay(matchList);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(matchList);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            return null;
        }
    }

    private static List<MapResultsDTO> classifyResultsByMap(List<MatchDataDTO> matches) {
        return matches.stream()
            .collect(Collectors.groupingBy(MatchDataDTO::getMap,
                Collectors.collectingAndThen(Collectors.toList(), matchList -> {
                    MapResultsDTO result = MapResultsDTO.builder()
                        .mapName(matchList.get(0).getMap())
                        .wins((int) matchList.stream().filter(m -> "Victoria".equals(m.getResult())).count())
                        .losses((int) matchList.stream().filter(m -> "Derrota".equals(m.getResult())).count())
                        .ties((int) matchList.stream().filter(m -> "Empate".equals(m.getResult())).count())
                        .build();
                    return result;
                })))
            .values().stream()
            .collect(Collectors.toList());
    }

    private static Integer countMatchResult(List<MatchDataDTO> matchDataList, String matchResult) {
        return (int) matchDataList.stream()
            .filter(match -> matchResult.equals(match.getResult()))
            .count();
    }

    private static String getFavouriteMap(List<MatchDataDTO> matchList){
        return matchList.stream()
                .collect(Collectors.groupingBy(MatchDataDTO::getMap, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static String getBestMap(List<MatchDataDTO> matchList){
        return matchList.stream()
                .collect(Collectors.groupingBy(MatchDataDTO::getMap, 
                    Collectors.averagingDouble(match -> "Victoria".equals(match.getResult()) ? 1 : 0)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static String getWorstMap(List<MatchDataDTO> matchList){
        return matchList.stream()
                .collect(Collectors.groupingBy(MatchDataDTO::getMap, 
                    Collectors.averagingDouble(match -> "Derrota".equals(match.getResult()) ? 1 : 0)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
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

    private static Integer getDaysOf(List<MatchDataDTO> matchList, String matchResult){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        
        Map<String, List<MatchDataDTO>> matchesByDay = matchList.stream()
                .map(match -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(match.getDate());
                    cal.add(Calendar.HOUR, -3); // Restar 3 horas
                    match.setDate(cal.getTime());
                    return match;
                })
                .collect(Collectors.groupingBy(match -> sdf.format(match.getDate())));
        
        return (int) matchesByDay.values().stream()
                .filter(matches -> matches.stream().allMatch(match -> matchResult.equals(match.getResult())))
                .count();
    }

    private static Integer getMaxStreakOf(List<MatchDataDTO> matchList, String matchResult){
        // Ordenar la lista por fecha ajustada
        matchList.sort((m1, m2) -> {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(m1.getDate());
            cal1.add(Calendar.HOUR, -3);
            Date date1 = cal1.getTime();

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(m2.getDate());
            cal2.add(Calendar.HOUR, -3);
            Date date2 = cal2.getTime();

            return date1.compareTo(date2);
        });

        int maxStreak = 0;
        int currentStreak = 0;

        for (MatchDataDTO match : matchList) {
            if (matchResult.equals(match.getResult())) {
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }
        return maxStreak;
    }

    private static Integer getCurrentStreakOf(List<MatchDataDTO> matchList, String matchResult){
        // Ordenar la lista por fecha ajustada
        matchList.sort((m1, m2) -> {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(m1.getDate());
            cal1.add(Calendar.HOUR, -3);
            Date date1 = cal1.getTime();

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(m2.getDate());
            cal2.add(Calendar.HOUR, -3);
            Date date2 = cal2.getTime();

            return date1.compareTo(date2);
        });

        int currentStreak = 0;

        for (int i = matchList.size() - 1; i >= 0; i--) {
            MatchDataDTO match = matchList.get(i);
            if ("Victoria".equals(match.getResult())) {
                currentStreak++;
            } else {
                break;
            }
        }

        return currentStreak;
    }

    private static java.sql.Date convertUtilDateToSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}