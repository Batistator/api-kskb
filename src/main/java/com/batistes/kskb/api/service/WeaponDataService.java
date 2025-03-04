package com.batistes.kskb.api.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.dto.WeaponCounterDTO;
import com.batistes.kskb.api.dto.WeaponsDTO;
import com.batistes.kskb.api.entity.Kills;
import com.batistes.kskb.api.repository.KillsRepository;
import com.batistes.kskb.api.util.Constants;
import com.batistes.kskb.api.util.Utils;

@Service
public class WeaponDataService {
    
    @Autowired
    private KillsRepository killsRepository;

    @Autowired
    private Constants constants;

    public String getWeaponData(Date startDate, Date endDate){
    
        List<Kills> kills = killsRepository.findByKillerNameBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);

        Map<String, Map<String, Long>> killsByWeaponAndPlayer = kills.stream()
            .collect(Collectors.groupingBy(Kills::getWeaponName, 
                    Collectors.groupingBy(Kills::getKillerName, Collectors.counting())));

        // Convert the map to a list of WeaponsDTO
        List<WeaponsDTO> weaponsData = killsByWeaponAndPlayer.entrySet().stream()
            .map(weaponEntry -> {
                Map<String, Long> playerCounts = weaponEntry.getValue();
                return WeaponsDTO.builder().weapon(weaponEntry.getKey())
                .icon("/icons/weapons/weapon_"+weaponEntry.getKey()+".svg")
                .shinchan(playerCounts.getOrDefault(constants.PLAYER_SHINCHAN, 0L))
                .kazama(playerCounts.getOrDefault(constants.PLAYER_KAZAMA, 0L))
                .nene(playerCounts.getOrDefault(constants.PLAYER_NENE, 0L))
                .swagchan(playerCounts.getOrDefault(constants.PLAYER_SWAGCHAN, 0L))
                .mafios(playerCounts.getOrDefault(constants.PLAYER_MAFIOS, 0L))
                .build();
            })
            .collect(Collectors.toList());

        //Convertimos el LIST en un STRING JSON
        String result = Utils.convertToJSON(weaponsData);

        return result;
    }

    public String getWeaponCount(Date startDate, Date endDate){
    
        List<WeaponCounterDTO> weaponCounters = killsRepository.countDistinctWeaponKillsByPlayers(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);

        //Convertimos el LIST en un STRING JSON
        String result = Utils.convertToJSON(weaponCounters);

        return result;
    }
}