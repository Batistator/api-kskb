package com.batistes.kskb.api.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class Utils {
    
    @Value("${steam.id.swagchan}")
    private String swagchanId;
    
    @Value("${steam.id.mafios}")
    private String mafiosId;
    
    @Value("${steam.id.kazama}")
    private String kazamaId;
    
    @Value("${steam.id.shinchan}")
    private String shinchanId;
    
    @Value("${steam.id.nene}")
    private String neneId;
        
        
    public static java.sql.Date convertUtilDateToSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
    
    public static String convertToJSON(Object object){
        String jsonResult = "";
        try {
            jsonResult = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error de conversión JSON";
        }
        return jsonResult;
    }
    
    public String convertSteamIdToName(String id) {

        if (id.equals(swagchanId)) {
            return "SwagChan";
        } else if (id.equals(mafiosId)) {
            return "The Mafios";
        } else if (id.equals(kazamaId)) {
            return "Kazama";
        } else if (id.equals(shinchanId)) {
            return "ShinChan";
        } else if (id.equals(neneId)) {
            return "Nene";
        } else {
            return "Unknown ID";
        }
    }

    public String convertNameToSteamId(String name) {
        switch (name) {
            case "SwagChan":
            return swagchanId;
            case "The Mafios":
            return mafiosId;
            case "Kazama":
            return kazamaId;
            case "ShinChan":
            return shinchanId;
            case "Nene":
            return neneId;
            default:
            return "Unknown Name";
        }
    }
}
