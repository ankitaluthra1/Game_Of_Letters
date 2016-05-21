package com.gameofletters.properties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationProperties {

    private static final Config config = ConfigFactory.load();
    private static final String jdbcUrlKey = "jdbcUrl";
    private static final String jdbcDriverKey = "jdbcDriver";

    public static String getJdbcUrl(){
        return config.getString(jdbcUrlKey);
    }

    public static String getJdbcDriver(){
        return config.getString(jdbcDriverKey);
    }

}
