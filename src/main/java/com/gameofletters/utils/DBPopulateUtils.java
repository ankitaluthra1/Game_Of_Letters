package com.gameofletters.utils;

import com.gameofletters.properties.ApplicationProperties;
import com.gameofletters.repository.AllWordRepository;
import com.gameofletters.repository.EightLetterWordRepository;
import com.gameofletters.service.PermutationService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DBPopulateUtils {

    private EightLetterWordRepository eightLetterWordRepository;
    private PermutationService permutationService;
    private AllWordRepository allWordRepository;

    public DBPopulateUtils() throws Exception {
        eightLetterWordRepository = new EightLetterWordRepository();
        permutationService = new PermutationService();
        allWordRepository = new AllWordRepository();
    }

    public static void main(String[] args) throws Exception {
        DBPopulateUtils utils = new DBPopulateUtils();
        utils.populate();
    }

    private void populate() throws SQLException {
        List<String> eightLetterWordList = eightLetterWordRepository.fetchAllWords();
        System.out.println("Startddddd,,,ing migration for "+ eightLetterWordList.size());
        eightLetterWordList.stream().forEach(
                                word -> {
                                    Set<String> permutedWords = permutationService.permute(word, 3, 8);
                                    try {
                                        List<Integer> ids = allWordRepository.fetchIdsForWords(new ArrayList<>(permutedWords));
                                        eightLetterWordRepository.updatePermutationWords(word, ids);
                                        System.out.println("Migration completed for word: "+word+" with list: " + ids);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });
    }

}
