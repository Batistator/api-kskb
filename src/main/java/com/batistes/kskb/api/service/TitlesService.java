package com.batistes.kskb.api.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batistes.kskb.api.config.TitleConfig;
import com.batistes.kskb.api.dto.FullTitleDTO;
import com.batistes.kskb.api.dto.StatisticDTO;
import com.batistes.kskb.api.entity.BombsDefuseStart;
import com.batistes.kskb.api.entity.BombsDefused;
import com.batistes.kskb.api.entity.BombsPlantStart;
import com.batistes.kskb.api.entity.BombsPlanted;
import com.batistes.kskb.api.entity.ChickenDeaths;
import com.batistes.kskb.api.entity.Clutches;
import com.batistes.kskb.api.entity.GrenadeProjectilesDestroy;
import com.batistes.kskb.api.entity.Kills;
import com.batistes.kskb.api.entity.PlayerBlinds;
import com.batistes.kskb.api.entity.PlayerEconomies;
import com.batistes.kskb.api.entity.Players;
import com.batistes.kskb.api.entity.Rounds;
import com.batistes.kskb.api.repository.BombsDefuseStartRepository;
import com.batistes.kskb.api.repository.BombsDefusedRepository;
import com.batistes.kskb.api.repository.BombsPlantStartRepository;
import com.batistes.kskb.api.repository.BombsPlantedRepository;
import com.batistes.kskb.api.repository.ChickenDeathsRepository;
import com.batistes.kskb.api.repository.ClutchesRepository;
import com.batistes.kskb.api.repository.DamagesRepository;
import com.batistes.kskb.api.repository.GrenadeBouncesRepository;
import com.batistes.kskb.api.repository.GrenadeProjectilesDestroyRepository;
import com.batistes.kskb.api.repository.KillsRepository;
import com.batistes.kskb.api.repository.PlayerBlindsRepository;
import com.batistes.kskb.api.repository.PlayerBuysRepository;
import com.batistes.kskb.api.repository.PlayerEconomiesRepository;
import com.batistes.kskb.api.repository.PlayersRepository;
import com.batistes.kskb.api.repository.RoundsRepository;
import com.batistes.kskb.api.repository.ShotsRepository;
import com.batistes.kskb.api.util.Constants;
import com.batistes.kskb.api.util.Utils;

@Service
public class TitlesService {
    @Autowired
    private GrenadeProjectilesDestroyRepository grenadeProjectilesDestroyRepository;
    
    @Autowired
    private BombsDefusedRepository bombsDefusedRepository;

    @Autowired
    private BombsPlantedRepository bombsPlantedRepository;

    @Autowired
    private BombsDefuseStartRepository bombsDefuseStartRepository;

    @Autowired
    private BombsPlantStartRepository bombsPlantStartRepository;

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
    private ClutchesRepository clutchesRepository;

    @Autowired
    private DamagesRepository damagesRepository;

    @Autowired
    private RoundsRepository roundsRepository;

    @Autowired
    private GrenadeBouncesRepository grenadeBouncesRepository;

    @Autowired
    private PlayerBuysRepository playerBuysRepository;

    @Autowired
    private Constants constants;

    @Autowired
    private Utils utils;

    @Autowired
    private TitleConfig titles;

    final Logger logger = LoggerFactory.getLogger(TitlesService.class);

    public List<FullTitleDTO> findAllTitles(Date startDate, Date endDate){
        
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
        logger.info("SQL - Bombs planted start data Finished");

        List<BombsDefuseStart> bombsDefuseStart = bombsDefuseStartRepository.findByDefuserNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Bombs defused start data Finished");

        List<BombsPlantStart> bombsPlantStart = bombsPlantStartRepository.findByPlanterNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Bombs planted data Finished");

        List<GrenadeProjectilesDestroy> grenades = grenadeProjectilesDestroyRepository.findByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Grenade projectile destroy data Finished");

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

        List<Clutches> clutches = clutchesRepository.findByClutcherNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
           startDate, endDate);
        logger.info("SQL - Player buys data Finished");

        List<Rounds> rounds = roundsRepository.findAll();
        logger.info("SQL - Rounds data Finished");

        List<StatisticDTO> shotsData = shotsRepository.countShotsByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Shots data Finished");

        List<StatisticDTO> damagesData = damagesRepository.countDamagesByAttackerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        logger.info("SQL - Damages data Finished");

