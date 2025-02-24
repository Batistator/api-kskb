package com.batistes.kskb.api.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.controller.AuthController;
import com.batistes.kskb.api.dto.TotalDataDTO;
import com.batistes.kskb.api.entity.BombsDefused;
import com.batistes.kskb.api.entity.BombsPlanted;
import com.batistes.kskb.api.entity.ChickenDeaths;
import com.batistes.kskb.api.entity.Damages;
import com.batistes.kskb.api.entity.GrenadeBounces;
import com.batistes.kskb.api.entity.GrenadeProjectilesDestroy;
import com.batistes.kskb.api.entity.Kills;
import com.batistes.kskb.api.entity.PlayerBlinds;
import com.batistes.kskb.api.entity.PlayerEconomies;
import com.batistes.kskb.api.entity.Players;
import com.batistes.kskb.api.entity.Shots;
import com.batistes.kskb.api.repository.BombsDefusedRepository;
import com.batistes.kskb.api.repository.BombsPlantedRepository;
import com.batistes.kskb.api.repository.ChickenDeathsRepository;
import com.batistes.kskb.api.repository.DamagesRepository;
import com.batistes.kskb.api.repository.GrenadeBouncesRepository;
import com.batistes.kskb.api.repository.GrenadeProjectilesDestroyRepository;
import com.batistes.kskb.api.repository.KillsRepository;
import com.batistes.kskb.api.repository.PlayerBlindsRepository;
import com.batistes.kskb.api.repository.PlayerEconomiesRepository;
import com.batistes.kskb.api.repository.PlayersRepository;
import com.batistes.kskb.api.repository.ShotsRepository;
import com.batistes.kskb.api.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TotalDataService {
    @Autowired
    private GrenadeProjectilesDestroyRepository grenadeProjectilesDestroyRepository;
    
    @Autowired
    private BombsDefusedRepository bombsDefusedRepository;

    @Autowired
    private BombsPlantedRepository bombsPlantedRepository;

    @Autowired
    private KillsRepository killsRepository;

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private PlayerEconomiesRepository playerEconomiesRepository;

    @Autowired
    private ShotsRepository shotsRepository;

    @Autowired
    private Constants constants;

    @Autowired
    private ChickenDeathsRepository chickenDeathsRepository;

    @Autowired
    private PlayerBlindsRepository playerBlindsRepository;

    @Autowired
    private DamagesRepository damagesRepository;

    @Autowired
    private GrenadeBouncesRepository grenadeBouncesRepository;

    public String getTotalData(Date startDate, Date endDate){

        final Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        logger.info("Data retrieve start");
        List<Players> players = playersRepository.findByNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));

        logger.info("Players data Finished");
        List<Kills> kills = killsRepository.findByKillerNameOrAssisterNameOrVictimNameBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);

        logger.info("Kills data Finished");        
        List<BombsDefused> bombsDefused = bombsDefusedRepository.findByDefuserNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
        
        logger.info("Bombs defused data Finished");     
        List<BombsPlanted> bombsPlanted = bombsPlantedRepository.findByPlanterNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
        
        logger.info("Bombs planted data Finished");
        List<GrenadeProjectilesDestroy> grenades = grenadeProjectilesDestroyRepository.findByThrowerNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
        
        logger.info("Grenade projectile destroy data Finished");
        List<PlayerEconomies> playerEconomies = playerEconomiesRepository.findByPlayerNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
  
        logger.info("Player economies data Finished");  
        List<Shots> shots = shotsRepository.findByPlayerNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
        
        logger.info("Shots data Finished");
        List<ChickenDeaths> chickenDeaths = chickenDeathsRepository.findByKillerSteamIdIn(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID));
        
        logger.info("Chicken deaths data Finished");        
        List<PlayerBlinds> playerBlinds = playerBlindsRepository.findByKillerNameOrAssisterNameOrVictimName(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID));
        
        logger.info("Player blinds data Finished");
        List<Damages> damages = damagesRepository.findByAttackerSteamIdOrVictimSteamIdIn(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID));
        
        logger.info("Damages data Finished");
        List<GrenadeBounces> grenadeBounces = grenadeBouncesRepository.findByThrowerNameIn(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN));
        logger.info("Grenade bounces data Finished");

        logger.info("SQL Finished");

        final Map<String, TotalDataDTO> totalData = new HashMap<>();

        totalData.putAll(filterKills(kills));

        
        String jsonResult = "";
        try {
            jsonResult = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(totalData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error de conversión JSON";
        }

        return jsonResult;
    }

    private Map<String, TotalDataDTO> filterKills (List<Kills> totalKills){
        
        Map<String, TotalDataDTO> killsMap = new HashMap<>();
        
        TotalDataDTO kills = new TotalDataDTO();
        kills.setIcon("iconKills"); 
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SHINCHAN.equals(kill.getVictimName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && !constants.PLAYER_KAZAMA.equals(kill.getVictimName()))
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && !constants.PLAYER_MAFIOS.equals(kill.getVictimName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && !constants.PLAYER_NENE.equals(kill.getVictimName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SWAGCHAN.equals(kill.getVictimName()))
            .count()));
        killsMap.put("kills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconAssists");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getAssisterName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()))
            .count()));        
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getAssisterName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getAssisterName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getAssisterName()))
            .count()));
        killsMap.put("assists", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconDeaths");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getVictimName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getVictimName()))
            .count()));        
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getVictimName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getVictimName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getVictimName()))
            .count()));
        killsMap.put("deaths", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconHeadshots");        
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && kill.isHeadshot())
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && kill.isHeadshot())
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && kill.isHeadshot())
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && kill.isHeadshot())
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && kill.isHeadshot())
            .count()));
        killsMap.put("headshots", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconHeadshotRatio");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("headshots").getShinchan()) / Double.valueOf(killsMap.get("kills").getShinchan())) * 100
        ));
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("headshots").getKazama()) / Double.valueOf(killsMap.get("kills").getKazama())) * 100
        ));
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("headshots").getMafios()) / Double.valueOf(killsMap.get("kills").getMafios())) * 100
        ));
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("headshots").getNene()) / Double.valueOf(killsMap.get("kills").getNene())) * 100
        ));
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("headshots").getSwagchan()) / Double.valueOf(killsMap.get("kills").getSwagchan())) * 100
        ));
        killsMap.put("headshotRatio", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconTeamKills");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getVictimName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(kill.getVictimName()))
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(kill.getVictimName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getVictimName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getVictimName()))
            .count()));
        killsMap.put("teamKills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconAssisstedFlash");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getAssisterName()) && kill.isAssistedFlash() &&
            Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getKillerName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getAssisterName()) && kill.isAssistedFlash() &&
            Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(kill.getKillerName()))
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getAssisterName()) && kill.isAssistedFlash() &&
            Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(kill.getKillerName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getAssisterName()) && kill.isAssistedFlash() &&
            Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getKillerName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getAssisterName()) && kill.isAssistedFlash() &&
            Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(kill.getKillerName()))
            .count()));
        killsMap.put("assisstedFlash", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconBlindedKills");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && kill.isKillerBlinded())
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && kill.isKillerBlinded())
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && kill.isKillerBlinded())
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && kill.isKillerBlinded())
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && kill.isKillerBlinded())
            .count()));
        killsMap.put("blindedKills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconC4Deaths");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getVictimName()) && "C4".equals(kill.getWeaponName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getVictimName()) && "C4".equals(kill.getWeaponName()))
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getVictimName()) && "C4".equals(kill.getWeaponName()))
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getVictimName()) && "C4".equals(kill.getWeaponName()))
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getVictimName()) && "C4".equals(kill.getWeaponName()))
            .count()));
        killsMap.put("c4Deaths", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconCTKills");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SHINCHAN.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 2)
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && !constants.PLAYER_KAZAMA.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 2)
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && !constants.PLAYER_MAFIOS.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 2)
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && !constants.PLAYER_NENE.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 2)
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SWAGCHAN.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 2)
            .count()));
        killsMap.put("ctKills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("iconTKills");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SHINCHAN.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 3)
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getKillerName()) && !constants.PLAYER_KAZAMA.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 3)
            .count()));
        kills.setMafios(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_MAFIOS.equals(kill.getKillerName()) && !constants.PLAYER_MAFIOS.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 3)
            .count()));
        kills.setNene(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_NENE.equals(kill.getKillerName()) && !constants.PLAYER_NENE.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 3)
            .count()));
        kills.setSwagchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SWAGCHAN.equals(kill.getKillerName()) && !constants.PLAYER_SWAGCHAN.equals(kill.getVictimName()) &&
            kill.getKillerSide() == 3)
            .count()));
        killsMap.put("tKills", kills);
        
        return killsMap;
    }

}
