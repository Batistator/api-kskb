package com.batistes.kskb.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${steam.id.nene}")
	public String NENE_STEAM_ID;
	@Value("${steam.id.shinchan}")
	public String SHINCHAN_STEAM_ID;
	@Value("${steam.id.mafios}")
	public String MAFIOS_STEAM_ID;
	@Value("${steam.id.swagchan}")
	public String SWAGCHAN_STEAM_ID;
    @Value("${steam.id.kazama}")
	public String KAZAMA_STEAM_ID;
}
