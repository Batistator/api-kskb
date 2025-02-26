package com.batistes.kskb.api.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    //TODO: Optimizar método de obtención de datos.

    public String getTotalData(Date startDate, Date endDate){

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

        logger.info("SQL Finished");

        final Map<String, TotalDataDTO> totalData = new HashMap<>();

        totalData.putAll(this.filterKills(kills));
        totalData.putAll(this.filterPlayers(players));
        totalData.putAll(this.filterBombsDefused(bombsDefused));
        totalData.putAll(this.filterBombsPlanted(bombsPlanted));
        totalData.putAll(this.filterGrenades(grenades));
        totalData.putAll(this.filterPlayerEconomies(playerEconomies));
        totalData.putAll(this.filterShots(shots, damages));
        totalData.putAll(this.filterChickens(chickenDeaths));
        totalData.putAll(this.filterBounces(grenadeBounces));
        totalData.putAll(this.filterDamages(damages));
        totalData.putAll(this.filterBlinds(playerBlinds));

        logger.info("Java process Finished");
        
        List<TotalDataDTO> totalDataList = new ArrayList<>();
        for (Entry<String, TotalDataDTO> entry : totalData.entrySet()) {
            TotalDataDTO dto = entry.getValue();
            dto.setData(entry.getKey());
            totalDataList.add(dto);
        }

        String jsonResult = "";
        try {
            jsonResult = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(totalDataList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error de conversión JSON";
        }

        return jsonResult;
    }

    private Map<String, TotalDataDTO> filterBlinds (List<PlayerBlinds> totalBlinds){
        Map<String, TotalDataDTO> blindsMap = new HashMap<>();

        TotalDataDTO blinds = new TotalDataDTO();
        blinds.setIcon("./icons/total_data/flash_enemies.png");
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
        blindsMap.put("Enemigos flasheados", blinds);

        blinds = new TotalDataDTO();
        blinds.setIcon("./icons/total_data/flash_allies.png");
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
        blindsMap.put("Aliados flasheados", blinds);

        blinds = new TotalDataDTO();
        blinds.setIcon("./icons/total_data/max_blind.png");
        blinds.setShinchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SHINCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)));
        blinds.setKazama(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_KAZAMA.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)));
        blinds.setMafios(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_MAFIOS.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_NENE, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_SHINCHAN).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)));
        blinds.setNene(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_NENE.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_SWAGCHAN, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)));
        blinds.setSwagchan(String.valueOf(totalBlinds.stream()
            .filter(blind -> constants.PLAYER_SWAGCHAN.equals(blind.getFlasherName()) && !Arrays.asList(
                constants.PLAYER_NENE, 
                constants.PLAYER_SHINCHAN, 
                constants.PLAYER_KAZAMA, 
                constants.PLAYER_MAFIOS).contains(blind.getFlashedName()))
            .mapToDouble(PlayerBlinds::getDuration)
            .max()
            .orElse(0)));
        blindsMap.put("Máximo flasheo (seg)", blinds);

        return blindsMap;
    }

    private Map<String, TotalDataDTO> filterDamages (List<Damages> totalDamages){
        Map<String, TotalDataDTO> damagesMap = new HashMap<>();
        
        TotalDataDTO damages = new TotalDataDTO();
        damages.setIcon("./icons/total_data/ally_damage.png"); 
        damages.setShinchan(String.valueOf(totalDamages.stream()
            .filter(damage -> constants.SHINCHAN_STEAM_ID.equals(damage.getAttackerSteamId()) &&
            Arrays.asList(constants.SWAGCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID).contains(damage.getVictimSteamId()))
            .mapToInt(damage -> Integer.parseInt(damage.getArmorDamage()) + Integer.parseInt(damage.getHealthDamage())).sum()));
        damages.setKazama(String.valueOf(totalDamages.stream()
            .filter(damage -> constants.KAZAMA_STEAM_ID.equals(damage.getAttackerSteamId()) &&
            Arrays.asList(constants.SWAGCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.SHINCHAN_STEAM_ID, constants.MAFIOS_STEAM_ID).contains(damage.getVictimSteamId()))
            .mapToInt(damage -> Integer.parseInt(damage.getArmorDamage()) + Integer.parseInt(damage.getHealthDamage())).sum()));
        damages.setMafios(String.valueOf(totalDamages.stream()
            .filter(damage -> constants.MAFIOS_STEAM_ID.equals(damage.getAttackerSteamId()) &&
            Arrays.asList(constants.SWAGCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.SHINCHAN_STEAM_ID).contains(damage.getVictimSteamId()))
            .mapToInt(damage -> Integer.parseInt(damage.getArmorDamage()) + Integer.parseInt(damage.getHealthDamage())).sum()));
        damages.setNene(String.valueOf(totalDamages.stream()
            .filter(damage -> constants.NENE_STEAM_ID.equals(damage.getAttackerSteamId()) &&
            Arrays.asList(constants.SWAGCHAN_STEAM_ID, constants.SHINCHAN_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID).contains(damage.getVictimSteamId()))
            .mapToInt(damage -> Integer.parseInt(damage.getArmorDamage()) + Integer.parseInt(damage.getHealthDamage())).sum()));
        damages.setSwagchan(String.valueOf(totalDamages.stream()
            .filter(damage -> constants.SWAGCHAN_STEAM_ID.equals(damage.getAttackerSteamId()) &&
            Arrays.asList(constants.SHINCHAN_STEAM_ID, constants.NENE_STEAM_ID, constants.KAZAMA_STEAM_ID, constants.MAFIOS_STEAM_ID).contains(damage.getVictimSteamId()))
            .mapToInt(damage -> Integer.parseInt(damage.getArmorDamage()) + Integer.parseInt(damage.getHealthDamage())).sum()));
        damagesMap.put("Daño a aliados", damages);
        
        return damagesMap;
    }

    private Map<String, TotalDataDTO> filterBounces (List<GrenadeBounces> totalgrenadeBounces){
        Map<String, TotalDataDTO> bouncesMap = new HashMap<>();

        TotalDataDTO bounces = new TotalDataDTO();
        bounces.setIcon("./icons/total_data/bounces.png");
        bounces.setShinchan(String.valueOf(totalgrenadeBounces.stream()
            .filter(bounce -> constants.PLAYER_SHINCHAN.equals(bounce.getThrowerName()))
            .count()));
        bounces.setKazama(String.valueOf(totalgrenadeBounces.stream()
            .filter(bounce -> constants.PLAYER_KAZAMA.equals(bounce.getThrowerName()))
            .count()));
        bounces.setMafios(String.valueOf(totalgrenadeBounces.stream()
            .filter(bounce -> constants.PLAYER_MAFIOS.equals(bounce.getThrowerName()))
            .count()));
        bounces.setNene(String.valueOf(totalgrenadeBounces.stream()
            .filter(bounce -> constants.PLAYER_NENE.equals(bounce.getThrowerName()))
            .count()));
        bounces.setSwagchan(String.valueOf(totalgrenadeBounces.stream()
            .filter(bounce -> constants.PLAYER_SHINCHAN.equals(bounce.getThrowerName()))
            .count()));
        bouncesMap.put("Rebotes de granadas", bounces);

        return bouncesMap;
    }

    private Map<String, TotalDataDTO> filterChickens (List<ChickenDeaths> totalChickenDeaths){
        Map<String, TotalDataDTO> chickenMap = new HashMap<>();

        TotalDataDTO chickens = new TotalDataDTO();
        chickens.setIcon("./icons/total_data/chicken_deaths.png");
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
        chickenMap.put("Pollos asesinados", chickens);

        return chickenMap;
    }

    private Map<String, TotalDataDTO> filterShots (List<Shots> totalShots, List<Damages> totalDamages){
        Map<String, TotalDataDTO> shotsMap = new HashMap<>();

        TotalDataDTO shots = new TotalDataDTO();
        shots.setIcon("./icons/total_data/shots.png");
        shots.setShinchan(String.valueOf(totalShots.stream()
            .filter(shot -> constants.PLAYER_SHINCHAN.equals(shot.getPlayerName()))
            .count()));
        shots.setKazama(String.valueOf(totalShots.stream()
            .filter(shot -> constants.PLAYER_KAZAMA.equals(shot.getPlayerName()))
            .count()));
        shots.setMafios(String.valueOf(totalShots.stream()
            .filter(shot -> constants.PLAYER_MAFIOS.equals(shot.getPlayerName()))
            .count()));
        shots.setNene(String.valueOf(totalShots.stream()
            .filter(shot -> constants.PLAYER_NENE.equals(shot.getPlayerName()))
            .count()));
        shots.setSwagchan(String.valueOf(totalShots.stream()
            .filter(shot -> constants.PLAYER_SWAGCHAN.equals(shot.getPlayerName()))
            .count()));
        shotsMap.put("Disparos", shots);

        TotalDataDTO precision = new TotalDataDTO();
        precision.setIcon("./icons/total_data/precision.png");
        precision.setShinchan(String.format("%.2f",
            Double.valueOf(totalDamages.stream()
            .filter(damage -> constants.SHINCHAN_STEAM_ID.equals(damage.getAttackerSteamId()) && 
            !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName())).count()) / Double.valueOf(shots.getShinchan()) * 100));
        precision.setKazama(String.format("%.2f",
            Double.valueOf(totalDamages.stream()
            .filter(damage -> constants.KAZAMA_STEAM_ID.equals(damage.getAttackerSteamId()) && 
            !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName())).count()) / Double.valueOf(shots.getKazama()) * 100));
        precision.setMafios(String.format("%.2f",
            Double.valueOf(totalDamages.stream()
            .filter(damage -> constants.MAFIOS_STEAM_ID.equals(damage.getAttackerSteamId()) && 
            !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName())).count()) / Double.valueOf(shots.getMafios()) * 100));
        precision.setNene(String.format("%.2f",
            Double.valueOf(totalDamages.stream()
            .filter(damage -> constants.NENE_STEAM_ID.equals(damage.getAttackerSteamId()) && 
            !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName())).count()) / Double.valueOf(shots.getNene()) * 100));
        precision.setSwagchan(String.format("%.2f",
            Double.valueOf(totalDamages.stream()
            .filter(damage -> constants.SWAGCHAN_STEAM_ID.equals(damage.getAttackerSteamId()) && 
            !Arrays.asList("HE Grenade", "Incendiary Grenade", "Molotov").contains(damage.getWeaponName())).count()) / Double.valueOf(shots.getSwagchan()) * 100));
        shotsMap.put("Precisión", precision);

        return shotsMap;
    }

    private Map<String, TotalDataDTO> filterPlayerEconomies (List<PlayerEconomies> totalEconomies){
        Map<String, TotalDataDTO> economiesMap = new HashMap<>();
        
        TotalDataDTO economies = new TotalDataDTO();
        economies.setIcon("./icons/total_data/money_spent.png");
        economies.setShinchan(String.valueOf(totalEconomies.stream()
            .filter(economy -> constants.PLAYER_SHINCHAN.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum()));
        economies.setKazama(String.valueOf(totalEconomies.stream()
            .filter(economy -> constants.PLAYER_KAZAMA.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum()));
        economies.setMafios(String.valueOf(totalEconomies.stream()
            .filter(economy -> constants.PLAYER_MAFIOS.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum()));
        economies.setNene(String.valueOf(totalEconomies.stream()
            .filter(economy -> constants.PLAYER_NENE.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum()));
        economies.setSwagchan(String.valueOf(totalEconomies.stream()
            .filter(economy -> constants.PLAYER_SWAGCHAN.equals(economy.getPlayerName()))
            .mapToInt(PlayerEconomies::getMoneySpent).sum()));
        economiesMap.put("Dinero gastado", economies);
        
        return economiesMap;
    }

    private Map<String, TotalDataDTO> filterGrenades (List<GrenadeProjectilesDestroy> totalGrenades){
        Map<String, TotalDataDTO> grenadesMap = new HashMap<>();

        TotalDataDTO flashes = new TotalDataDTO();
        flashes.setIcon("./icons/total_data/flashes.png");
        flashes.setShinchan(String.valueOf(totalGrenades.stream()
            .filter(flash -> constants.PLAYER_SHINCHAN.equals(flash.getThrowerName()) && "Flashbang".equals(flash.getGrenadeName()))
            .count()));
        flashes.setKazama(String.valueOf(totalGrenades.stream()
            .filter(flash -> constants.PLAYER_KAZAMA.equals(flash.getThrowerName()) && "Flashbang".equals(flash.getGrenadeName()))
            .count()));
        flashes.setMafios(String.valueOf(totalGrenades.stream()
            .filter(flash -> constants.PLAYER_MAFIOS.equals(flash.getThrowerName()) && "Flashbang".equals(flash.getGrenadeName()))
            .count()));
        flashes.setNene(String.valueOf(totalGrenades.stream()
            .filter(flash -> constants.PLAYER_NENE.equals(flash.getThrowerName()) && "Flashbang".equals(flash.getGrenadeName()))
            .count()));
        flashes.setSwagchan(String.valueOf(totalGrenades.stream()
            .filter(flash -> constants.PLAYER_SWAGCHAN.equals(flash.getThrowerName()) && "Flashbang".equals(flash.getGrenadeName()))
            .count()));
        grenadesMap.put("Flashes lanzadas", flashes);

        TotalDataDTO smokes = new TotalDataDTO();
        smokes.setIcon("./icons/total_data/smokes.png");
        smokes.setShinchan(String.valueOf(totalGrenades.stream()
            .filter(smoke -> constants.PLAYER_SHINCHAN.equals(smoke.getThrowerName()) && "Smoke Grenade".equals(smoke.getGrenadeName()))
            .count()));
        smokes.setKazama(String.valueOf(totalGrenades.stream()
            .filter(smoke -> constants.PLAYER_KAZAMA.equals(smoke.getThrowerName()) && "Smoke Grenade".equals(smoke.getGrenadeName()))
            .count()));
        smokes.setMafios(String.valueOf(totalGrenades.stream()
            .filter(smoke -> constants.PLAYER_MAFIOS.equals(smoke.getThrowerName()) && "Smoke Grenade".equals(smoke.getGrenadeName()))
            .count()));
        smokes.setNene(String.valueOf(totalGrenades.stream()
            .filter(smoke -> constants.PLAYER_NENE.equals(smoke.getThrowerName()) && "Smoke Grenade".equals(smoke.getGrenadeName()))
            .count()));
        smokes.setSwagchan(String.valueOf(totalGrenades.stream()
            .filter(smoke -> constants.PLAYER_SWAGCHAN.equals(smoke.getThrowerName()) && "Smoke Grenade".equals(smoke.getGrenadeName()))
            .count()));
        grenadesMap.put("Humos lanzados", smokes);

        TotalDataDTO hes = new TotalDataDTO();
        hes.setIcon("./icons/total_data/he_grenades.png");
        hes.setShinchan(String.valueOf(totalGrenades.stream()
            .filter(he -> constants.PLAYER_SHINCHAN.equals(he.getThrowerName()) && "HE Grenade".equals(he.getGrenadeName()))
            .count()));
        hes.setKazama(String.valueOf(totalGrenades.stream()
            .filter(he -> constants.PLAYER_KAZAMA.equals(he.getThrowerName()) && "HE Grenade".equals(he.getGrenadeName()))
            .count()));
        hes.setMafios(String.valueOf(totalGrenades.stream()
            .filter(he -> constants.PLAYER_MAFIOS.equals(he.getThrowerName()) && "HE Grenade".equals(he.getGrenadeName()))
            .count()));
        hes.setNene(String.valueOf(totalGrenades.stream()
            .filter(he -> constants.PLAYER_NENE.equals(he.getThrowerName()) && "HE Grenade".equals(he.getGrenadeName()))
            .count()));
        hes.setSwagchan(String.valueOf(totalGrenades.stream()
            .filter(he -> constants.PLAYER_SWAGCHAN.equals(he.getThrowerName()) && "HE Grenade".equals(he.getGrenadeName()))
            .count()));
        grenadesMap.put("HEs lanzadas", hes);

        TotalDataDTO decoys = new TotalDataDTO();
        decoys.setIcon("./icons/total_data/decoys.png");
        decoys.setShinchan(String.valueOf(totalGrenades.stream()
            .filter(decoy -> constants.PLAYER_SHINCHAN.equals(decoy.getThrowerName()) && "Decoy Grenade".equals(decoy.getGrenadeName()))
            .count()));
        decoys.setKazama(String.valueOf(totalGrenades.stream()
            .filter(decoy -> constants.PLAYER_KAZAMA.equals(decoy.getThrowerName()) && "Decoy Grenade".equals(decoy.getGrenadeName()))
            .count()));
        decoys.setMafios(String.valueOf(totalGrenades.stream()
            .filter(decoy -> constants.PLAYER_MAFIOS.equals(decoy.getThrowerName()) && "Decoy Grenade".equals(decoy.getGrenadeName()))
            .count()));
        decoys.setNene(String.valueOf(totalGrenades.stream()
            .filter(decoy -> constants.PLAYER_NENE.equals(decoy.getThrowerName()) && "Decoy Grenade".equals(decoy.getGrenadeName()))
            .count()));
        decoys.setSwagchan(String.valueOf(totalGrenades.stream()
            .filter(decoy -> constants.PLAYER_SWAGCHAN.equals(decoy.getThrowerName()) && "Decoy Grenade".equals(decoy.getGrenadeName()))
            .count()));
        grenadesMap.put("Decoys lanzadas", decoys);

        TotalDataDTO molotovs = new TotalDataDTO();
        molotovs.setIcon("./icons/total_data/molotovs.png");
        molotovs.setShinchan(String.valueOf(totalGrenades.stream()
            .filter(molotov -> constants.PLAYER_SHINCHAN.equals(molotov.getThrowerName()) && Arrays.asList("Molotov","Incendiary Grenade").contains(molotov.getGrenadeName()))
            .count()));
        molotovs.setKazama(String.valueOf(totalGrenades.stream()
            .filter(molotov -> constants.PLAYER_KAZAMA.equals(molotov.getThrowerName()) && Arrays.asList("Molotov","Incendiary Grenade").contains(molotov.getGrenadeName()))
            .count()));
        molotovs.setMafios(String.valueOf(totalGrenades.stream()
            .filter(molotov -> constants.PLAYER_MAFIOS.equals(molotov.getThrowerName()) && Arrays.asList("Molotov","Incendiary Grenade").contains(molotov.getGrenadeName()))
            .count()));
        molotovs.setNene(String.valueOf(totalGrenades.stream()
            .filter(molotov -> constants.PLAYER_NENE.equals(molotov.getThrowerName()) && Arrays.asList("Molotov","Incendiary Grenade").contains(molotov.getGrenadeName()))
            .count()));
        molotovs.setSwagchan(String.valueOf(totalGrenades.stream()
            .filter(molotov -> constants.PLAYER_SWAGCHAN.equals(molotov.getThrowerName()) && Arrays.asList("Molotov","Incendiary Grenade").contains(molotov.getGrenadeName()))
            .count()));
        grenadesMap.put("Molotovs lanzados", molotovs);

        return grenadesMap;
    }

    private Map<String, TotalDataDTO> filterBombsDefused (List<BombsDefused> totalBombsDefused){
        Map<String, TotalDataDTO> bombsDefusedMap = new HashMap<>();

        TotalDataDTO bombsDefused = new TotalDataDTO();
        bombsDefused.setIcon("./icons/total_data/defuses.png");
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
        bombsDefusedMap.put("Bombas defusadas", bombsDefused);

        return bombsDefusedMap;
    }

    private Map<String, TotalDataDTO> filterBombsPlanted (List<BombsPlanted> totalBombsPlanted){
        Map<String, TotalDataDTO> bombsPlantedMap = new HashMap<>();

        TotalDataDTO bombsPlanted = new TotalDataDTO();
        bombsPlanted.setIcon("./icons/total_data/c4_plants.png");
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
            bombsPlantedMap.put("Bombas plantadas", bombsPlanted);

        return bombsPlantedMap;
    }

    private Map<String, TotalDataDTO> filterPlayers (List<Players> totalPlayers){
        Map<String, TotalDataDTO> playersMap = new HashMap<>();

        TotalDataDTO players = new TotalDataDTO();
        players.setIcon("./icons/total_data/mvps.png");
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
        playersMap.put("MVPs", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/entry_kills.png");
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
        playersMap.put("Entry kills", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/health_damage.png");
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
        playersMap.put("Daño a vida", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/armor_damage.png");
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
        playersMap.put("Daño a armadura", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/5k.png");
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
        playersMap.put("5K", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/4k.png");
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
        playersMap.put("4K", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/3k.png");
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
        playersMap.put("3K", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/2k.png");
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
        playersMap.put("2K", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/1k.png");
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
        playersMap.put("1K", players);

        players = new TotalDataDTO();
        players.setIcon("./icons/total_data/kast.png");
        players.setShinchan(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_SHINCHAN.equals(player.getName())).count()));
        players.setKazama(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_KAZAMA.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_KAZAMA.equals(player.getName())).count()));
        players.setMafios(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_MAFIOS.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_MAFIOS.equals(player.getName())).count()));
        players.setNene(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_NENE.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_NENE.equals(player.getName())).count()));
        players.setSwagchan(String.format("%.2f",
        totalPlayers.stream().filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName()))
            .mapToDouble(Players::getKast).sum() /
        totalPlayers.stream().filter(player -> constants.PLAYER_SWAGCHAN.equals(player.getName())).count()));
        playersMap.put("% KAST", players);

        return playersMap;
    }

    private Map<String, TotalDataDTO> filterKills (List<Kills> totalKills){
        
        Map<String, TotalDataDTO> killsMap = new HashMap<>();
        
        TotalDataDTO kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/kills.png"); 
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
        killsMap.put("Eliminaciones", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/assists.png");
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
        killsMap.put("Asistencias", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/deaths.png");
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
        killsMap.put("Muertes", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/headshots.png");    
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
        killsMap.put("Headshots", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/headshot_rate.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("Headshots").getShinchan()) / Double.valueOf(killsMap.get("Eliminaciones").getShinchan())) * 100
        ));
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getKazama()) / Double.valueOf(killsMap.get("Eliminaciones").getKazama())) * 100
        ));
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getMafios()) / Double.valueOf(killsMap.get("Eliminaciones").getMafios())) * 100
        ));
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getNene()) / Double.valueOf(killsMap.get("Eliminaciones").getNene())) * 100
        ));
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("Headshots").getSwagchan()) / Double.valueOf(killsMap.get("Eliminaciones").getSwagchan())) * 100
        ));
        killsMap.put("% Headshots", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/kd_ratio.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf(killsMap.get("Eliminaciones").getShinchan()) / Double.valueOf(killsMap.get("Muertes").getShinchan())) * 100
        ));
        kills.setKazama(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getKazama()) / Double.valueOf(killsMap.get("Muertes").getKazama())) * 100
        ));
        kills.setMafios(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getMafios()) / Double.valueOf(killsMap.get("Muertes").getMafios())) * 100
        ));
        kills.setNene(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getNene()) / Double.valueOf(killsMap.get("Muertes").getNene())) * 100
        ));
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf(killsMap.get("Eliminaciones").getSwagchan()) / Double.valueOf(killsMap.get("Muertes").getSwagchan())) * 100
        ));
        killsMap.put("K/D Ratio", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/kda_ratio.png");
        kills.setShinchan(String.format("%.2f", 
            (Double.valueOf((killsMap.get("Eliminaciones").getShinchan() + killsMap.get("Asistencias").getShinchan())) / Double.valueOf(killsMap.get("Muertes").getShinchan())) * 100
        ));
        kills.setKazama(String.format("%.2f",
            (Double.valueOf((killsMap.get("Eliminaciones").getKazama() + killsMap.get("Asistencias").getKazama())) / Double.valueOf(killsMap.get("Muertes").getKazama())) * 100
        ));
        kills.setMafios(String.format("%.2f",
            (Double.valueOf((killsMap.get("Eliminaciones").getMafios() + killsMap.get("Asistencias").getMafios())) / Double.valueOf(killsMap.get("Muertes").getMafios())) * 100
        ));
        kills.setNene(String.format("%.2f",
            (Double.valueOf((killsMap.get("Eliminaciones").getNene() + killsMap.get("Asistencias").getNene())) / Double.valueOf(killsMap.get("Muertes").getNene())) * 100
        ));
        kills.setSwagchan(String.format("%.2f",
            (Double.valueOf((killsMap.get("Eliminaciones").getSwagchan() + killsMap.get("Asistencias").getSwagchan())) / Double.valueOf(killsMap.get("Muertes").getSwagchan())) * 100
        ));
        killsMap.put("K/D/A Ratio", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/team_kills.png");
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
        killsMap.put("Team kills", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/flash_assists.png");
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
        killsMap.put("Asistencias de flash", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/blinded_kills.png");
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
        killsMap.put("Eliminaciones ciego", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/c4_deaths.png");
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
        killsMap.put("Muertes por C4", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/ct_side_kills.png");
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
        killsMap.put("Elim. como CT", kills);

        kills = new TotalDataDTO();
        kills.setIcon("./icons/total_data/t_side_kills.png");
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
        killsMap.put("Elim. como T", kills);
        
        return killsMap;
    }

}
