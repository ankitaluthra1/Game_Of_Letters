package com.gameofletters.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class PermutationServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnPermutationListForThreeLengthWord() throws Exception {
        List<String> expectedList = Arrays.asList("abc", "acb", "bac" , "bca", "cab", "cba");
        Set<String> actualList = new PermutationService().permute("abc", 3);
        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    @Test
    public void shouldThrowExceptionForInvalidPermutationLength() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Permutation Length can not be greater than actual word length");
        new PermutationService().permute("abc", 4);

    }

    @Test
    public void shouldReturnPermutaionListForRepeatLetter() throws Exception {
        List<String> expectedList = Arrays.asList("aba", "aab", "baa");
        Set<String> actualList = new PermutationService().permute("aba", 3);
        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));

    }

    @Test
    public void shouldReturnAllPermutationsForWordLengthInRange() throws Exception {
        List<String> actualList = Arrays.asList("ab", "ac", "ba", "bc", "ca", "cb", "abc", "acb", "bac", "bca", "cab", "cba");
        Set<String> wordList = new PermutationService().permute("abc", 2, 3);
        assertThat(actualList, containsInAnyOrder(wordList.toArray()));

    }

    @Test
    public void shouldThrowExceptionForInvalidRange() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Permutation Length can not be greater than actual word length");
        Set<String> wordList = new PermutationService().permute("abc", 2, 5);
    }
}