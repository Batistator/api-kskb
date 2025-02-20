-- Definir variable en DBeaver
@set year_input = 2023;


select 
	'Eliminaciones' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name != 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name != 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name != 'Nene' then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name != 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name != 'The Mafios' then 1 else null end) as "The Mafios"
	from kills k
	union all 
select 
	'Asistencias' as "  ",
	'' as " ",
	COUNT(case assister_name when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case assister_name when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case assister_name when 'Nene' then 1 else null end) as "Nene",
	COUNT(case assister_name when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case assister_name when 'The Mafios' then 1 else null end) as "The Mafios"
	from kills
		union all 
select 
	'Muertes' as "  ",
	'' as " ",
	COUNT(case victim_name when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case victim_name when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case victim_name when 'Nene' then 1 else null end) as "Nene",
	COUNT(case victim_name when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case victim_name when 'The Mafios' then 1 else null end) as "The Mafios"
	from kills
		union all 
select 
	'MVP' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then mvp_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then mvp_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then mvp_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then mvp_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then mvp_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'K/D Ratio' as "  ",
	'' as " ",
	Round(cast(SUM(case name when 'ShinChan' then kill_count else 0 end) as decimal(10,2)) / cast(SUM(case name when 'ShinChan' then death_count else 0 end) as decimal(10,2)),2) as "ShinChan",
	Round(cast(SUM(case name when 'Kazama' then kill_count else 0 end) as decimal(10,2)) / cast(SUM(case name when 'Kazama' then death_count else 0 end) as decimal(10,2)),2) as "Kazama",
	Round(cast(SUM(case name when 'Nene' then kill_count else 0 end) as decimal(10,2)) / cast(SUM(case name when 'Nene' then death_count else 0 end) as decimal(10,2)),2) as "Nene",
	Round(cast(SUM(case name when 'SwagChan' then kill_count else 0 end) as decimal(10,2)) / cast(SUM(case name when 'SwagChan' then death_count else 0 end) as decimal(10,2)),2) as "SwagChan",
	Round(cast(SUM(case name when 'The Mafios' then kill_count else 0 end) as decimal(10,2)) / cast(SUM(case name when 'The Mafios' then death_count else 0 end) as decimal(10,2)),2) as "The Mafios"
	from players
		union all 
select 
	'K/D/A Ratio' as "  ",
	'' as " ",
	Round((cast(SUM(case name when 'ShinChan' then kill_count else 0 end) as decimal(10,2)) + cast(SUM(case name when 'ShinChan' then assist_count else 0 end) as decimal(10,2))) / cast(SUM(case name when 'ShinChan' then death_count else 0 end) as decimal(10,2)),2) as "ShinChan",
	Round((cast(SUM(case name when 'Kazama' then kill_count else 0 end) as decimal(10,2)) + cast(SUM(case name when 'Kazama' then assist_count else 0 end) as decimal(10,2)))/ cast(SUM(case name when 'Kazama' then death_count else 0 end) as decimal(10,2)),2) as "Kazama",
	Round((cast(SUM(case name when 'Nene' then kill_count else 0 end) as decimal(10,2)) + cast(SUM(case name when 'Nene' then assist_count else 0 end) as decimal(10,2)))/ cast(SUM(case name when 'Nene' then death_count else 0 end) as decimal(10,2)),2) as "Nene",
	Round((cast(SUM(case name when 'SwagChan' then kill_count else 0 end) as decimal(10,2)) + cast(SUM(case name when 'SwagChan' then assist_count else 0 end) as decimal(10,2)))/ cast(SUM(case name when 'SwagChan' then death_count else 0 end) as decimal(10,2)),2) as "SwagChan",
	Round((cast(SUM(case name when 'The Mafios' then kill_count else 0 end) as decimal(10,2)) + cast(SUM(case name when 'The Mafios' then assist_count else 0 end) as decimal(10,2)))/ cast(SUM(case name when 'The Mafios' then death_count else 0 end) as decimal(10,2)),2) as "The Mafios"
	from players
		union all 
select 
	'Headshots' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and is_headshot = true then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and is_headshot = true then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and is_headshot = true then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and is_headshot = true then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and is_headshot = true then 1 else null end) as "The Mafios"
	from kills
		union all 
select 
	'% Headshots Ratio' as "  ",
	'' as " ",
	Round(cast(COUNT(case when killer_name = 'ShinChan' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'ShinChan' then 1 else null end) as decimal(10,2)),4) * 100 as "ShinChan",
	Round(cast(COUNT(case when killer_name = 'Kazama' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'Kazama' then 1 else null end) as decimal(10,2)),4) * 100 as "Kazama",
	Round(cast(COUNT(case when killer_name = 'Nene' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'Nene' then 1 else null end) as decimal(10,2)),4) * 100 as "Nene",
	Round(cast(COUNT(case when killer_name = 'SwagChan' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'SwagChan' then 1 else null end) as decimal(10,2)),4) * 100 as "SwagChan",
	Round(cast(COUNT(case when killer_name = 'The Mafios' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'The Mafios' then 1 else null end) as decimal(10,2)),4) * 100 as "The Mafios"
	from kills
		union all 
select 
	'% KAST' as "  ",
	'' as " ",
	cast(SUM(case name when 'ShinChan' then kast else 0 end) / COUNT(case name when 'ShinChan' then kast else null end) as decimal(10,2)) as "ShinChan",
	cast(SUM(case name when 'Kazama' then kast else 0 end) / COUNT(case name when 'Kazama' then kast else null end) as decimal(10,2)) as "Kazama",
	cast(SUM(case name when 'Nene' then kast else 0 end) / COUNT(case name when 'Nene' then kast else null end) as decimal(10,2)) as "Nene",
	cast(SUM(case name when 'SwagChan' then kast else 0 end) / COUNT(case name when 'SwagChan' then kast else null end) as decimal(10,2)) as "SwagChan",
	cast(SUM(case name when 'The Mafios' then kast else 0 end) / COUNT(case name when 'The Mafios' then kast else null end) as decimal(10,2)) as "The Mafios"
	from players	
		union all 
