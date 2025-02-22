package com.batistes.kskb.api.util;

import java.util.Date;

public class Utils {
    public static java.sql.Date convertUtilDateToSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
