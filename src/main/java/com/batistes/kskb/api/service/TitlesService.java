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

import com.batistes.kskb.api.controller.AuthController;
import com.batistes.kskb.api.dto.FullTitleDTO;
import com.batistes.kskb.api.entity.BombsDefuseStart;
import com.batistes.kskb.api.entity.BombsDefused;
import com.batistes.kskb.api.entity.BombsPlantStart;
import com.batistes.kskb.api.entity.BombsPlanted;
import com.batistes.kskb.api.entity.ChickenDeaths;
import com.batistes.kskb.api.entity.Clutches;
import com.batistes.kskb.api.entity.Damages;
import com.batistes.kskb.api.entity.GrenadeBounces;
import com.batistes.kskb.api.entity.GrenadeProjectilesDestroy;
import com.batistes.kskb.api.entity.Kills;
import com.batistes.kskb.api.entity.PlayerBlinds;
import com.batistes.kskb.api.entity.PlayerBuys;
import com.batistes.kskb.api.entity.PlayerEconomies;
import com.batistes.kskb.api.entity.Players;
import com.batistes.kskb.api.entity.Rounds;
import com.batistes.kskb.api.entity.Shots;
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

    final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public List<FullTitleDTO> findKillsTitle(Date startDate, Date endDate){

        
        
        logger.info("Data retrieve start");
        List<Players> players = playersRepository.findByNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);

        logger.info("Players data Finished");
        List<Kills> kills = killsRepository.findByKillerNameOrAssisterNameOrVictimNameBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);

        logger.info("Kills data Finished");        
        List<BombsDefused> bombsDefused = bombsDefusedRepository.findByDefuserNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Bombs defused data Finished");     
        List<BombsPlanted> bombsPlanted = bombsPlantedRepository.findByPlanterNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Bombs planted start data Finished");
        List<BombsDefuseStart> bombsDefuseStart = bombsDefuseStartRepository.findByDefuserNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Bombs defused start data Finished");     
        List<BombsPlantStart> bombsPlantStart = bombsPlantStartRepository.findByPlanterNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Bombs planted data Finished");
        List<GrenadeProjectilesDestroy> grenades = grenadeProjectilesDestroyRepository.findByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Grenade projectile destroy data Finished");
        List<PlayerEconomies> playerEconomies = playerEconomiesRepository.findByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
  
        logger.info("Player economies data Finished");  
        List<Shots> shots = shotsRepository.findByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Shots data Finished");
        List<ChickenDeaths> chickenDeaths = chickenDeathsRepository.findByKillerSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        
        logger.info("Chicken deaths data Finished");        
        List<PlayerBlinds> playerBlinds = playerBlindsRepository.findByKillerNameOrAssisterNameOrVictimNameBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        
        logger.info("Player blinds data Finished");
        List<PlayerBuys> playerBuys = playerBuysRepository.findByPlayerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        
        logger.info("Player buys data Finished");
        List<Damages> damages = damagesRepository.findByAttackerSteamIdOrVictimSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        
        logger.info("Damages data Finished");
        List<GrenadeBounces> grenadeBounces = grenadeBouncesRepository.findByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("Grenade bounces data Finished");

        List<Clutches> clutches = clutchesRepository.findByClutcherNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
           startDate, endDate);
        logger.info("Player buys data Finished");

        List<Rounds> rounds = roundsRepository.findAll();
        logger.info("Rounds data Finished");

        logger.info("SQL Finished");

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        titlesList.addAll(filterKills(kills, rounds));
        titlesList.addAll(filterPlayers(players));
        titlesList.addAll(filterGrenades(grenades));
        titlesList.addAll(filterPrecision(shots, damages));
        titlesList.addAll(filterClutches(clutches));
        titlesList.addAll(filterBlinds(playerBlinds));
        titlesList.addAll(filterPlants(bombsPlanted));
        titlesList.addAll(filterDefuses(bombsDefused));
        titlesList.addAll(filterFakePlants(bombsPlanted,bombsPlantStart));
        titlesList.addAll(filterFakeDefuse(bombsDefused,bombsDefuseStart));
        titlesList.addAll(filterChicken(chickenDeaths));
        titlesList.addAll(filterPlayerBuys(playerBuys));
        titlesList.addAll(filterPlayerEconomies(playerEconomies));
        titlesList.addAll(filterShots(shots));
        titlesList.addAll(filterDesperdicios(playerBuys,grenades));
        titlesList.addAll(filterBounces(grenadeBounces));

        return titlesList;
    }

    private List<FullTitleDTO> filterKills (List<Kills> kills, List<Rounds> rounds){

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
            .map(entry -> 
            FullTitleDTO.builder()
                .title("La mula de carga")
                .description("Más eliminaciones")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más muertes
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El mártir")
                .description("Más muertes")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Muertes")
                .build())
            .orElse(null));

        //  Título de más asistencias
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getAssisterName()))
            .filter(kill -> kill.getAssisterName() != null)
            .collect(Collectors.groupingBy(Kills::getAssisterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
                FullTitleDTO.builder()
                .title("La enfermera")
                .description("Más asistencias")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Asistencias")
                .build())
            .orElse(null));

        //  Título de menos muertes
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El último superviviente")
                .description("Menos muertes")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Muertes")
                .build())
            .orElse(null));

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
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El neurocirujano")
                .description("Mejor porcentaje de tiros a la cabeza")
                .player(entry.getKey())
                .valueString(String.format("%.2f", entry.getValue()) + " %")
                .build())
            .orElse(null));

        //  Título de eliminación más rápida
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> !Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .flatMap(kill -> rounds.stream()
                .filter(round -> round.getMatchChecksum().equals(kill.getMatchChecksum()) && round.getNumber() == kill.getRoundNumber())
                .map(round -> Map.entry(kill.getKillerName(), (double) (kill.getTick() - round.getFreezeTimeEndTick()))))
            .min(Map.Entry.comparingByValue())
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El Lucky Luke")
                .description("La eliminación más rápida")
                .player(entry.getKey())
                .valueString(String.format("%.2f", entry.getValue() / 64) + " segundos")
                .build())
            .orElse(null));

        //  Título de muerte más rápida
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .filter(kill -> !Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .flatMap(kill -> rounds.stream()
                .filter(round -> round.getMatchChecksum().equals(kill.getMatchChecksum()) && round.getNumber() == kill.getRoundNumber())
                .map(round -> Map.entry(kill.getVictimName(), (double) (kill.getTick() - round.getFreezeTimeEndTick()))))
            .min(Map.Entry.comparingByValue())
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El Lemming")
                .description("La muerte más rápida")
                .player(entry.getKey())
                .valueString(String.format("%.2f", entry.getValue() / 64) + " segundos")
                .build())
            .orElse(null));

        //  Título de más muertes por C4
        titlesList.add(kills.stream()
        .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(kill.getVictimName()))
        .filter(kill -> kill.getWeaponName().equals("C4"))
        .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(entry -> 
            FullTitleDTO.builder()
            .title("El pasota")
            .description("Más veces muerto por C4")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Muertes")
            .build())
        .orElse(null));

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
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El traidor")
                .description("Más team kills")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones con granada
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("HE Grenade"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Personalidad Explosiva")
                .description("Más eliminaciones con granada")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones con fuego
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Incendiary Grenade") || kill.getWeaponName().equals("Molotov"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Ola de calor")
                .description("Más eliminaciones con fuego")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones con cuchillo
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Knife"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El afilador")
                .description("Más eliminaciones con cuchillo")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones con taser
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponName().equals("Zeus x27"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El Pikachu")
                .description("Más eliminaciones con taser")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones con escopeta
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getWeaponType().equals("shotgun"))
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El fotógrafo")
                .description("Más muertes con escopeta")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de la eliminación desde más lejos
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .max((kill1, kill2) -> Double.compare(kill1.getDistance(), kill2.getDistance()))
            .map(kill -> 
            FullTitleDTO.builder()
                .title("El ojo de halcón")
                .description("Eliminación a más distancia")
                .player(kill.getKillerName())
                .valueString(String.format("%.2f", kill.getDistance()) + " metros")
                .build())
            .orElse(null));

        //  Título de más trade kills
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isTradeKill())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El lobo de WallStreet")
                .description("Más trade kills")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más trade deaths
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getVictimName()))
            .filter(kill -> kill.isTradeDeath())
            .collect(Collectors.groupingBy(Kills::getVictimName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El cebo")
                .description("Más trade deaths")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Muertes")
                .build())
            .orElse(null));

        //  Título de más eliminaciones saltando
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isKillerAirborne())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El Legolas")
                .description("Más kills saltando")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones atravesando
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.getPenetratedObjects() > 0)
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El dildo")
                .description("Más muertes penetrando")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones traspasando humo
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isThroughSmoke())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El Snoop Dogg")
                .description("Más eliminaciones atravesando humo")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones no scope
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isNoScope())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Chin chin. Afflelou")
                .description("Más eliminaciones no scope")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más asistencias con flash
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getAssisterName()))
            .filter(kill -> kill.isAssistedFlash())
            .collect(Collectors.groupingBy(Kills::getAssisterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El farero")
                .description("Más asistencias con flash")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        //  Título de más eliminaciones ciego
        titlesList.add(kills.stream()
            .filter(kill -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(kill.getKillerName()))
            .filter(kill -> kill.isKillerBlinded())
            .collect(Collectors.groupingBy(Kills::getKillerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Corto de vista")
                .description("Más eliminaciones estando ciego")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Eliminaciones")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterPlayers (List<Players> players){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más 5K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFiveKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Ace Ventura")
                .description("Más veces 5K")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rachas")
                .build())
            .orElse(null));

        //  Título de más 4K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFourKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Ultra HD")
                .description("Más veces 4K")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rachas")
                .build())
            .orElse(null));

        //  Título de más 3K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getThreeKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Mr. Triplete")
                .description("Más veces 3K")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rachas")
                .build())
            .orElse(null));

        //  Título de más 2K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getTwoKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Efecto 2000")
                .description("Más veces 2K")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rachas")
                .build())
            .orElse(null));

        //  Título de más 1K
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getOneKillCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El humilde")
                .description("Más veces 1K")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rachas")
                .build())
            .orElse(null));

        //  Título de más MVP
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getMvpCount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El musiquitas")
                .description("Más MVPs")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " MVPs")
                .build())
            .orElse(null));

        //  Título de más daño diverso
        titlesList.add(players.stream()
            .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(player.getName()))
            .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getUtilityDamage)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Viva la diversidad ")
                .description("Más daño diverso")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Daño")
                .build())
            .orElse(null));

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
            .map(entry -> 
                FullTitleDTO.builder()
                .title("El anti-ShinChan")
                .description("Por encima de ShinChan")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " veces")
                .build())
            .orElse(null));

        //  Título de más entry kills
        titlesList.add(players.stream()
        .filter(player -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(player.getName()))
        .collect(Collectors.groupingBy(Players::getName, Collectors.summingInt(Players::getFirstKillCount)))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(entry -> 
        FullTitleDTO.builder()
            .title("El derriba-puertas")
            .description("Más entry kills")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Eliminaciones")
            .build())
        .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterPrecision (List<Shots> shots, List<Damages> damages){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();
        
        //  Título de mejor precisión
        titlesList.add(damages.stream()
        .filter(damage -> Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID)
            .contains(damage.getAttackerSteamId()))
        .filter(damage -> !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName()))
        .collect(Collectors.groupingBy(Damages::getAttackerSteamId, Collectors.counting()))
        .entrySet()
        .stream()
        .map(entry -> {
            String playerId = entry.getKey();
            long totalShots = shots.stream()
                .filter(shot -> shot.getPlayerSteamId().equals(playerId))
                .count();
            double precision = (double) entry.getValue() / totalShots * 100;
            return Map.entry(playerId, precision);
        })
        .max(Map.Entry.comparingByValue())
        .map(entry -> 
            FullTitleDTO.builder()
                .title("Enhebrando la aguja")
                .description("Mayor precisión")
                .player(utils.convertSteamIdToName(entry.getKey()))
                .valueString(String.format("%.2f", entry.getValue()) + " %")
                .build())
        .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterGrenades (List<GrenadeProjectilesDestroy> grenades){
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
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El Granaino")
                .description("Tira más granadas HE")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Granadas")
                .build())
            .orElse(null));

        //  Título de más decoys tiradas
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Decoy Grenade"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El trilero")
                .description("Tira más granadas decoy")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Decoys")
                .build())
            .orElse(null));

        //  Título de más flashes tiradas
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Flashbang"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Flashdance")
                .description("Tira más granadas flash")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Flashes")
                .build())
            .orElse(null));

        //  Título de más humos tirados
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> grenade.getGrenadeName().equals("Smoke Grenade"))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("SmokeHouse")
                .description("Tira más granadas de humo")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Humos")
                .build())
            .orElse(null));

        //  Título de más fuegos tirados
        titlesList.add(grenades.stream()
            .filter(grenade -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(grenade.getThrowerName()))
            .filter(grenade -> (grenade.getGrenadeName().equals("Molotov") || grenade.getGrenadeName().equals("Incendiary Grenade")))
            .collect(Collectors.groupingBy(GrenadeProjectilesDestroy::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Hot And Spicy")
                .description("Tira más molotov o incend.")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Fuegos")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterClutches (List<Clutches> clutches){
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
        .map(entry -> 
        FullTitleDTO.builder()
            .title("El John Wick")
            .description("Más clutches ganados")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Clutches")
            .build())
        .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterBlinds (List<PlayerBlinds> playerBlinds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de la flash más duradera
        titlesList.add(playerBlinds.stream()
            .filter(playerBlind -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(playerBlind.getFlasherName()))
            .max((playerBlind1, playerBlind2) -> Double.compare(playerBlind1.getDuration(), playerBlind2.getDuration()))
            .map(playerBlind -> 
            FullTitleDTO.builder()
                .title("Taiyoken")
                .description("Flash más duradera")
                .player(playerBlind.getFlasherName())
                .valueString(String.format("%.2f", playerBlind.getDuration()) + " segundos")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterDefuses (List<BombsDefused> bombsDefuseds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más defuses
        titlesList.add(bombsDefuseds.stream()
            .filter(defuse -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(defuse.getDefuserName()))
            .collect(Collectors.groupingBy(BombsDefused::getDefuserName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El ingeniero")
                .description("Más veces defusa")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Defuses")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterPlants (List<BombsPlanted> bombsPlanteds){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más plants
        titlesList.add(bombsPlanteds.stream()
            .filter(plant -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(plant.getPlanterName()))
            .collect(Collectors.groupingBy(BombsPlanted::getPlanterName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El botánico")
                .description("Más veces planta")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Bombas")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterFakePlants (List<BombsPlanted> bombsPlanteds, List<BombsPlantStart> bombsPlantStart){
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
            .map(entry -> 
            FullTitleDTO.builder()
            .title("La planta mentirosa")
            .description("Más fakes al plantar")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Fakes")
            .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterFakeDefuse (List<BombsDefused> bombsDefused, List<BombsDefuseStart> bombsDefusedStart){
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
            .map(entry -> 
            FullTitleDTO.builder()
            .title("El ingeniero mentiroso")
            .description("Más fakes al defusar")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Fakes")
            .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterChicken (List<ChickenDeaths> chickenDeaths){
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más pollos asesinados
        titlesList.add(chickenDeaths.stream()
            .filter(chicken -> Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID)
                .contains(chicken.getKillerSteamId()))
            .collect(Collectors.groupingBy(ChickenDeaths::getKillerSteamId, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("Coronel Sanders")
                .description("Más pollos asesinados")
                .player(utils.convertSteamIdToName(entry.getKey()))
                .valueString(entry.getValue().toString() + " pollos")
                .build())
            .orElse(null));

        return titlesList;
    }

    private List<FullTitleDTO> filterPlayerEconomies (List<PlayerEconomies> playerEconomies){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más dinero gastado
        titlesList.add(playerEconomies.stream()
            .filter(economy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(economy.getPlayerName()))
            .collect(Collectors.groupingBy(PlayerEconomies::getPlayerName, Collectors.summingInt(PlayerEconomies::getMoneySpent)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El millonetis")
                .description("Más dinero gastado")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " $")
                .build())
            .orElse(null));

        //  Título de menos dinero gastado
        titlesList.add(playerEconomies.stream()
            .filter(economy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(economy.getPlayerName()))
            .collect(Collectors.groupingBy(PlayerEconomies::getPlayerName, Collectors.summingInt(PlayerEconomies::getMoneySpent)))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El puño cerrao")
                .description("Menos dinero gastado")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " $")
                .build())
            .orElse(null));
            
        return titlesList;
    }

    private List<FullTitleDTO> filterPlayerBuys (List<PlayerBuys> playerBuys){

        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más devoluciones de armas
        titlesList.add(playerBuys.stream()
            .filter(buy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(buy.getPlayerName()))
            .filter(buy -> buy.isHasRefunded())
            .collect(Collectors.groupingBy(PlayerBuys::getPlayerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El cliente insatisfecho")
                .description("Más devoluciones")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Devoluciones")
                .build())
            .orElse(null));
            
        return titlesList;
    }

    private List<FullTitleDTO> filterShots (List<Shots> shots){
        
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más tiros
        titlesList.add(shots.stream()
            .filter(shot -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(shot.getPlayerName()))
            .collect(Collectors.groupingBy(Shots::getPlayerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El derrochador de balas")
                .description("Más disparos en total")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Disparos")
                .build())
            .orElse(null));

        //  Título de menos tiros
        titlesList.add(shots.stream()
            .filter(shot -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(shot.getPlayerName()))
            .collect(Collectors.groupingBy(Shots::getPlayerName, Collectors.counting()))
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El mosquetero")
                .description("Menos disparos en total")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Disparos")
                .build())
            .orElse(null));
            
        return titlesList;
    }

    private List<FullTitleDTO> filterBounces (List<GrenadeBounces> bounces){
        
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más rebotes de granadas
        titlesList.add(bounces.stream()
            .filter(bounce -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
                .contains(bounce.getThrowerName()))
            .collect(Collectors.groupingBy(GrenadeBounces::getThrowerName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
                .title("El pinball")
                .description("Más rebotes con granadas")
                .player(entry.getKey())
                .valueString(entry.getValue().toString() + " Rebotes")
                .build())
            .orElse(null));
            
        return titlesList;
    }

    private List<FullTitleDTO> filterDesperdicios (List<PlayerBuys> playerBuys, List<GrenadeProjectilesDestroy> grenades){
        
        List<FullTitleDTO> titlesList = new ArrayList<FullTitleDTO>();

        //  Título de más granadas desperdiciadas
        titlesList.add(playerBuys.stream()
            .filter(buy -> Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN)
            .contains(buy.getPlayerName()))
            .filter(buy -> !buy.isHasRefunded())
            .collect(Collectors.groupingBy(PlayerBuys::getPlayerName, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> {
            String playerName = entry.getKey();
            long grenadesThrown = grenades.stream()
            .filter(grenade -> grenade.getThrowerName().equals(playerName))
            .count();
            long grenadesWasted = entry.getValue() - grenadesThrown;
            return Map.entry(playerName, grenadesWasted);
            })
            .max(Map.Entry.comparingByValue())
            .map(entry -> 
            FullTitleDTO.builder()
            .title("Poco ecológico")
            .description("Más granadas no usadas")
            .player(entry.getKey())
            .valueString(entry.getValue().toString() + " Granadas")
            .build())
            .orElse(null));

        return titlesList;
    }
}
