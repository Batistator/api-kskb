package com.batistes.kskb.api.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.dto.TotalDataDTO;
import com.batistes.kskb.api.entity.BombsDefused;
import com.batistes.kskb.api.entity.BombsPlanted;
import com.batistes.kskb.api.entity.ChickenDeaths;
import com.batistes.kskb.api.entity.Kills;
import com.batistes.kskb.api.entity.PlayerBlinds;
import com.batistes.kskb.api.entity.PlayerEconomies;
import com.batistes.kskb.api.entity.Players;
import com.batistes.kskb.api.repository.BombsDefusedRepository;
import com.batistes.kskb.api.repository.BombsPlantedRepository;
import com.batistes.kskb.api.repository.ChickenDeathsRepository;
import com.batistes.kskb.api.repository.DamagesRepository;
import com.batistes.kskb.api.repository.GrenadeBouncesRepository;
import com.batistes.kskb.api.repository.GrenadeProjectilesDestroyRepository;
import com.batistes.kskb.api.repository.KillsRepository;
import com.batistes.kskb.api.repository.PlayerBlindsRepository;
import com.batistes.kskb.api.repository.PlayerBuysRepository;
import com.batistes.kskb.api.repository.PlayerEconomiesRepository;
import com.batistes.kskb.api.repository.PlayersRepository;
import com.batistes.kskb.api.repository.ShotsRepository;
import com.batistes.kskb.api.util.Constants;
import com.batistes.kskb.api.util.Utils;

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
    private ChickenDeathsRepository chickenDeathsRepository;

    @Autowired
    private PlayerBlindsRepository playerBlindsRepository;

    @Autowired
    private DamagesRepository damagesRepository;

    @Autowired
    private GrenadeBouncesRepository grenadeBouncesRepository;

    @Autowired
    private PlayerBuysRepository playerBuysRepository;

    @Autowired
    private Constants constants;

    @Autowired
    private Utils utils;

    public String getTotalData(Date startDate, Date endDate){

        final Logger logger = LoggerFactory.getLogger(TotalDataService.class);
        
        logger.info("Data retrieve start");

        List<Players> players = playersRepository.findByNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Players data Finished");

        List<Kills> kills = killsRepository.findByKillerNameOrAssisterNameOrVictimNameBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Kills data Finished");

        List<BombsDefused> bombsDefused = bombsDefusedRepository.findByDefuserNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Bombs defused data Finished");
        
        List<BombsPlanted> bombsPlanted = bombsPlantedRepository.findByPlanterNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Bombs planted data Finished");

        List<PlayerEconomies> playerEconomies = playerEconomiesRepository.findByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player economies data Finished");
        
        List<ChickenDeaths> chickenDeaths = chickenDeathsRepository.findByKillerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Chicken deaths data Finished");
        
        List<PlayerBlinds> playerBlinds = playerBlindsRepository.findByKillerNameOrAssisterNameOrVictimNameBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Player blinds data Finished");

        List<StatisticDTO> shotsData = shotsRepository.countShotsByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Shots data Finished");

        List<StatisticDTO> damagesData = damagesRepository.countDamagesByAttackerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Damages data Finished");

        List<StatisticDTO> allyDamagesData = damagesRepository.sumTeamDamagesByAttackerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Ally damages data Finished");

        List<StatisticDTO> selfDamagesData = damagesRepository.sumSelfDamagesByAttackerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Self damages data Finished");

        List<StatisticDTO> grenadeBouncesData = grenadeBouncesRepository.countBouncesByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Grenade Bounces data Finished");

        List<StatisticDTO> playerGrenadeBuysData = playerBuysRepository.findGrenadeBuysByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player Grenade Buys data Finished");

        List<StatisticDTO> grenadeThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player Grenade Throws data Finished");

        List<StatisticDTO> heThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInAndTypeInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            Arrays.asList("HE Grenade"),
            startDate, endDate);
        logger.info("SQL - Player HE Throws data Finished");

        List<StatisticDTO> smokeThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInAndTypeInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            Arrays.asList("Smoke Grenade"),
            startDate, endDate);
        logger.info("SQL - Player Smoke Throws data Finished");

        List<StatisticDTO> molotovThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInAndTypeInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            Arrays.asList("Incendiary Grenade","Molotov"),
            startDate, endDate);
        logger.info("SQL - Player Fires Throws data Finished");

        List<StatisticDTO> decoyThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInAndTypeInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            Arrays.asList("Decoy Grenade"),
            startDate, endDate);
        logger.info("SQL - Player Decoy Throws data Finished");

        List<StatisticDTO> flashThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInAndTypeInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            Arrays.asList("Flashbang"),
            startDate, endDate);
        logger.info("SQL - Player Flash Throws data Finished");

        logger.info("SQL Finished");

        final Map<String, TotalDataDTO> totalData = new HashMap<>();

        totalData.putAll(this.processKills(kills));
        logger.info("Kills Processing Finished");
        totalData.putAll(this.processPlayers(players));
        logger.info("Players Processing Finished");
        totalData.putAll(this.processBombsDefused(bombsDefused));
        logger.info("Bombs Defused Processing Finished");
        totalData.putAll(this.processBombsPlanted(bombsPlanted));
        logger.info("Bombs Planted Processing Finished");
        totalData.putAll(this.processPlayerEconomies(playerEconomies));
        logger.info("Player Economies Processing Finished");
        totalData.putAll(this.processShots(shotsData, damagesData));
        logger.info("Shots Processing Finished");
        totalData.putAll(this.processChickens(chickenDeaths));
        logger.info("Chickens Processing Finished");
        totalData.putAll(this.processBounces(grenadeBouncesData));
        logger.info("Bounces Processing Finished");
        totalData.putAll(this.processDamages(allyDamagesData, selfDamagesData));
        logger.info("Damages Processing Finished");
        totalData.putAll(this.processBlinds(playerBlinds));
        logger.info("Blinds Processing Finished");
        totalData.putAll(this.processWastes(grenadeThrowsData, playerGrenadeBuysData, 
                                           heThrowsData, smokeThrowsData, molotovThrowsData, 
                                           decoyThrowsData, flashThrowsData));
        logger.info("Wastes Processing Finished");
        logger.info("Java process Finished");
        
        //Convertimos el MAP en LIST
        List<TotalDataDTO> totalDataList = utils.convertMapToList(totalData);

        //Ordenamos la lista
        totalDataList.sort((dto1, dto2) -> Integer.compare(dto1.getCustomOrder(), dto2.getCustomOrder()));

        //Convertimos el LIST en un STRING JSON
        String result = Utils.convertToJSON(totalDataList);

        logger.info("Data formatting Finished");

        // Liberar referencias a las listas
        logger.info("Memory freeing");
        players = null;
        kills = null;
        bombsDefused = null;
        bombsPlanted = null;
        playerEconomies = null;
        shotsData = null;
        chickenDeaths = null;
        playerBlinds = null;
        damagesData = null;
        grenadeBouncesData = null;
        playerGrenadeBuysData = null;
        grenadeThrowsData = null;
        heThrowsData = null;
        smokeThrowsData = null;
        molotovThrowsData = null;
        decoyThrowsData = null;
        flashThrowsData = null;
        allyDamagesData = null;
        selfDamagesData = null;

        return result;
    }

    private Map<String, TotalDataDTO> processBlinds (List<PlayerBlinds> totalBlinds){
        Map<String, TotalDataDTO> blindsMap = new HashMap<>();

        TotalDataDTO blinds = new TotalDataDTO();
        blinds.setIcon("/icons/total_data/flash_enemies.png");
        blinds.setShinchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SHINCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setKazama(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_KAZAMA.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setMafios(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_MAFIOS.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(blind.getFlashedName()))
            .count()));
        blinds.setNene(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_NENE.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setSwagchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SWAGCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setCustomOrder(32);
        blindsMap.put("Enemigos flasheados", blinds);

        blinds = new TotalDataDTO();
        blinds.setIcon("/icons/total_data/flash_allies.png");
        blinds.setShinchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SHINCHAN.equals(blind.getFlasherName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setKazama(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_KAZAMA.equals(blind.getFlasherName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setMafios(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_MAFIOS.equals(blind.getFlasherName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(blind.getFlashedName()))
            .count()));
        blinds.setNene(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_NENE.equals(blind.getFlasherName()) && Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setSwagchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SWAGCHAN.equals(blind.getFlasherName()) && Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .count()));
        blinds.setCustomOrder(33);
        blindsMap.put("Aliados flasheados", blinds);

        blinds = new TotalDataDTO();
        blinds.setIcon("/icons/total_data/max_blind.png");
        blinds.setShinchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SHINCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)).replace(".",","));
        blinds.setKazama(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_KAZAMA.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)).replace(".",","));
        blinds.setMafios(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_MAFIOS.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)).replace(".",","));
        blinds.setNene(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_NENE.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)).replace(".",","));
        blinds.setSwagchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SWAGCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)).replace(".",","));
        blinds.setCustomOrder(34);
        blindsMap.put("Máximo flasheo (seg)", blinds);

        return blindsMap;
    }

    private Map<String, TotalDataDTO> processDamages (List<StatisticDTO> allyDamagesData, List<StatisticDTO> selfDamagesData){
        Map<String, TotalDataDTO> damagesMap = new HashMap<>();
        
        TotalDataDTO damages = new TotalDataDTO();
        damages.setIcon("/icons/total_data/ally_damage.png"); 
        int allyDamageShinchan = allyDamagesData.stream()
            .filter(damage -> constants.SHINCHAN_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        int selfDamageShinchan = selfDamagesData.stream()
            .filter(damage -> constants.SHINCHAN_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        damages.setShinchan(String.valueOf(allyDamageShinchan - selfDamageShinchan));
        int allyDamageKazama = allyDamagesData.stream()
            .filter(damage -> constants.KAZAMA_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        int selfDamageKazama = selfDamagesData.stream()
            .filter(damage -> constants.KAZAMA_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        damages.setKazama(String.valueOf(allyDamageKazama - selfDamageKazama));
        int allyDamageMafios = allyDamagesData.stream()
            .filter(damage -> constants.MAFIOS_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        int selfDamageMafios = selfDamagesData.stream()
            .filter(damage -> constants.MAFIOS_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        damages.setMafios(String.valueOf(allyDamageMafios - selfDamageMafios));
        int allyDamageNene = allyDamagesData.stream()
            .filter(damage -> constants.NENE_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        int selfDamageNene = selfDamagesData.stream()
            .filter(damage -> constants.NENE_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        damages.setNene(String.valueOf(allyDamageNene - selfDamageNene));
        int allyDamageSwagchan = allyDamagesData.stream()
            .filter(damage -> constants.SWAGCHAN_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        int selfDamageSwagchan = selfDamagesData.stream()
            .filter(damage -> constants.SWAGCHAN_STEAM_ID.equals(damage.getPlayer()))
            .mapToInt(stat -> Integer.parseInt(stat.getValue().toString())).sum();
        damages.setSwagchan(String.valueOf(allyDamageSwagchan - selfDamageSwagchan));
        damages.setCustomOrder(18);
        damagesMap.put("Daño a aliados", damages);
        
        return damagesMap;
    }

    private Map<String, TotalDataDTO> processBounces (List<StatisticDTO> grenadeBouncesData){
        Map<String, TotalDataDTO> bouncesMap = new HashMap<>();

        TotalDataDTO bounces = new TotalDataDTO();
        bounces.setIcon("/icons/total_data/bounces.png");
        bounces.setShinchan(grenadeBouncesData.stream()
            .filter(bounce -> constants.PLAYER_SHINCHAN.equals(bounce.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        bounces.setKazama(grenadeBouncesData.stream()
            .filter(bounce -> constants.PLAYER_KAZAMA.equals(bounce.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        bounces.setMafios(grenadeBouncesData.stream()
            .filter(bounce -> constants.PLAYER_MAFIOS.equals(bounce.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        bounces.setNene(grenadeBouncesData.stream()
            .filter(bounce -> constants.PLAYER_NENE.equals(bounce.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        bounces.setSwagchan(grenadeBouncesData.stream()
            .filter(bounce -> constants.PLAYER_SWAGCHAN.equals(bounce.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        bounces.setCustomOrder(30);
        bouncesMap.put("Rebotes de granadas", bounces);

        return bouncesMap;
    }

    private Map<String, TotalDataDTO> processChickens (List<ChickenDeaths> totalChickenDeaths){
        Map<String, TotalDataDTO> chickenMap = new HashMap<>();

        TotalDataDTO chickens = new TotalDataDTO();
        chickens.setIcon("/icons/total_data/chicken_deaths.png");
        chickens.setShinchan(String.valueOf(totalChickenDeaths.stream()
            .filter(chicken -> constants.SHINCHAN_STEAM_ID.equals(chicken.getKillerSteamId()))
            .count()));
        chickens.setKazama(String.valueOf(totalChickenDeaths.stream()
            .filter(chicken -> constants.KAZAMA_STEAM_ID.equals(chicken.getKillerSteamId()))
            .count()));
        chickens.setMafios(String.valueOf(totalChickenDeaths.stream()
            .filter(chicken -> constants.MAFIOS_STEAM_ID.equals(chicken.getKillerSteamId()))
            .count()));
        chickens.setNene(String.valueOf(totalChickenDeaths.stream()
            .filter(chicken -> constants.NENE_STEAM_ID.equals(chicken.getKillerSteamId()))
            .count()));
        chickens.setSwagchan(String.valueOf(totalChickenDeaths.stream()
            .filter(chicken -> constants.SWAGCHAN_STEAM_ID.equals(chicken.getKillerSteamId()))
            .count()));
        chickens.setCustomOrder(40);
        chickenMap.put("Pollos asesinados", chickens);

        return chickenMap;
    }

    private Map<String, TotalDataDTO> processShots (List<StatisticDTO> shotsData, List<StatisticDTO> damagesData){
        Map<String, TotalDataDTO> shotsMap = new HashMap<>();

        TotalDataDTO shots = new TotalDataDTO();
        shots.setIcon("/icons/total_data/shots.png");
        shots.setShinchan(shotsData.stream()
            .filter(shot -> constants.PLAYER_SHINCHAN.equals(shot.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        shots.setKazama(shotsData.stream()
            .filter(shot -> constants.PLAYER_KAZAMA.equals(shot.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        shots.setMafios(shotsData.stream()
            .filter(shot -> constants.PLAYER_MAFIOS.equals(shot.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        shots.setNene(shotsData.stream()
            .filter(shot -> constants.PLAYER_NENE.equals(shot.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        shots.setSwagchan(shotsData.stream()
            .filter(shot -> constants.PLAYER_SWAGCHAN.equals(shot.getPlayer())).findFirst().map(StatisticDTO::getValue)
            .orElse("0").toString());
        shots.setCustomOrder(38);
        shotsMap.put("Disparos", shots);


        // Calculando precisión a partir de tiros y daño
        Map<String, Double> precisionMap = damagesData.stream()
            .collect(Collectors.toMap(
                stat -> utils.convertSteamIdToName(stat.getPlayer()),
                stat -> Integer.valueOf(stat.getValue().toString())
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .flatMap(damageEntry -> shotsData.stream()
                .filter(shotStat -> shotStat.getPlayer().equals(damageEntry.getKey()))
                .map(shotStat -> {
                    Integer shotsFired = Integer.valueOf(shotStat.getValue().toString());
                    double precision = damageEntry.getValue() / (double) shotsFired * 100;
                    return Map.entry(shotStat.getPlayer(), precision);
                })
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Setteando los valores desde el map anterior
        TotalDataDTO precision = new TotalDataDTO();
        precision.setIcon("/icons/total_data/precision.png");
        precision.setShinchan(String.format("%.2f",precisionMap.get(constants.PLAYER_SHINCHAN)) + "%");
        precision.setKazama(String.format("%.2f",precisionMap.get(constants.PLAYER_KAZAMA)) + "%");
        precision.setMafios(String.format("%.2f",precisionMap.get(constants.PLAYER_MAFIOS)) + "%"); 
        precision.setNene(String.format("%.2f",precisionMap.get(constants.PLAYER_NENE)) + "%");
        precision.setSwagchan(String.format("%.2f",precisionMap.get(constants.PLAYER_SWAGCHAN)) + "%");
        precision.setCustomOrder(39);
        shotsMap.put("Precisión", precision);

        return shotsMap;
    }

    private Map<String, TotalDataDTO> processPlayerEconomies (List<PlayerEconomies> totalEconomies){
        Map<String, TotalDataDTO> economiesMap = new HashMap<>();
        
        TotalDataDTO economies = new TotalDataDTO();
        economies.setIcon("/icons/total_data/money_spent.png");
        economies.setShinchan(String.format("%,d", totalEconomies.stream()
            .filter(economy -> constants.PLAYER_SHINCHAN.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum())+" $");
        economies.setKazama(String.format("%,d", totalEconomies.stream()
            .filter(economy -> constants.PLAYER_KAZAMA.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum())+" $");
        economies.setMafios(String.format("%,d", totalEconomies.stream()
            .filter(economy -> constants.PLAYER_MAFIOS.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum())+" $");
        economies.setNene(String.format("%,d", totalEconomies.stream()
            .filter(economy -> constants.PLAYER_NENE.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum())+" $");
        economies.setSwagchan(String.format("%,d", totalEconomies.stream()
            .filter(economy -> constants.PLAYER_SWAGCHAN.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum())+" $");
        economies.setCustomOrder(37);
        economiesMap.put("Dinero gastado", economies);
        
        return economiesMap;
    }

    private Map<String, TotalDataDTO> processWastes (List<StatisticDTO> grenadeThrowsData, List<StatisticDTO> grenadeBuysData,
                                                    List<StatisticDTO> heThrowsData, List<StatisticDTO> smokeThrowsData, 
                                                    List<StatisticDTO> molotovThrowsData, List<StatisticDTO> decoyThrowsData, 
                                                    List<StatisticDTO> flashThrowsData){

        Map<String, TotalDataDTO> grenadesMap = new HashMap<>();

        // Calculando desperdicios a partir de compras y lanzamientos
        Map<String, Integer> wastesMap = grenadeBuysData.stream()
            .collect(Collectors.toMap(
                stat -> stat.getPlayer(),
                stat -> Integer.valueOf(stat.getValue().toString())
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .flatMap(buysEntry -> grenadeThrowsData.stream()
                .filter(throwStat -> throwStat.getPlayer().equals(buysEntry.getKey()))
                .map(throwStat -> {
                    Integer throwsCount = Integer.valueOf(throwStat.getValue().toString());
                    Integer wastes = buysEntry.getValue() - throwsCount;
                    return Map.entry(buysEntry.getKey(), wastes);
                })
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Setteando los valores desde el map anterior
        TotalDataDTO leftOvers = new TotalDataDTO();
        leftOvers.setIcon("/icons/total_data/grenades_no_use.png");
        leftOvers.setShinchan(wastesMap.get(constants.PLAYER_SHINCHAN).toString());
        leftOvers.setKazama(wastesMap.get(constants.PLAYER_KAZAMA).toString());
        leftOvers.setMafios(wastesMap.get(constants.PLAYER_MAFIOS).toString());
        leftOvers.setNene(wastesMap.get(constants.PLAYER_NENE).toString());
        leftOvers.setSwagchan(wastesMap.get(constants.PLAYER_SWAGCHAN).toString());
        leftOvers.setCustomOrder(29);
        grenadesMap.put("Granadas no lanzadas", leftOvers);

        TotalDataDTO flashes = new TotalDataDTO();
        flashes.setIcon("/icons/total_data/flashes.png");
        flashes.setShinchan(flashThrowsData.stream()
            .filter(flash -> constants.PLAYER_SHINCHAN.equals(flash.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        flashes.setKazama(flashThrowsData.stream()
            .filter(flash -> constants.PLAYER_KAZAMA.equals(flash.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        flashes.setMafios(flashThrowsData.stream()
            .filter(flash -> constants.PLAYER_MAFIOS.equals(flash.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        flashes.setNene(flashThrowsData.stream()
            .filter(flash -> constants.PLAYER_NENE.equals(flash.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        flashes.setSwagchan(flashThrowsData.stream()
            .filter(flash -> constants.PLAYER_SWAGCHAN.equals(flash.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        flashes.setCustomOrder(24);
        grenadesMap.put("Flashes lanzadas", flashes);

        TotalDataDTO smokes = new TotalDataDTO();
        smokes.setIcon("/icons/total_data/smokes.png");
        smokes.setShinchan(smokeThrowsData.stream()
            .filter(smoke -> constants.PLAYER_SHINCHAN.equals(smoke.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        smokes.setKazama(smokeThrowsData.stream()
            .filter(smoke -> constants.PLAYER_KAZAMA.equals(smoke.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        smokes.setMafios(smokeThrowsData.stream()
            .filter(smoke -> constants.PLAYER_MAFIOS.equals(smoke.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        smokes.setNene(smokeThrowsData.stream()
            .filter(smoke -> constants.PLAYER_NENE.equals(smoke.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        smokes.setSwagchan(smokeThrowsData.stream()
            .filter(smoke -> constants.PLAYER_SWAGCHAN.equals(smoke.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        smokes.setCustomOrder(25);
        grenadesMap.put("Humos lanzados", smokes);

        TotalDataDTO hes = new TotalDataDTO();
        hes.setIcon("/icons/total_data/he_grenades.png");
        hes.setShinchan(heThrowsData.stream()
            .filter(he -> constants.PLAYER_SHINCHAN.equals(he.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        hes.setKazama(heThrowsData.stream()
            .filter(he -> constants.PLAYER_KAZAMA.equals(he.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        hes.setMafios(heThrowsData.stream()
            .filter(he -> constants.PLAYER_MAFIOS.equals(he.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        hes.setNene(heThrowsData.stream()
            .filter(he -> constants.PLAYER_NENE.equals(he.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        hes.setSwagchan(heThrowsData.stream()
            .filter(he -> constants.PLAYER_SWAGCHAN.equals(he.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        hes.setCustomOrder(26);
        grenadesMap.put("HEs lanzadas", hes);

        TotalDataDTO decoys = new TotalDataDTO();
        decoys.setIcon("/icons/total_data/decoys.png");
        decoys.setShinchan(decoyThrowsData.stream()
            .filter(decoy -> constants.PLAYER_SHINCHAN.equals(decoy.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        decoys.setKazama(decoyThrowsData.stream()
            .filter(decoy -> constants.PLAYER_KAZAMA.equals(decoy.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        decoys.setMafios(decoyThrowsData.stream()
            .filter(decoy -> constants.PLAYER_MAFIOS.equals(decoy.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        decoys.setNene(decoyThrowsData.stream()
            .filter(decoy -> constants.PLAYER_NENE.equals(decoy.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        decoys.setSwagchan(decoyThrowsData.stream()
            .filter(decoy -> constants.PLAYER_SWAGCHAN.equals(decoy.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        decoys.setCustomOrder(27);
        grenadesMap.put("Decoys lanzadas", decoys);

        TotalDataDTO molotovs = new TotalDataDTO();
        molotovs.setIcon("/icons/total_data/molotovs.png");
        molotovs.setShinchan(molotovThrowsData.stream()
            .filter(molotov -> constants.PLAYER_SHINCHAN.equals(molotov.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        molotovs.setKazama(molotovThrowsData.stream()
            .filter(molotov -> constants.PLAYER_KAZAMA.equals(molotov.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        molotovs.setMafios(molotovThrowsData.stream()
            .filter(molotov -> constants.PLAYER_MAFIOS.equals(molotov.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        molotovs.setNene(molotovThrowsData.stream()
            .filter(molotov -> constants.PLAYER_NENE.equals(molotov.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        molotovs.setSwagchan(molotovThrowsData.stream()
            .filter(molotov -> constants.PLAYER_SWAGCHAN.equals(molotov.getPlayer()))
            .findFirst().map(StatisticDTO::getValue).orElse("0").toString());
        molotovs.setCustomOrder(28);
        grenadesMap.put("Molotovs lanzados", molotovs);

        return grenadesMap;
    }

    private Map<String, TotalDataDTO> processBombsDefused (List<BombsDefused> totalBombsDefused){
        Map<String, TotalDataDTO> bombsDefusedMap = new HashMap<>();

        TotalDataDTO bombsDefused = new TotalDataDTO();
        bombsDefused.setIcon("/icons/total_data/defuses.png");
        bombsDefused.setShinchan(String.valueOf(totalBombsDefused.stream()
            .filter(bombDefused -> constants.PLAYER_SHINCHAN.equals(bombDefused.getDefuserName()))
            .count()));
        bombsDefused.setKazama(String.valueOf(totalBombsDefused.stream()
            .filter(bombDefused -> constants.PLAYER_KAZAMA.equals(bombDefused.getDefuserName()))
            .count()));
        bombsDefused.setMafios(String.valueOf(totalBombsDefused.stream()
            .filter(bombDefused -> constants.PLAYER_MAFIOS.equals(bombDefused.getDefuserName()))
            .count()));
        bombsDefused.setNene(String.valueOf(totalBombsDefused.stream()
            .filter(bombDefused -> constants.PLAYER_NENE.equals(bombDefused.getDefuserName()))
            .count()));
        bombsDefused.setSwagchan(String.valueOf(totalBombsDefused.stream()
            .filter(bombDefused -> constants.PLAYER_SWAGCHAN.equals(bombDefused.getDefuserName()))
            .count()));
        bombsDefused.setCustomOrder(15);
        bombsDefusedMap.put("Bombas defusadas", bombsDefused);

        return bombsDefusedMap;
    }

    private Map<String, TotalDataDTO> processBombsPlanted (List<BombsPlanted> totalBombsPlanted){
        Map<String, TotalDataDTO> bombsPlantedMap = new HashMap<>();

        TotalDataDTO bombsPlanted = new TotalDataDTO();
        bombsPlanted.setIcon("/icons/total_data/c4_plants.png");
        bombsPlanted.setShinchan(String.valueOf(totalBombsPlanted.stream()
            .filter(bombPlanted -> constants.PLAYER_SHINCHAN.equals(bombPlanted.getPlanterName()))
            .count()));
        bombsPlanted.setKazama(String.valueOf(totalBombsPlanted.stream()
            .filter(bombPlanted -> constants.PLAYER_KAZAMA.equals(bombPlanted.getPlanterName()))
            .count()));
        bombsPlanted.setMafios(String.valueOf(totalBombsPlanted.stream()
            .filter(bombPlanted -> constants.PLAYER_MAFIOS.equals(bombPlanted.getPlanterName()))
            .count()));
        bombsPlanted.setNene(String.valueOf(totalBombsPlanted.stream()
            .filter(bombPlanted -> constants.PLAYER_NENE.equals(bombPlanted.getPlanterName()))
            .count()));
        bombsPlanted.setSwagchan(String.valueOf(totalBombsPlanted.stream()
            .filter(bombPlanted -> constants.PLAYER_SWAGCHAN.equals(bombPlanted.getPlanterName()))
            .count()));
        bombsPlanted.setCustomOrder(14);
        bombsPlantedMap.put("Bombas plantadas", bombsPlanted);

        return bombsPlantedMap;
    }

    private Map<String, TotalDataDTO> processPlayers (List<Players> totalPlayers){
        Map<String, TotalDataDTO> playersMap = new HashMap<>();

        TotalDataDTO players = new TotalDataDTO();
        players.setIcon("/icons/total_data/mvps.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getMvpCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getMvpCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getMvpCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getMvpCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getMvpCount).sum()));
        players.setCustomOrder(6);
        playersMap.put("MVPs", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/entry_kills.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getFirstKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getFirstKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getFirstKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getFirstKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getFirstKillCount).sum()));
        players.setCustomOrder(13);
        playersMap.put("Entry kills", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/health_damage.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getDamageHealth).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getDamageHealth).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getDamageHealth).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getDamageHealth).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getDamageHealth).sum()));
        players.setCustomOrder(16);
        playersMap.put("Daño a vida", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/armor_damage.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getDamageArmor).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getDamageArmor).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getDamageArmor).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getDamageArmor).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getDamageArmor).sum()));
        players.setCustomOrder(17);
        playersMap.put("Daño a armadura", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/5k.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getFiveKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getFiveKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getFiveKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getFiveKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getFiveKillCount).sum()));
        players.setCustomOrder(19);
        playersMap.put("5K", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/4k.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getFourKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getFourKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getFourKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getFourKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getFourKillCount).sum()));
        players.setCustomOrder(20);
        playersMap.put("4K", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/3k.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getThreeKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getThreeKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getThreeKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getThreeKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getThreeKillCount).sum()));
        players.setCustomOrder(21);
        playersMap.put("3K", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/2k.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getTwoKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getTwoKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getTwoKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getTwoKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getTwoKillCount).sum()));
        players.setCustomOrder(22);
        playersMap.put("2K", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/1k.png");
        players.setShinchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToInt(Players::getOneKillCount).sum()));
        players.setKazama(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToInt(Players::getOneKillCount).sum()));
        players.setMafios(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToInt(Players::getOneKillCount).sum()));
        players.setNene(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToInt(Players::getOneKillCount).sum()));
        players.setSwagchan(String.valueOf(totalPlayers.stream()
            .filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToInt(Players::getOneKillCount).sum()));
        players.setCustomOrder(23);
        playersMap.put("1K", players);

        players = new TotalDataDTO();
        players.setIcon("/icons/total_data/kast.png");
        players.setShinchan(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName())).count())+"%");
        players.setKazama(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_KAZAMA.equals(player.getName())).count())+"%");
        players.setMafios(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_MAFIOS.equals(player.getName())).count())+"%");
        players.setNene(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_NENE.equals(player.getName())).count())+"%");
        players.setSwagchan(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName())).count())+"%");
        players.setCustomOrder(11);
        playersMap.put("% KAST", players);

        return playersMap;
    }

    private Map<String, TotalDataDTO> processKills (List<Kills> totalKills){
        
        Map<String, TotalDataDTO> killsMap = new HashMap<>();
        
        TotalDataDTO kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/kills.png"); 
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
        kills.setCustomOrder(1);
        killsMap.put("Eliminaciones", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/assists.png");
        kills.setShinchan(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_SHINCHAN.equals(kill.getAssisterName()))
            .count()));
        kills.setKazama(String.valueOf(totalKills.stream()
            .filter(kill -> constants.PLAYER_KAZAMA.equals(kill.getAssisterName()))
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
        kills.setCustomOrder(4);
        killsMap.put("Asistencias", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/deaths.png");
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
        kills.setCustomOrder(5);
        killsMap.put("Muertes", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/headshots.png");    
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
        kills.setCustomOrder(9);
        killsMap.put("Headshots", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/headshot_rate.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("Headshots").getShinchan()) / Double.valueOf(killsMap.get("Eliminaciones").getShinchan())) * 100
            )+"%");
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getKazama()) / Double.valueOf(killsMap.get("Eliminaciones").getKazama())) * 100
            )+"%");
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getMafios()) / Double.valueOf(killsMap.get("Eliminaciones").getMafios())) * 100
            )+"%");
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getNene()) / Double.valueOf(killsMap.get("Eliminaciones").getNene())) * 100
            )+"%");
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getSwagchan()) / Double.valueOf(killsMap.get("Eliminaciones").getSwagchan())) * 100
            )+"%");
        kills.setCustomOrder(10);
        killsMap.put("% Headshots", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/kd_ratio.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("Eliminaciones").getShinchan()) / Double.valueOf(killsMap.get("Muertes").getShinchan()))
        ));
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getKazama()) / Double.valueOf(killsMap.get("Muertes").getKazama()))
        ));
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getMafios()) / Double.valueOf(killsMap.get("Muertes").getMafios()))
        ));
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getNene()) / Double.valueOf(killsMap.get("Muertes").getNene()))
        ));
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getSwagchan()) / Double.valueOf(killsMap.get("Muertes").getSwagchan()))
        ));
        kills.setCustomOrder(7);
        killsMap.put("K/D Ratio", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/kda_ratio.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("Eliminaciones").getShinchan()) + Double.valueOf(killsMap.get("Asistencias").getShinchan())) / Double.valueOf(killsMap.get("Muertes").getShinchan()))
        );
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getKazama()) + Double.valueOf(killsMap.get("Asistencias").getKazama())) / Double.valueOf(killsMap.get("Muertes").getKazama()))
        );
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getMafios()) + Double.valueOf(killsMap.get("Asistencias").getMafios())) / Double.valueOf(killsMap.get("Muertes").getMafios()))
        );
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getNene()) + Double.valueOf(killsMap.get("Asistencias").getNene())) / Double.valueOf(killsMap.get("Muertes").getNene()))
        );
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getSwagchan()) + Double.valueOf(killsMap.get("Asistencias").getSwagchan())) / Double.valueOf(killsMap.get("Muertes").getSwagchan()))
        );
        kills.setCustomOrder(8);
        killsMap.put("K/D/A Ratio", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/team_kills.png");
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
            kills.setCustomOrder(12);
        killsMap.put("Team kills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/flash_assists.png");
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
        kills.setCustomOrder(31);
        killsMap.put("Asistencias de flash", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/blinded_kills.png");
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
        kills.setCustomOrder(35);
        killsMap.put("Eliminaciones ciego", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/c4_deaths.png");
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
        kills.setCustomOrder(36);
        killsMap.put("Muertes por C4", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/ct_side_kills.png");
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
        kills.setCustomOrder(2);
        killsMap.put("Elim. como CT", kills);

        kills = new TotalDataDTO();
        kills.setIcon("/icons/total_data/t_side_kills.png");
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
        kills.setCustomOrder(3);
        killsMap.put("Elim. como T", kills);
        
        return killsMap;
    }

}
