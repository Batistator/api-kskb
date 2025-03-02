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
import com.batistes.kskb.api.entity.BombsDefused;
import com.batistes.kskb.api.entity.BombsPlanted;
import com.batistes.kskb.api.entity.ChickenDeaths;
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
import com.batistes.kskb.api.repository.RoundsRepository;
import com.batistes.kskb.api.repository.ShotsRepository;
import com.batistes.kskb.api.util.Constants;

@Service
public class TitlesService {
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
    private RoundsRepository roundsRepository;

    @Autowired
    private GrenadeBouncesRepository grenadeBouncesRepository;

    @Autowired
    private PlayerBuysRepository playerBuysRepository;

    @Autowired
    private Constants constants;

    public List<FullTitleDTO> findKillsTitle(Date startDate, Date endDate){

        final Logger logger = LoggerFactory.getLogger(AuthController.class);
        
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
        List<Damages> damages = damagesRepository.findByAttackerSteamIdOrVictimSteamIdInBetweenDates(
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID, constants.SWAGCHAN_STEAM_ID),
            startDate, endDate);
        
        logger.info("Damages data Finished");
        List<GrenadeBounces> grenadeBounces = grenadeBouncesRepository.findByThrowerNameInBetweenDates(
            Arrays.asList(constants.PLAYER_SHINCHAN, constants.PLAYER_NENE, constants.PLAYER_KAZAMA, constants.PLAYER_MAFIOS, constants.PLAYER_SWAGCHAN),
            startDate, endDate);
        logger.info("Grenade bounces data Finished");

        List<PlayerBuys> grenadeBuys = playerBuysRepository.findByPlayerNameInBetweenDates(
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
}
