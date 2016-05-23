package com.gameofletters.repository;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gameofletters.config.DataSourceConfig.getDataSource;

public class AllWordRepository {

    private final DataSource dataSource;

    public AllWordRepository() throws Exception {
        this.dataSource = getDataSource();
    }

    public List<Integer> fetchIdsForWords(List<String> wordList) throws SQLException {
        try{
            QueryRunner queryRunner = new QueryRunner(dataSource);
            List<String> words = wordList.stream()
                                         .map(str -> "'" + str + "'")
                                         .collect(Collectors.toList());
            String parameters = String.join(",", words);
            List<Map<String, Object>> mapList = queryRunner.query("select id from ALL_WORDS where word in ("+parameters+")",
                    new MapListHandler());
            return mapList.stream()
                    .map(map -> (Integer) map.get("id"))
                    .collect(Collectors.toList());
        }finally {
            DbUtils.closeQuietly(dataSource.getConnection());
        }
    }
}
