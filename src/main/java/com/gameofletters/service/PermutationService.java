package com.gameofletters.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PermutationService {

    public Set<String> permute(String word, int desiredLength) {
        if (desiredLength > word.length())
            throw new RuntimeException("Permutation Length can not be greater than actual word length");
        return permute(word, desiredLength, new ArrayList<>());
    }

    private Set<String> permute(String word, int desiredWordLength, ArrayList<Integer> usedIndexes) {
        Set<String> wordList = new HashSet<>();
        for (int currentIndex = 0; currentIndex < word.length(); currentIndex++) {
            if (usedIndexes.contains(currentIndex)) {
                continue;
            }
            usedIndexes.add(currentIndex);
            if (usedIndexes.size() == desiredWordLength) {
                wordList.add(composeWordFromUsedIndexes(word, usedIndexes));
                usedIndexes.remove(new Integer(currentIndex));
                continue;
            }
            wordList.addAll(permute(word, desiredWordLength, new ArrayList<Integer>(usedIndexes)));
            usedIndexes.remove(new Integer(currentIndex));
        }
        return wordList;
    }

    private String composeWordFromUsedIndexes(String word, ArrayList<Integer> usedIndexes) {
        return usedIndexes.stream()
                .map(index -> "" + word.charAt(index))
                .reduce((acc, character) -> acc + character)
                .get();
    }


    public Set<String> permute(String word, int startLength, int endLength) {
        return IntStream.rangeClosed(startLength, endLength).parallel()
                .mapToObj(length -> permute(word, length))
                .reduce((accWordList, wordList) -> {accWordList.addAll(wordList); return accWordList;})
                .get();
    }
}
