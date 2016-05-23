package com.gameofletters.repository;

import com.gameofletters.config.DataSourceConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AllWordRepositoryTest {

    private static DataSource dataSource;

    @BeforeClass
    public static void setUpDataSource() throws Exception {
        dataSource = DataSourceConfig.getDataSource();
    }

    @Before
    public void setUp() throws Exception {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        queryRunner.update("delete from ALL_WORDS");
        queryRunner.update("Insert into ALL_WORDS(word) values(?)", "aardvark");
        queryRunner.update("Insert into ALL_WORDS(word) values(?)", "abandons");
        queryRunner.update("Insert into ALL_WORDS(word) values(?)", "abattoir");
        queryRunner.update("Insert into ALL_WORDS(word) values(?)", "abdicate");
        queryRunner.update("Insert into ALL_WORDS(word) values(?)", "abnormal");
    }


    @Test
    public void shouldFetchListOfIdsForGivenWords() throws Exception {
        AllWordRepository repository = new AllWordRepository();
        List<String> wordList = Arrays.asList("aardvark", "abnormal");
        List<Integer> ids = repository.fetchIdsForWords(wordList);
        assert ids.size() == 2;
    }
}