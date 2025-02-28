package com.batistes.kskb.api.util;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Utils {
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


}