        List<StatisticDTO> playerRefundsData = playerBuysRepository.findRefundsByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player Refunds data Finished");

        List<StatisticDTO> playerGrenadeBuysData = playerBuysRepository.findGrenadeBuysByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player Grenade Buys data Finished");

        List<StatisticDTO> grenadeThrowsData = grenadeProjectilesDestroyRepository.countGrenadesByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Player Grenade Throws data Finished");

        List<StatisticDTO> grenadeBouncesData = grenadeBouncesRepository.countBouncesByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Grenade Bounces data Finished");

        StatisticDTO fastestKillData = killsRepository.findFastestKillTitle(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Fastest kill data Finished");

        StatisticDTO fastestDeathData = killsRepository.findFastestDeathTitle(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("SQL - Fastest death data Finished");
        
        logger.info("SQL Finished");

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        titlesList.addAll(processKills(kills, rounds));
        logger.info("Kills Processing Finished");
        titlesList.addAll(processFastestKill(fastestKillData));
        logger.info("FastestKill Processing Finished");
        titlesList.addAll(processFastestDeath(fastestDeathData));
        logger.info("FastestDeath Processing Finished");
        titlesList.addAll(processPlayers(players));
        logger.info("Players Processing Finished");
        titlesList.addAll(processGrenades(grenades));
        logger.info("Grenades Processing Finished");
        titlesList.addAll(processPrecision(shotsData, damagesData));
        logger.info("Precision Processing Finished");
        titlesList.addAll(processClutches(clutches));
        logger.info("Clutches Processing Finished");
        titlesList.addAll(processBlinds(playerBlinds));
        logger.info("Blinds Processing Finished");
        titlesList.addAll(processPlants(bombsPlanted));
        logger.info("Plants Processing Finished");
        titlesList.addAll(processDefuses(bombsDefused));
        logger.info("Defuses Processing Finished");
        titlesList.addAll(processFakePlants(bombsPlanted,bombsPlantStart));
        logger.info("FakePlants Processing Finished");
        titlesList.addAll(processFakeDefuse(bombsDefused,bombsDefuseStart));
        logger.info("FakeDefuse Processing Finished");
        titlesList.addAll(processChicken(chickenDeaths));
        logger.info("Chicken Processing Finished");
        titlesList.addAll(processRefunds(playerRefundsData));
        logger.info("Refunds Processing Finished");
        titlesList.addAll(processPlayerEconomies(playerEconomies));
        logger.info("PlayerEconomies Processing Finished");
        titlesList.addAll(processShots(shotsData));
        logger.info("Shots Processing Finished");
        titlesList.addAll(processWastes(playerGrenadeBuysData,grenadeThrowsData));
        logger.info("Wastes Processing Finished");
        titlesList.addAll(processBounces(grenadeBouncesData));
        logger.info("Bounces Processing Finished");
       

        logger.info("Java process Finished");

        // Liberar referencias a las listas
        logger.info("Memory freeing");
        players = null;
        kills = null;
        bombsDefused = null;
        bombsPlanted = null;
        bombsDefuseStart = null;
        bombsPlantStart = null;
        grenades = null;
        playerEconomies = null;
        shotsData = null;
        chickenDeaths = null;
        playerBlinds = null;
        playerRefundsData = null;
        damagesData = null;
        grenadeBouncesData = null;
        clutches = null;
        rounds = null;
        playerGrenadeBuysData = null;
        grenadeThrowsData = null;
        fastestKillData = null;
        fastestDeathData = null;

        return titlesList;
    }

    private List<FullTitleDTO> processKills (List<Kills> kills, List<Rounds> rounds){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más eliminaciones 
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> !kill.getWeaponName().equals("World"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostkills","-","-")));

        //  Título de más muertes
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostdeaths",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostdeaths","-","-")));