select 
	'Team Kill' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name in ('Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name in ('ShinChan', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name in ('ShinChan','Kazama', 'SwagChan', 'The Mafios') then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name in ('ShinChan','Kazama', 'Nene', 'The Mafios') then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name in ('ShinChan','Kazama', 'Nene', 'SwagChan') then 1 else null end) as "The Mafios"
	from kills
		union all 
select 
	'Entry Kill' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then first_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then first_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then first_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then first_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then first_kill_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'Plant' as "  ",
	'' as " ",
	COUNT(case planter_name when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case planter_name when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case planter_name when 'Nene' then 1 else null end) as "Nene",
	COUNT(case planter_name when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case planter_name when 'The Mafios' then 1 else null end) as "The Mafios"
	from bombs_planted
		union all 
select 
	'Defuse' as "  ",
	'' as " ",
	COUNT(case defuser_name when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case defuser_name when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case defuser_name when 'Nene' then 1 else null end) as "Nene",
	COUNT(case defuser_name when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case defuser_name when 'The Mafios' then 1 else null end) as "The Mafios"
	from bombs_defused
		union all 
select 
	'Daño a vida' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then damage_health else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then damage_health else 0 end) as "Kazama",
	SUM(case name when 'Nene' then damage_health else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then damage_health else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then damage_health else 0 end) as "The Mafios"
	from players
		union all 
select 
	'Daño a armadura' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then damage_armor else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then damage_armor else 0 end) as "Kazama",
	SUM(case name when 'Nene' then damage_armor else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then damage_armor else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then damage_armor else 0 end) as "The Mafios"
	from players
		union all 
select 
	'5K' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then five_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then five_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then five_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then five_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then five_kill_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'4K' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then four_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then four_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then four_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then four_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then four_kill_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'3K' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then three_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then three_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then three_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then three_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then three_kill_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'2K' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then two_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then two_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then two_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then two_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then two_kill_count else 0 end) as "The Mafios"
	from players
		union all 
select 
	'1K' as "  ",
	'' as " ",
	SUM(case name when 'ShinChan' then one_kill_count else 0 end) as "ShinChan",
	SUM(case name when 'Kazama' then one_kill_count else 0 end) as "Kazama",
	SUM(case name when 'Nene' then one_kill_count else 0 end) as "Nene",
	SUM(case name when 'SwagChan' then one_kill_count else 0 end) as "SwagChan",
	SUM(case name when 'The Mafios' then one_kill_count else 0 end) as "The Mafios"
	from players
		union all
select
	'Flash' as "  ",
	'' as " ",
	COUNT(case when thrower_name = 'ShinChan' and grenade_name = 'Flashbang' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' and grenade_name = 'Flashbang' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' and grenade_name = 'Flashbang' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' and grenade_name = 'Flashbang' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' and grenade_name = 'Flashbang' then 1 else null end) as "The Mafios"
	from grenade_projectiles_destroy
		union all
select
	'Smoke' as "  ",
	'' as " ",
	COUNT(case when thrower_name = 'ShinChan' and grenade_name = 'Smoke Grenade' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' and grenade_name = 'Smoke Grenade' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' and grenade_name = 'Smoke Grenade' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' and grenade_name = 'Smoke Grenade' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' and grenade_name = 'Smoke Grenade' then 1 else null end) as "The Mafios"
	from grenade_projectiles_destroy
		union all
select
	'Granada' as "  ",
	'' as " ",
	COUNT(case when thrower_name = 'ShinChan' and grenade_name = 'HE Grenade' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' and grenade_name = 'HE Grenade' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' and grenade_name = 'HE Grenade' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' and grenade_name = 'HE Grenade' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' and grenade_name = 'HE Grenade' then 1 else null end) as "The Mafios"
	from grenade_projectiles_destroy
		union all
select
	'Decoy' as "  ",
	'' as " ",
	COUNT(case when thrower_name = 'ShinChan' and grenade_name = 'Decoy Grenade' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' and grenade_name = 'Decoy Grenade' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' and grenade_name = 'Decoy Grenade' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' and grenade_name = 'Decoy Grenade' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' and grenade_name = 'Decoy Grenade' then 1 else null end) as "The Mafios"
	from grenade_projectiles_destroy
		union all
select
	'Molotov/Incend' as "  ",
	'' as " ",
	COUNT(case when thrower_name ='ShinChan' and grenade_name in ('Molotov','Incendiary Grenade') then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' and grenade_name in ('Molotov','Incendiary Grenade') then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' and grenade_name in ('Molotov','Incendiary Grenade') then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' and grenade_name in ('Molotov','Incendiary Grenade') then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' and grenade_name in ('Molotov','Incendiary Grenade') then 1 else null end) as "The Mafios"
	from grenade_projectiles_destroy gpd 
		union all
