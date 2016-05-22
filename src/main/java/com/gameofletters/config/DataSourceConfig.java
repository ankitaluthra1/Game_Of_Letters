package com.gameofletters.config;

import com.gameofletters.properties.ApplicationProperties;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

import javax.sql.DataSource;

public class DataSourceConfig {

    private static final String JDBC_URL = ApplicationProperties.getJdbcUrl();

    public static DataSource getDataSource() throws Exception {
        SQLiteJDBCLoader.initialize();
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(JDBC_URL);
        return dataSource;
    }
}
