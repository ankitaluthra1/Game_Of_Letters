package com.gameofletters.properties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationProperties {

    private static final Config config = ConfigFactory.load(getPropertyFileName());
    private static final String jdbcUrlKey = "jdbcUrl";

    public static String getJdbcUrl() {
        return config.getString(jdbcUrlKey);
    }

    private static String getActiveProfile() {
        return null == System.getProperty("profileId") ? "dev" : System.getProperty("profileId");
    }

    private static String getPropertyFileName() {
        return "application" + (getActiveProfile().equals("dev") ? "" : "-" + getActiveProfile()) + ".properties";
    }

}