select
	'Asistencia de flash' as "  ",
	'' as " ",
	COUNT(case when assister_name ='ShinChan' and killer_name in ('Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_assisted_flash = true then 1 else null end) as "ShinChan",
	COUNT(case when assister_name = 'Kazama' and killer_name in ('ShinChan', 'Nene', 'SwagChan', 'The Mafios') and is_assisted_flash = true then 1 else null end) as "Kazama",
	COUNT(case when assister_name = 'Nene' and killer_name in ('ShinChan','Kazama', 'SwagChan', 'The Mafios') and is_assisted_flash = true then 1 else null end) as "Nene",
	COUNT(case when assister_name = 'SwagChan' and killer_name in ('ShinChan','Kazama', 'Nene', 'The Mafios') and is_assisted_flash = true then 1 else null end) as "SwagChan",
	COUNT(case when assister_name = 'The Mafios' and killer_name in ('ShinChan','Kazama', 'Nene', 'SwagChan') and is_assisted_flash = true then 1 else null end) as "The Mafios"
	from kills 
		union all
select
	'Asesinatos ciego' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and is_killer_blinded = true then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and is_killer_blinded = true then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and is_killer_blinded = true then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and is_killer_blinded = true then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and is_killer_blinded = true then 1 else null end) as "The Mafios"
	from kills 
		union all
select
	'Muertes por C4' as "  ",
	'' as " ",
	COUNT(case when victim_name = 'ShinChan' and weapon_name = 'C4' then 1 else null end) as "ShinChan",
	COUNT(case when victim_name = 'Kazama' and weapon_name = 'C4' then 1 else null end) as "Kazama",
	COUNT(case when victim_name = 'Nene' and weapon_name = 'C4' then 1 else null end) as "Nene",
	COUNT(case when victim_name = 'SwagChan' and weapon_name = 'C4' then 1 else null end) as "SwagChan",
	COUNT(case when victim_name = 'The Mafios' and weapon_name = 'C4' then 1 else null end) as "The Mafios"
	from kills 
		union all
select
	'Dinero total gastado' as "  ",
	'' as " ",
	SUM(case player_name when 'ShinChan' then money_spent else 0 end) as "ShinChan",
	SUM(case player_name when 'Kazama' then money_spent else 0 end) as "Kazama",
	SUM(case player_name when 'Nene' then money_spent else 0 end) as "Nene",
	SUM(case player_name when 'SwagChan' then money_spent else 0 end) as "SwagChan",
	SUM(case player_name when 'The Mafios' then money_spent else 0 end) as "The Mafios"
	from player_economies
		union all 
select 
	'Tiros' as "  ",
	'' as " ",
	COUNT(case player_name when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case player_name when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case player_name when 'Nene' then 1 else null end) as "Nene",
	COUNT(case player_name when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case player_name when 'The Mafios' then 1 else null end) as "The Mafios"
	from shots
		union all
select
	'Pollos asesinados' as "  ",
	'' as " ",
	COUNT(case sa."name" when 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case sa."name" when 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case sa."name" when 'Nene' then 1 else null end) as "Nene",
	COUNT(case sa."name" when 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case sa."name" when 'The Mafios' then 1 else null end) as "ShinChan"
	from chicken_deaths cd join steam_accounts sa on cd.killer_steam_id = sa.steam_id
		union all
select 
	'Enemigos flasheados' as "  ",
	'' as " ",
	COUNT(case when flasher_name = 'ShinChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "ShinChan",
	COUNT(case when flasher_name = 'Kazama' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Kazama",
	COUNT(case when flasher_name = 'Nene' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Nene",
	COUNT(case when flasher_name = 'SwagChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "SwagChan",
	COUNT(case when flasher_name = 'The Mafios' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "The Mafios"
	from player_blinds pb 
		union all
select 
	'Aliados flasheados' as "  ",
	'' as " ",
	SUM(case when flasher_name = 'ShinChan' and flashed_name in ('Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "ShinChan",
	SUM(case when flasher_name = 'Kazama' and flashed_name in ('ShinChan', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Kazama",
	SUM(case when flasher_name = 'Nene' and flashed_name in ('ShinChan', 'Kazama', 'SwagChan', 'The Mafios') then 1 else null end) as "Nene",
	SUM(case when flasher_name = 'SwagChan' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'The Mafios') then 1 else null end) as "SwagChan",
	SUM(case when flasher_name = 'The Mafios' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan') then 1 else null end) as "The Mafios"
	from player_blinds pb 
		union all
select 
	'Máximo flasheo' as "  ",
	'' as " ",
	MAX(case when flasher_name = 'ShinChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "ShinChan",
	MAX(case when flasher_name = 'Kazama' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "Kazama",
	MAX(case when flasher_name = 'Nene' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "Nene",
	MAX(case when flasher_name = 'SwagChan' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'The Mafios') then duration else 0 end) as "SwagChan",
	MAX(case when flasher_name = 'The Mafios' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan') then duration else 0 end) as "The Mafios"
	from player_blinds pb 
		union all
select 
	'Daño a aliados' as "  ",
	'' as " ",
	SUM(case when attacker_steam_id = '76561197969326379' and victim_steam_id in ('76561198020496882','76561198009962148','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "ShinChan",
	SUM(case when attacker_steam_id = '76561198020496882' and victim_steam_id in ('76561197969326379','76561198009962148','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "Kazama",
	SUM(case when attacker_steam_id = '76561198009962148' and victim_steam_id in ('76561197969326379','76561198020496882','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "Nene",
	SUM(case when attacker_steam_id = '76561198158839442' and victim_steam_id in ('76561197969326379','76561198020496882','76561198009962148','76561198010914876') then health_damage + armor_damage else null end) as "SwagChan",
	SUM(case when attacker_steam_id = '76561198010914876' and victim_steam_id in ('76561197969326379','76561198020496882','76561198009962148','76561198158839442') then health_damage + armor_damage else null end) as "The Mafios"
	from damages 
		union all
select 
	'Rebotador de granadas' as "  ",
	'' as " ",
	COUNT(case when thrower_name  = 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' then 1 else null end) as "The Mafios"
	from grenade_bounces gb  
		union all
select 
	'Eliminaciones como poli' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name != 'ShinChan' and killer_side = 2 then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name != 'Kazama' and killer_side = 2 then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name != 'Nene' and killer_side = 2 then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name != 'SwagChan' and killer_side = 2 then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name != 'The Mafios' and killer_side = 2 then 1 else null end) as "The Mafios"
	from kills 
		union all
select 
	'Eliminaciones como terro' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name != 'ShinChan' and killer_side = 3 then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name != 'Kazama' and killer_side = 3 then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name != 'Nene' and killer_side = 3 then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name != 'SwagChan' and killer_side = 3 then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name != 'The Mafios' and killer_side = 3 then 1 else null end) as "The Mafios"
	from kills 
	
	
	(select total as "ShinChan" from (select sa.name, count(*) as total, rank() over(order by count(*) desc) as ranking from chicken_deaths cd join steam_accounts sa on cd.killer_steam_id = sa.steam_id where sa.name = 'ShinChan'))





	
select 'Victorias' as campo, cast(COUNT(*) as varchar) as dato from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') and p.team_name = m.winner_name
union all
select 'Derrotas' as campo, cast(COUNT(*) as varchar) as dato from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') and p.team_name != m.winner_name
union all
select 'Empate' as campo, cast(COUNT(*) as varchar) as dato from matches m where m.winner_name is null
union all
select 'Win Rate' as campo, cast(Round(cast(count(case when p.name in ('Nene') and p.team_name = m.winner_name then 1 else null end) as decimal(10,2)) * 100 / cast(count(distinct m.checksum)as decimal(10,2)),2) as varchar) as dato from matches m left join players p on m.checksum = p.match_checksum
union all
select 'No lose Rate' as campo, cast(Round(cast(count(case when p.name in ('Nene') and (p.team_name = m.winner_name or m.winner_name is null) then 1 else null end) as decimal(10,2)) * 100 / cast(count(distinct m.checksum)as decimal(10,2)),2) as varchar) as dato from matches m left join players p on m.checksum = p.match_checksum
union all
select 'Total matches' as campo, cast(count(distinct m.checksum) as varchar) as dato from matches m left join players p on m.checksum = p.match_checksum
union all
select 'Mapa favorable' as campo, (map_name) as dato from (select map_name, count_win, count_lose, (cast(count_win as decimal(10,2))/cast(count_lose as decimal(10,2))) as rate from (select SUM(CASE WHEN p.team_name = m.winner_name THEN 1 ELSE 0 END) AS "count_win", map_name, SUM(CASE WHEN p.team_name <> m.winner_name THEN 1 ELSE 0 END) AS "count_lose" from matches m LEFT join players p ON m.checksum = p.match_checksum where p.name IN ('Nene') GROUP BY map_name) as T1 order by rate desc limit 1) as t2
union all
select 'Mapa desfavorable' as campo, (map_name) as dato from (select map_name, count_win, count_lose, (cast(count_win as decimal(10,2))/cast(count_lose as decimal(10,2))) as rate from (select SUM(CASE WHEN p.team_name = m.winner_name THEN 1 ELSE 0 END) AS "count_win", map_name, SUM(CASE WHEN p.team_name <> m.winner_name THEN 1 ELSE 0 END) AS "count_lose" from matches m LEFT join players p ON m.checksum = p.match_checksum where p.name IN ('Nene') GROUP BY map_name) as T1 order by rate asc limit 1) as t2
union all
select 'Mapa favorito' as campo, (map_name) as dato from (select COUNT(map_name), map_name from matches group by map_name order by 1 desc limit 1) as t1
union all
select 'Días de victoria' as campo, cast(SUM(case when total = victorias then 1 else 0 end)as varchar) as "dato" from (
	select fecha, COUNT(resultado) as Total, COUNT(case when resultado = 'win' then 1 else null end) as victorias, COUNT(case when resultado = 'lose' then 1 else null end) as derrotas from (
		select  cast("date"- INTERVAL '8 hours' as date) as fecha, case when p.team_name = m.winner_name then 'win' else 'lose' end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc
	) t1 group by fecha order by FECHA desc
) t2
union all
select 'Días de derrota' as campo, cast(SUM(case when total = derrotas then 1 else 0 end) as varchar) as "dias de derrotas" from (
	select fecha, COUNT(resultado) as Total, COUNT(case when resultado = 'win' then 1 else null end) as victorias, COUNT(case when resultado = 'lose' then 1 else null end) as derrotas from (
		select  cast("date"- INTERVAL '8 hours' as date) as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc
	) t1 group by fecha order by FECHA desc
) t2
union all
select 'Días de empate' as campo, cast(SUM(case when total = empates then 1 else 0 end) as varchar) as "dato" from (
	select fecha, COUNT(resultado) as Total, COUNT(case when resultado = 'win' then 1 else null end) as victorias, COUNT(case when resultado = 'lose' then 1 else null end) as derrotas, COUNT(case when resultado = 'tie' then 1 else null end) as empates from (
		select  cast("date"- INTERVAL '8 hours' as date) as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc
	) t1 group by fecha order by FECHA desc
) t2
union all
SELECT 'Máxima racha de victorias' as campo, cast(MAX(count) as varchar) AS dato
FROM   (
  SELECT COUNT(*) AS count
  FROM   (SELECT *, ROW_NUMBER() OVER (ORDER BY fecha DESC) - ROW_NUMBER() OVER (PARTITION BY resultado ORDER BY fecha DESC) AS streak FROM  (select "date" as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc) t1) t2
  WHERE  resultado = 'win'
  GROUP  BY streak
) s
union all
SELECT 'Máxima racha de derrotas' as campo, cast(MAX(count) as varchar) AS dato
FROM   (
  SELECT COUNT(*) AS count
  FROM   (SELECT *, ROW_NUMBER() OVER (ORDER BY fecha DESC) - ROW_NUMBER() OVER (PARTITION BY resultado ORDER BY fecha DESC) AS streak FROM  (select "date" as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc) t1) t2
  WHERE  resultado = 'lose'
  GROUP  BY streak
) s
union all
SELECT 'Racha de victorias actual' as campo, cast(COALESCE(racha, 0) as varchar) AS dato
FROM (SELECT COUNT(*) AS racha FROM (SELECT fecha, resultado, ROW_NUMBER() OVER (ORDER BY fecha DESC) AS rn FROM (select "date" as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc) t1) t2 WHERE resultado = 'win' AND rn <= COALESCE((SELECT rn FROM (SELECT fecha, resultado, ROW_NUMBER() OVER (ORDER BY fecha DESC) AS rn FROM (select "date" as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc) t1) t2 WHERE resultado != 'win' ORDER BY fecha DESC LIMIT 1), (SELECT MAX(rn) FROM (SELECT fecha, resultado, ROW_NUMBER() OVER (ORDER BY fecha DESC) AS rn FROM (select "date" as fecha, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc) t1) t2))) t3
union all
SELECT
  'Partidas ShinChan no primero' as campo, cast(COUNT(*) as varchar)
FROM
  (select match_checksum, name, score, RANK() OVER (PARTITION BY match_checksum ORDER BY score DESC) AS ranking FROM (select match_checksum, name, score from players where "name" in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios')) t1) t2
WHERE
  ranking = 1 and name != 'ShinChan'
union all
select 'Porcentaje ShinChan no primero' as campo, 
cast(Round(cast( (select COUNT(*) 
from (select match_checksum, name, score, RANK() OVER (PARTITION BY match_checksum ORDER BY score DESC) AS ranking FROM (select match_checksum, name, score from players where "name" in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios')) t1) t2 
where ranking = 1 and name != 'ShinChan') as decimal(10,2)) * 100 / cast((select count(distinct m.checksum) from matches m left join players p on m.checksum = p.match_checksum) as decimal(10,2)),2) as varchar) as dato






select mapa, COUNT(case when resultado = 'win' then 1 else null end) as victorias, COUNT(case when resultado = 'lose' then 1 else null end) as derrotas, COUNT(case when resultado = 'tie' then 1 else null end) as empates, COUNT(resultado) as Total from (
	select  cast("date"- INTERVAL '8 hours' as date) as fecha, map_name as mapa, case when p.team_name = m.winner_name then 'win' else case when m.winner_name is null then 'tie' else 'lose' end end as resultado from matches m left join players p on m.checksum = p.match_checksum where p.name in ('Nene') order by 1 desc
) t1 group by mapa




select weapon_name as "Arma", count(case killer_name when 'ShinChan' then 1 else null end) as "ShinChan", count(case killer_name when 'Kazama' then 1 else null end) as "Kazama", count(case killer_name when 'Nene' then 1 else null end) as "Nene", count(case killer_name when 'SwagChan' then 1 else null end) as "SwagChan", count(case killer_name when 'The Mafios' then 1 else null end) as "The Mafios" from kills where weapon_name NOT IN ('World', 'C4') group by weapon_name



(select 'Más eliminaciones' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name != 'World' group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más muertes' as "Título", victim_name as ganador, total as cantidad from (select victim_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by victim_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más asistencias' as "Título", assister_name as ganador, total as cantidad from (select assister_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where assister_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by assister_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más tiros a la cabeza' as "Título", killer_name as ganador, total as cantidad from (select killer_name, total, rank() over(order by total desc) as ranking from (select 'ShinChan' as killer_name, Round(cast(COUNT(case when killer_name = 'ShinChan' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'ShinChan' then 1 else null end) as decimal(10,2)),4) as "total" from kills union all select 'Kazama' as killer_name, Round(cast(COUNT(case when killer_name = 'Kazama' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'Kazama' then 1 else null end) as decimal(10,2)),4) as "total" from kills union all select 'Nene' as killer_name, Round(cast(COUNT(case when killer_name = 'Nene' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'Nene' then 1 else null end) as decimal(10,2)),4) as "total" from kills union all select 'SwagChan' as killer_name, Round(cast(COUNT(case when killer_name = 'SwagChan' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'SwagChan' then 1 else null end) as decimal(10,2)),4) as "SwagChan" from kills union all select 'The Mafios' as killer_name, Round(cast(COUNT(case when killer_name = 'The Mafios' and is_headshot = true then 1 else null end) as decimal(10,2)) / cast(COUNT(case when killer_name = 'The Mafios' then 1 else null end) as decimal(10,2)),4) as "The Mafios" from kills ) t1) t2 where t2.ranking = 1 limit 1)
union all 
(select 'Más team kills' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name != 'World' and victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más entry kills' as "Título", name as ganador, total as cantidad from (select name, sum(first_kill_count) as total, rank() over(order by sum(first_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces planta' as "Título", planter_name as ganador, total as cantidad from (select planter_name, count(*) as total, rank() over(order by count(*) desc) as ranking from bombs_planted where planter_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by planter_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces defusa' as "Título", defuser_name as ganador, total as cantidad from (select defuser_name, count(*) as total, rank() over(order by count(*) desc) as ranking from bombs_defused where defuser_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by defuser_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces 5K' as "Título", name as ganador, total as cantidad from (select name, sum(five_kill_count) as total, rank() over(order by sum(five_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces 4K' as "Título", name as ganador, total as cantidad from (select name, sum(four_kill_count) as total, rank() over(order by sum(four_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces 3K' as "Título", name as ganador, total as cantidad from (select name, sum(three_kill_count) as total, rank() over(order by sum(three_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces 2K' as "Título", name as ganador, total as cantidad from (select name, sum(two_kill_count) as total, rank() over(order by sum(two_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces 1K' as "Título", name as ganador, total as cantidad from (select name, sum(one_kill_count) as total, rank() over(order by sum(one_kill_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Tira más flashes' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_projectiles_destroy where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and grenade_name = 'Flashbang' group by thrower_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Tira más humos' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_projectiles_destroy where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and grenade_name = 'Smoke Grenade' group by thrower_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Tira más granadas' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_projectiles_destroy where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and grenade_name = 'HE Grenade' group by thrower_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Tira más decoys' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_projectiles_destroy where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and grenade_name = 'Decoy Grenade' group by thrower_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Tira más fuegos' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_projectiles_destroy where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and grenade_name in ('Molotov','Incendiary Grenade') group by thrower_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills saltando' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_killer_airborne = true group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más clutches ganados' as "Título", clutcher_name as ganador, total as cantidad from (select clutcher_name, count(*) as total, rank() over(order by count(*) desc) as ranking from clutches where clutcher_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and won = true and opponent_count >= 2 group by clutcher_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más asistencias de flash' as "Título", assister_name as ganador, total as cantidad from (select assister_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where assister_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_assisted_flash = true group by assister_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills estando ciego' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_killer_blinded = true group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más veces muerto por C4' as "Título", victim_name as ganador, total as cantidad from (select victim_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name = 'C4' group by victim_name) t1 where t1.ranking = 1 limit 1)
union all 
(select t1.titulo, coalesce(t2.ganador, '-') as killer_name, coalesce(t2.cantidad,0) as cantidad from (select 'Más kills con cuchillo' as "titulo") as t1 left join (select killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name = 'Knife' group by killer_name) t1 where t1.ranking = 1) as t2 on 1 = 1 limit 1)
union all 
(select t1.titulo, coalesce(t2.ganador, '-') as killer_name, coalesce(t2.cantidad,0) as cantidad from (select 'Más kills con Taser' as "titulo") as t1 left join (select killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name like '%Zeus%' group by killer_name) t1 where t1.ranking = 1) as t2 on 1 = 1 limit 1)
union all 
(select 'Más kills con granada' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name = 'HE Grenade' group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills con fuego' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and (weapon_name = 'Incendiary Grenade' or weapon_name = 'Molotov') group by killer_name) t1 where t1.ranking = 1 limit 1)
union all
(select 'Por encima de ShinChan' as "Título", name as ganador, total as cantidad from (select name, count(*) as total, rank() over(order by count(*) desc) as "rank" from (SELECT match_checksum, name, score, RANK() OVER (PARTITION BY match_checksum ORDER BY score DESC) AS ranking FROM (select match_checksum, name, score from players where "name" in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios')) t1) t2 where ranking = 1 and name != 'ShinChan' group by t2.name) t3 where t3."rank" = 1 limit 1)
union all 
(select 'Más dinero gastado' as "Título", player_name as ganador, total as cantidad from (select player_name, sum(money_spent) as total, rank() over(order by sum(money_spent) desc) as ranking from player_economies where player_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by player_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Menos dinero gastado' as "Título", player_name as ganador, total as cantidad from (select player_name, sum(money_spent) as total, rank() over(order by sum(money_spent) asc) as ranking from player_economies where player_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by player_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Pega más tiros' as "Título", player_name as ganador, total as cantidad from (select player_name, count(*) as total, rank() over(order by count(*) desc) as ranking from shots where player_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by player_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Pega menos tiros' as "Título", player_name as ganador, total as cantidad from (select player_name, count(*) as total, rank() over(order by count(*) asc) as ranking from shots where player_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by player_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Muerte desde más lejos' as "Título", killer_name as ganador, total as cantidad from (select killer_name, max(distance) as total, rank() over(order by max(distance) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más daño diverso' as "Título", name as ganador, total as cantidad from (select name, sum(utility_damage) as total, rank() over(order by sum(utility_damage) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más trade kills' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_trade_kill = true group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más trade deaths' as "Título", victim_name as ganador, total as cantidad from (select victim_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_trade_death = true group by victim_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más inspecciones de arma' as "Título", case when total = 0 then '-' else name end as ganador, total as cantidad from (select name, sum(inspect_weapon_count) as total, rank() over(order by sum(inspect_weapon_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills atravesando' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and penetrated_objects > 0 group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills a través de humo' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_through_smoke = true group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más kills con no scope' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and is_no_scope = true group by killer_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más pollos asesinados' as "Título", name as ganador, total as cantidad from (select sa.name, count(*) as total, rank() over(order by count(*) desc) as ranking from chicken_deaths cd join steam_accounts sa on cd.killer_steam_id = sa.steam_id where sa.name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios')  group by sa.name) t1 where t1.ranking = 1 limit 1)
union all
(select 'Mayor precisión' as "Título", nombre as ganador, "precision" as cantidad from (select nombre, "precision", rank() over(order by "precision" desc) as ranking from (select 'ShinChan' as Nombre, (Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561197969326379' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'ShinChan' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)) as "precision" union all select 'Kazama' as Nombre, (Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198020496882' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'Kazama' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)) as "precision" union all select 'Nene' as Nombre, (Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198009962148' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'Nene' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)) as "precision" union all select 'SwagChan' as Nombre, (Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198158839442' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'SwagChan' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)) as "precision" union all select 'The Mafios' as Nombre, (Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198010914876' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'The Mafios' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)) as "precision") t1) t2 where t2.ranking = 1 limit 1)
union all
(select 'Más desperdicios' as "Título", nombre as ganador, "Desperdicios" as cantidad from (select nombre, "Desperdicios", rank() over(order by "Desperdicios" desc) as ranking from (select 'ShinChan' as Nombre, ((select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'ShinChan' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'ShinChan')) as "Desperdicios" union all select 'Kazama' as Nombre, ((select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'Kazama' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'Kazama')) as "Desperdicios" union all select 'Nene' as Nombre, ((select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'Nene' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'Nene')) as "Desperdicios" union all select 'SwagChan' as Nombre, ((select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'SwagChan' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'SwagChan')) as "Desperdicios" union all select 'The Mafios' as Nombre, ((select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'The Mafios' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'The Mafios')) as "Desperdicios") t1) t2 where t2.ranking = 1 limit 1)
union all
(select 'Más fakes al defusar' as "Título", nombre as ganador, "fakes" as cantidad from (select nombre, "fakes", rank() over(order by "fakes" desc) as ranking from (select 'ShinChan' as nombre, (select count(*) from bombs_defuse_start where defuser_name = 'ShinChan')-(select count(*) from bombs_defused where defuser_name = 'ShinChan')  as "fakes" union all select 'Kazama' as nombre, (select count(*) from bombs_defuse_start where defuser_name = 'Kazama')-(select count(*) from bombs_defused where defuser_name = 'Kazama') as "fakes" union all select 'Nene' as nombre, (select count(*) from bombs_defuse_start where defuser_name = 'Nene')-(select count(*) from bombs_defused where defuser_name = 'Nene') as "fakes" union all select 'SwagChan' as nombre, (select count(*) from bombs_defuse_start where defuser_name = 'SwagChan')-(select count(*) from bombs_defused where defuser_name = 'SwagChan') as "fakes" union all select 'The Mafios' as nombre, (select count(*) from bombs_defuse_start where defuser_name = 'The Mafios')-(select count(*) from bombs_defused where defuser_name = 'The Mafios') as "fakes") t1) t2 where t2.ranking = 1 limit 1)
union all
(select 'Más fakes al plantar' as "Título", nombre as ganador, "fakes" as cantidad from (select nombre, "fakes", rank() over(order by "fakes" desc) as ranking from (select 'ShinChan' as nombre, (select count(*) from bombs_plant_start where planter_name = 'ShinChan')-(select count(*) from bombs_planted where planter_name = 'ShinChan')  as "fakes" union all select 'Kazama' as nombre, (select count(*) from bombs_plant_start where planter_name = 'Kazama')-(select count(*) from bombs_planted where planter_name = 'Kazama') as "fakes" union all select 'Nene' as nombre, (select count(*) from bombs_plant_start where planter_name = 'Nene')-(select count(*) from bombs_planted where planter_name = 'Nene') as "fakes" union all select 'SwagChan' as nombre, (select count(*) from bombs_plant_start where planter_name = 'SwagChan')-(select count(*) from bombs_planted where planter_name = 'SwagChan') as "fakes" union all select 'The Mafios' as nombre, (select count(*) from bombs_plant_start where planter_name = 'The Mafios')-(select count(*) from bombs_planted where planter_name = 'The Mafios') as "fakes") t1) t2 where t2.ranking = 1 limit 1)
union all
(select 'Más kills con escopeta' as "Título", killer_name as ganador, total as cantidad from (select killer_name, count(*) as total, rank() over(order by count(*) desc) as ranking from kills where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and weapon_name IN ('MAG-7','XM1014','Sawed-Off','Nova') group by killer_name) t1 where t1.ranking = 1 limit 1)
union all
(select 'Más devoluciones de armas' as "Título", player_name as ganador, total as cantidad from (select player_name, count(*) as total, rank() over(order by count(*) desc) as ranking from player_buys where player_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and has_refunded = TRUE group by player_name) t1 where t1.ranking = 1 limit 1)
union all
(select 'Flasheo más duradero' as "Título", flasher_name as ganador, duration as cantidad from (select flasher_name, duration from player_blinds pb where flasher_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') order by duration desc limit 1) t1)
union all
(select 'Más MVP' as "Título", name as ganador, total as cantidad from (select name, sum(mvp_count) as total, rank() over(order by sum(mvp_count) desc) as ranking from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by name) t1 where t1.ranking = 1 limit 1)
union all
(select 'Asesinato más rápido' as "Título", killer_name as ganador, tick as cantidad from (select killer_name, cast(((k.tick-r.freeze_time_end_tick))as decimal(10,2)) as tick from kills k join rounds r on k.match_checksum = r.match_checksum where killer_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and victim_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and r."number" = k.round_number order by tick asc limit 1) t1)
union all
(select 'Muerte más rápida' as "Título", victim_name as ganador, tick as cantidad from (select victim_name, cast(((k.tick-r.freeze_time_end_tick))as decimal(10,2)) as tick from kills k join rounds r on k.match_checksum = r.match_checksum where victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and killer_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and r."number" = k.round_number order by tick asc limit 1) t1)
union all 
(select 'Menos muertes' as "Título", victim_name as ganador, total as cantidad from (select victim_name, count(*) as total, rank() over(order by count(*) asc) as ranking from kills where victim_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by victim_name) t1 where t1.ranking = 1 limit 1)
union all 
(select 'Más rebotes de granada' as "Título", thrower_name as ganador, total as cantidad from (select thrower_name, count(*) as total, rank() over(order by count(*) desc) as ranking from grenade_bounces where thrower_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') group by thrower_name) t1 where t1.ranking = 1 limit 1)




select 'ShinChan' as Nombre, (
	Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561197969326379' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov', 'Knife'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'ShinChan' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)
) as "Precision"
union all
select 'Kazama' as Nombre, (
	Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198020496882' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov', 'Knife'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'Kazama' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)
) as "Precision"
union all
select 'Nene' as Nombre, (
	Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198009962148' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov', 'Knife'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'Nene' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)
) as "Precision"
union all
select 'SwagChan' as Nombre, (
	Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198158839442' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov', 'Knife'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'SwagChan' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)
) as "Precision"
union all
select 'The Mafios' as Nombre, (
	Round((select cast(COUNT(*) as decimal(15,2)) from damages where attacker_steam_id = '76561198010914876' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov', 'Knife'))/(select cast(COUNT(*) as decimal(15,2)) from shots where player_name = 'The Mafios' and weapon_name not in ('HE Grenade', 'Incendiary Grenade', 'Molotov')) * 100,2)
) as "Precision"




select 'ShinChan' as Nombre, (
	(select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'ShinChan' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'ShinChan')
) as "Desperdicios"
union all
select 'Kazama' as Nombre, (
	(select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'Kazama' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'Kazama')
) as "Desperdicios"
union all
select 'Nene' as Nombre, (
	(select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'Nene' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'Nene')
) as "Desperdicios"
union all
select 'SwagChan' as Nombre, (
	(select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'SwagChan' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'SwagChan')
) as "Desperdicios"
union all
select 'The Mafios' as Nombre, (
	(select COUNT(*) from player_buys where weapon_type = 'grenade' and player_name = 'The Mafios' and has_refunded = false)-(select COUNT(*) from grenade_projectiles_destroy where thrower_name = 'The Mafios')
) as "Desperdicios"





select TO_CHAR(m.date at time zone 'GMT-1', 'YYYY-MM-DD HH24:MI') as fecha, m.map_name as mapa, 
CONCAT(ROUND(m.duration/60),' min') as duración,
MAX(CASE WHEN t."name" = 'Team A' THEN t.score END) AS "equipo A",
MAX(CASE WHEN t."name" = 'Team B' THEN t.score END) AS "equipo B",
case when p.team_name = m.winner_name then 'VICTORIA' else case when m.winner_side = 0 then 'EMPATE' else 'DERROTA' end end as resultado,
case when m.overtime_count > 0 then 'Prórroga' else '' end as " "
from matches m join players p on m.checksum = p.match_checksum  join teams t on t.match_checksum = m.checksum 
where p."name" = 'Nene' group by m.date, m.map_name, p.team_name, m.winner_name, m.winner_side, m.overtime_count,m.duration  order by m.date desc
 







select 
	'Enemigos flasheados' as "  ",
	'' as " ",
	COUNT(case when flasher_name = 'ShinChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "ShinChan",
	COUNT(case when flasher_name = 'Kazama' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Kazama",
	COUNT(case when flasher_name = 'Nene' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Nene",
	COUNT(case when flasher_name = 'SwagChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "SwagChan",
	COUNT(case when flasher_name = 'The Mafios' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "The Mafios"
	from player_blinds pb 
	
select 
	'Aliados flasheados' as "  ",
	'' as " ",
	SUM(case when flasher_name = 'ShinChan' and flashed_name in ('Kazama', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "ShinChan",
	SUM(case when flasher_name = 'Kazama' and flashed_name in ('ShinChan', 'Nene', 'SwagChan', 'The Mafios') then 1 else null end) as "Kazama",
	SUM(case when flasher_name = 'Nene' and flashed_name in ('ShinChan', 'Kazama', 'SwagChan', 'The Mafios') then 1 else null end) as "Nene",
	SUM(case when flasher_name = 'SwagChan' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'The Mafios') then 1 else null end) as "SwagChan",
	SUM(case when flasher_name = 'The Mafios' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan') then 1 else null end) as "The Mafios"
	from player_blinds pb 
	
select 
	'Máximo flasheo' as "  ",
	'' as " ",
	MAX(case when flasher_name = 'ShinChan' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "ShinChan",
	MAX(case when flasher_name = 'Kazama' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "Kazama",
	MAX(case when flasher_name = 'Nene' and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') then duration else 0 end) as "Nene",
	MAX(case when flasher_name = 'SwagChan' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'The Mafios') then duration else 0 end) as "SwagChan",
	MAX(case when flasher_name = 'The Mafios' and flashed_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan') then duration else 0 end) as "The Mafios"
	from player_blinds pb 
	
select 
	'Daño a aliados' as "  ",
	'' as " ",
	SUM(case when attacker_steam_id = '76561197969326379' and victim_steam_id in ('76561198020496882','76561198009962148','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "ShinChan",
	SUM(case when attacker_steam_id = '76561198020496882' and victim_steam_id in ('76561197969326379','76561198009962148','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "Kazama",
	SUM(case when attacker_steam_id = '76561198009962148' and victim_steam_id in ('76561197969326379','76561198020496882','76561198158839442','76561198010914876') then health_damage + armor_damage else null end) as "Nene",
	SUM(case when attacker_steam_id = '76561198158839442' and victim_steam_id in ('76561197969326379','76561198020496882','76561198009962148','76561198010914876') then health_damage + armor_damage else null end) as "SwagChan",
	SUM(case when attacker_steam_id = '76561198010914876' and victim_steam_id in ('76561197969326379','76561198020496882','76561198009962148','76561198158839442') then health_damage + armor_damage else null end) as "The Mafios"
	from damages 
	
select 
	'Rebotador de granadas' as "  ",
	'' as " ",
	COUNT(case when thrower_name  = 'ShinChan' then 1 else null end) as "ShinChan",
	COUNT(case when thrower_name = 'Kazama' then 1 else null end) as "Kazama",
	COUNT(case when thrower_name = 'Nene' then 1 else null end) as "Nene",
	COUNT(case when thrower_name = 'SwagChan' then 1 else null end) as "SwagChan",
	COUNT(case when thrower_name = 'The Mafios' then 1 else null end) as "The Mafios"
	from grenade_bounces gb  
		
select 
	'Eliminaciones como poli' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name != 'ShinChan' and killer_side = 2 then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name != 'Kazama' and killer_side = 2 then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name != 'Nene' and killer_side = 2 then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name != 'SwagChan' and killer_side = 2 then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name != 'The Mafios' and killer_side = 2 then 1 else null end) as "The Mafios"
	from kills 
	
select 
	'Eliminaciones como terro' as "  ",
	'' as " ",
	COUNT(case when killer_name = 'ShinChan' and victim_name != 'ShinChan' and killer_side = 3 then 1 else null end) as "ShinChan",
	COUNT(case when killer_name = 'Kazama' and victim_name != 'Kazama' and killer_side = 3 then 1 else null end) as "Kazama",
	COUNT(case when killer_name = 'Nene' and victim_name != 'Nene' and killer_side = 3 then 1 else null end) as "Nene",
	COUNT(case when killer_name = 'SwagChan' and victim_name != 'SwagChan' and killer_side = 3 then 1 else null end) as "SwagChan",
	COUNT(case when killer_name = 'The Mafios' and victim_name != 'The Mafios' and killer_side = 3 then 1 else null end) as "The Mafios"
	from kills 
	
	
	
select flasher_name, duration, flashed_name from player_blinds pb where flasher_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') order by duration desc limit 1

select flasher_name, duration, flashed_name from player_blinds pb where flasher_name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') and flashed_name not in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios') order by duration desc limit 1



	
	

	 


select 
	'Daño a vida' as "  ",
	'' as " ",
	SUM(case when attacker_steam_id =  cast(76561197969326379 then health_damage + armor_damage else 0 end) as "ShinChan",
	SUM(case when attacker_steam_id = 76561198020496882 then health_damage + armor_damage else 0 end) as "Kazama",
	SUM(case when attacker_steam_id = 76561198009962148 then health_damage + armor_damage else 0 end) as "Nene",
	SUM(case when attacker_steam_id = 76561198158839442 then health_damage + armor_damage else 0 end) as "SwagChan",
	SUM(case when attacker_steam_id = 76561198010914876 then health_damage + armor_damage else 0 end) as "The Mafios"
	from damages

	
select distinct name, steam_id  from players where name in ('ShinChan', 'Kazama', 'Nene', 'SwagChan', 'The Mafios')



Nene	76561198009962148
SwagChan	76561198158839442
The Mafios	76561198010914876
ShinChan	76561197969326379
Kazama	76561198020496882




select TO_CHAR(m.date at time zone 'GMT-1', 'YYYY-MM-DD HH24:MI') as fecha, m.map_name as mapa, 
CONCAT(ROUND(m.duration/60),' min') as duración,
MAX(CASE WHEN t."name" = 'Team A' THEN t.score END) AS "equipo A",
MAX(CASE WHEN t."name" = 'Team B' THEN t.score END) AS "equipo B",
case when p.team_name = m.winner_name then 'VICTORIA' else case when m.winner_side = 0 then 'EMPATE' else 'DERROTA' end end as resultado,
case when m.overtime_count > 0 then 'Prórroga' else '' end as " "
from matches m join players p on m.checksum = p.match_checksum  join teams t on t.match_checksum = m.checksum 
where p."name" = 'Nene' group by m.date, m.map_name, p.team_name, m.winner_name, m.winner_side, m.overtime_count,m.duration  order by m.date desc