        //  Título de más asistencias
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getAssisterName()))
            .filter(kill -> kill.getAssisterName() != null)
            .collect(Collectors.groupingBy(Kills::getAssisterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostassists",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostassists","-","-")));

        //  Título de menos muertes
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("lessdeaths",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("lessdeaths","-","-")));

        //  Título de porcentaje de tiros en la cabeza
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(kill.getKillerName()))
            .collect(Collectors.groupingBy(Kills::getKillerName))
            .entrySet()
            .stream()
            .map(entry -> {
                long totalKills = entry.getValue().size();
                long headshotKills = entry.getValue().stream().filter(Kills::isHeadshot).count();
                double headshotPercentage = (double) headshotKills / totalKills * 100;
                return Map.entry(entry.getKey(), headshotPercentage);
            })
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostheadshots",entry.getKey(),String.format("%.2f", entry.getValue())))
            .orElse(formatTitle("mostheadshots","-","-")));

        //  Título de más muertes por C4
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .filter(kill -> kill.getWeaponName().equals("C4"))
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostc4deaths",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostc4deaths","-","-")));

        //  Título de más team kills
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .filter(kill -> !kill.getWeaponName().equals("World"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostteamkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostteamkills","-","-")));

        //  Título de más eliminaciones con granada
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("HE Grenade"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mosthekills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mosthekills","-","-")));

        //  Título de más eliminaciones con fuego
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Incendiary Grenade") || kill.getWeaponName().equals("Molotov"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostfirekills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostfirekills","-","-")));

        //  Título de más eliminaciones con cuchillo
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Knife"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostknifekills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostknifekills","-","-")));

        //  Título de más eliminaciones con taser
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Zeus x27"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mosttaserkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mosttaserkills","-","-")));

        //  Título de más eliminaciones con escopeta
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponType().equals("shotgun"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostshotgunkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostshotgunkills","-","-")));

        //  Título de la eliminación desde más lejos
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .max((kill1, kill2) -> Double.compare(kill1.getDistance(), kill2.getDistance()))
            .map(kill -> formatTitle("mostdistancekill",kill.getKillerName(),String.format("%.2f", kill.getDistance())))
            .orElse(formatTitle("mostdistancekill","-","-")));

        //  Título de más trade kills
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isTradeKill())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mosttradekills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mosttradekills","-","-")));

        //  Título de más trade deaths
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .filter(kill -> kill.isTradeDeath())
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mosttradedeaths",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mosttradedeaths","-","-")));

        //  Título de más eliminaciones saltando
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isKillerAirborne())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostairkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostairkills","-","-")));

        //  Título de más eliminaciones atravesando
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getPenetratedObjects() > 0)
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostthroughkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostthroughkills","-","-")));

        //  Título de más eliminaciones traspasando humo
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isThroughSmoke())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostsmokekills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostsmokekills","-","-")));

        //  Título de más eliminaciones no scope
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isNoScope())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostnoscope",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostnoscope","-","-")));

        //  Título de más asistencias con flash
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getAssisterName()))
            .filter(kill -> kill.isAssistedFlash())
            .collect(Collectors.groupingBy(Kills::getAssisterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostflashassists",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostflashassists","-","-")));

        //  Título de más eliminaciones ciego
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isKillerBlinded())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostblindkills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostblindkills","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processPlayers (List<Players> players){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más 5K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFiveKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("most5k",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("most5k","-","-")));

        //  Título de más 4K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFourKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("most4k",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("most4k","-","-")));

        //  Título de más 3K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getThreeKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("most3k",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("most3k","-","-")));
                    
        //  Título de más 2K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getTwoKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("most2k",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("most2k","-","-")));
            
        //  Título de más 1K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getOneKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("most1k",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("most1k","-","-")));

        //  Título de más MVP
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getMvpCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostmvp",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostmvp","-","-")));

        //  Título de más daño diverso
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getUtilityDamage)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostdd",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostdd","-","-")));

        // Título de más Anti-shinchan
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getMatchChecksum))
            .values()
            .stream()
            .flatMap(list -> list.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .limit(1))
            .filter(player -> !player.getName().equals(constants.PLAYER_SHINCHAN))
            .collect(Collectors.groupingBy(Players::getName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostovershinchan",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostovershinchan","-","-")));

        //  Título de más entry kills
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFirstKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostentrykills",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostentrykills","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processPrecision (List<StatisticDTO> shotsData, List<StatisticDTO> damagesData){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();
        
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
                    Integer shots = Integer.valueOf(shotStat.getValue().toString());
                    double precision = damageEntry.getValue() / (double) shots * 100;
                    return Map.entry(shotStat.getPlayer(), precision);
                })
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //  Título de mejor precisión
        titlesList.add(precisionMap.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostprecision",entry.getKey(),String.format("%.2f", entry.getValue())))
            .orElse(formatTitle("mostprecision","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processGrenades (List<GrenadeProjectilesDestroy> grenades){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más granadas tiradas
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("HE Grenade"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mosthe",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mosthe","-","-")));

        //  Título de más decoys tiradas
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Decoy Grenade"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostdecoy",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostdecoy","-","-")));

        //  Título de más flashes tiradas
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Flashbang"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostflash",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostflash","-","-")));

        //  Título de más humos tirados
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Smoke Grenade"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostsmoke",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostsmoke","-","-")));

        //  Título de más fuegos tirados
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> (grenade.getGrenadeName().equals("Molotov") || grenade.getGrenadeName().equals("Incendiary Grenade")))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostfire",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostfire","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processClutches (List<Clutches> clutches){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más clutches ganados
        titlesList.add(clutches.stream()
            .filter(clutch -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(clutch.getClutcherName()))
            .filter(clutch -> clutch.isWon() && clutch.getOpponentCount() >= 2)
            .collect(Collectors.groupingBy(Clutches::getClutcherName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostclutches",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostclutches","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processBlinds (List<PlayerBlinds> playerBlinds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de la flash más duradera
        titlesList.add(playerBlinds.stream()
            .filter(playerBlind -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(playerBlind.getFlasherName()))
            .max((playerBlind1, playerBlind2) -> Double.compare(playerBlind1.getDuration(), playerBlind2.getDuration()))
            .map(playerBlind -> formatTitle("longestflash",playerBlind.getFlasherName(),String.format("%.2f", playerBlind.getDuration())))
            .orElse(formatTitle("longestflash","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processDefuses (List<BombsDefused> bombsDefuseds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más defuses
        titlesList.add(bombsDefuseds.stream()
            .filter(defuse -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(defuse.getDefuserName()))
            .collect(Collectors.groupingBy(BombsDefused::getDefuserName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostdefuses",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostdefuses","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processPlants (List<BombsPlanted> bombsPlanteds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más plants
        titlesList.add(bombsPlanteds.stream()
            .filter(plant -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(plant.getPlanterName()))
            .collect(Collectors.groupingBy(BombsPlanted::getPlanterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostplants",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostplants","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processFakePlants (List<BombsPlanted> bombsPlanteds, List<BombsPlantStart> bombsPlantStart){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más fakes al plantar
        titlesList.add(bombsPlantStart.stream()
            .filter(plantStart -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(plantStart.getPlanterName()))
            .collect(Collectors.groupingBy(BombsPlantStart::getPlanterName, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> {
            String playerName = entry.getKey();
            long fakePlants = bombsPlantStart.stream()
                .filter(start -> start.getPlanterName().equals(playerName))
                .filter(start -> bombsPlanteds.stream()
                .noneMatch(plant -> plant.getPlanterName().equals(playerName) && plant.getMatchChecksum().equals(start.getMatchChecksum()) && plant.getRoundNumber() == start.getRoundNumber()))
                .count();
            return Map.entry(playerName, fakePlants);
            })
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostfakeplant",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostfakeplant","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processFakeDefuse (List<BombsDefused> bombsDefused, List<BombsDefuseStart> bombsDefusedStart){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más fakes al defusar
        titlesList.add(bombsDefusedStart.stream()
            .filter(defuseStart -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(defuseStart.getDefuserName()))
            .collect(Collectors.groupingBy(BombsDefuseStart::getDefuserName, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> {
            String playerName = entry.getKey();
            long fakeDefuse = bombsDefusedStart.stream()
                .filter(start -> start.getDefuserName().equals(playerName))
                .filter(start -> bombsDefused.stream()
                .noneMatch(defuse -> defuse.getDefuserName().equals(playerName) && defuse.getMatchChecksum().equals(start.getMatchChecksum()) && defuse.getRoundNumber() == start.getRoundNumber()))
                .count();
            return Map.entry(playerName, fakeDefuse);
            })
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostfakedefuse",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostfakedefuse","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processChicken (List<ChickenDeaths> chickenDeaths){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más pollos asesinados
        titlesList.add(chickenDeaths.stream()
            .filter(chicken -> Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID)
                .contains(chicken.getKillerSteamId()))
            .collect(Collectors.groupingBy(ChickenDeaths::getKillerSteamId, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostchicken",utils.convertSteamIdToName(entry.getKey()),entry.getValue()))
            .orElse(formatTitle("mostchicken","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processPlayerEconomies (List<PlayerEconomies> playerEconomies){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más dinero gastado
        titlesList.add(playerEconomies.stream()
            .filter(economy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(economy.getPlayerName()))
            .collect(Collectors.groupingBy(PlayerEconomies::getPlayerName, Collectors.summingInt(PlayerEconomies::getMoneySpent)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostspent",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("mostspent","-","-")));

        //  Título de menos dinero gastado
        titlesList.add(playerEconomies.stream()
            .filter(economy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(economy.getPlayerName()))
            .collect(Collectors.groupingBy(PlayerEconomies::getPlayerName, Collectors.summingInt(PlayerEconomies::getMoneySpent)))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("lessspent",entry.getKey(),String.format("%,d",entry.getValue())))
            .orElse(formatTitle("lessspent","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processRefunds (List<StatisticDTO> playerRefundsData){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más devoluciones
        titlesList.add(playerRefundsData.stream()
        .max((r1, r2) -> Integer.compare(Integer.valueOf(r1.getValue().toString()), Integer.valueOf(r2.getValue().toString())))
        .map(stat -> formatTitle("mostrefunds", stat.getPlayer(), String.format("%,d", Integer.valueOf(stat.getValue().toString()))))
        .orElse(formatTitle("mostrefunds","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processFastestKill (StatisticDTO fastestKillData){
            
            List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();
    
            //  Título de la eliminación más rápida
            titlesList.add(formatTitle("fastestkill", fastestKillData.getPlayer(), String.format("%.2f", Double.valueOf(fastestKillData.getValue().toString())/64)));
    
            return titlesList;
    }

    private List<FullTitleDTO> processFastestDeath (StatisticDTO fastestDeathData){
            
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de la eliminación más rápida
        titlesList.add(formatTitle("fastestdeath", fastestDeathData.getPlayer(), String.format("%.2f", Double.valueOf(fastestDeathData.getValue().toString())/64)));

        return titlesList;
}

    private List<FullTitleDTO> processShots (List<StatisticDTO> shots){
        
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más tiros
        titlesList.add(shots.stream()
        .max((s1, s2) -> Integer.compare(Integer.valueOf(s1.getValue().toString()), Integer.valueOf(s2.getValue().toString())))
        .map(stat -> formatTitle("mostshots", stat.getPlayer(), String.format("%,d", Integer.valueOf(stat.getValue().toString()))))
        .orElse(formatTitle("mostshots","-","-")));

        //  Título de menos tiros
        titlesList.add(shots.stream()
        .min((s1, s2) -> Integer.compare(Integer.valueOf(s1.getValue().toString()), Integer.valueOf(s2.getValue().toString())))
        .map(stat -> formatTitle("lessshots", stat.getPlayer(), String.format("%,d", Integer.valueOf(stat.getValue().toString()))))
        .orElse(formatTitle("lessshots","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processBounces (List<StatisticDTO> bouncesData){
        
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más rebotes de granadas
        titlesList.add(bouncesData.stream()
        .max((b1, b2) -> Integer.compare(Integer.valueOf(b1.getValue().toString()), Integer.valueOf(b2.getValue().toString())))
        .map(stat -> formatTitle("mostgrenadebounces", stat.getPlayer(), String.format("%,d", Integer.valueOf(stat.getValue().toString()))))
        .orElse(formatTitle("mostgrenadebounces","-","-")));

        return titlesList;
    }

    private List<FullTitleDTO> processWastes (List<StatisticDTO> playerGrenadeBuysData, List<StatisticDTO> grenadeThrowsData){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();
        
        // Calculando desperdicios a partir de compras y lanzamientos
        Map<String, Integer> wastesMap = playerGrenadeBuysData.stream()
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

        //  Título de mejor precisión
        titlesList.add(wastesMap.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> formatTitle("mostunusedgrenades", entry.getKey(), String.format("%,d", entry.getValue())))
            .orElse(formatTitle("mostunusedgrenades","-","-")));

        return titlesList;
    }

    // Método para formatear los títulos como un objeto FullTitleDTO
    private FullTitleDTO formatTitle(String titleCode, String player, Object value){
        return FullTitleDTO.builder()
            .title(titles.getTitlesMap().get(titleCode).getTitle())
            .description(titles.getTitlesMap().get(titleCode).getDescription())
            .player(player)
            .icon(titles.getTitlesMap().get(titleCode).getIcon())
            .valueString(value.equals("-")?"-":
                (value.toString() + " " + titles.getTitlesMap().get(titleCode).getUnit()))
            .build();
    }
}