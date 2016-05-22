package com.gameofletters.repository;

import com.gameofletters.config.DataSourceConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

public class EightLetterWordRepositoryTest {

    private static DataSource dataSource;

    @BeforeClass
    public static void setUpDataSource() throws Exception {
        dataSource = DataSourceConfig.getDataSource();
    }

    @Before
    public void setUp() throws Exception {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        queryRunner.update("delete from EIGHT_LETTER_WORDS");
        queryRunner.update("Insert into EIGHT_LETTER_WORDS(word) values(?)", "aardvark");
        queryRunner.update("Insert into EIGHT_LETTER_WORDS(word) values(?)", "abandons");
        queryRunner.update("Insert into EIGHT_LETTER_WORDS(word) values(?)", "abattoir");
        queryRunner.update("Insert into EIGHT_LETTER_WORDS(word) values(?)", "abdicate");
        queryRunner.update("Insert into EIGHT_LETTER_WORDS(word) values(?)", "abnormal");


    }

    @Test
    public void shouldFetchAllWordsFromDatabase() throws Exception {
        EightLetterWordRepository repo = new EightLetterWordRepository();
        List<String> wordList = repo.fetchAllWords();
        assert wordList.size() == 5;
    }
}