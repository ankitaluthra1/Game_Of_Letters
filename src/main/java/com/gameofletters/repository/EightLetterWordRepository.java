package com.gameofletters.repository;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gameofletters.config.DataSourceConfig.getDataSource;

public class EightLetterWordRepository {

    private final DataSource dataSource;

    public EightLetterWordRepository() throws Exception {
        dataSource = getDataSource();
    }

    public List<String> fetchAllWords() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        List<Map<String, Object>> mapList = queryRunner.query("select word from EIGHT_LETTER_WORDS",
                new MapListHandler());
        return mapList.stream()
                .map(map -> (String) map.get("word"))
                .collect(Collectors.toList());
    }
}
