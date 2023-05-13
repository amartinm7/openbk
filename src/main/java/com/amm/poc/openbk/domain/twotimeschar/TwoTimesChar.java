package com.amm.poc.openbk.domain.twotimeschar;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * given a string S, returns a single-character string: the single-character that occurs twice.
 *
 * examples:
 * 1. Given "aba", the function should return "a".
 * 2. Given "zz", the function should return "z".
 * 3. Given "codility", the function should return "i".
 */
public class TwoTimesChar {

    public String solution(String word) {
        String sanitizedWord = sanitizeWord(word);
        Set<Character> reducedChars = stringToSet(sanitizedWord);
        List<Character> allChars = stringToList(sanitizedWord);
        // iterate by the reducedChars and remove one by one
        for (Character c : reducedChars) {
            allChars.remove(c);
        }
        if (allChars.isEmpty()) {
            return "";
        }
        //at the end the allChars only retains the duplicated char
        return String.valueOf(allChars.get(0));
    }

    private String sanitizeWord(String word) {
        Pattern pattern = Pattern.compile("[^a-z]");
        String sanitizedWord = pattern.matcher(word).replaceAll("");
        if (sanitizedWord.isEmpty()) throw new RuntimeException(String.format("Invalid word! %s", word));
        return sanitizedWord;
    }

    // Remove duplicates
    private Set<Character> stringToSet(String word) {
       return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    // string to arrayList
    private List<Character> stringToList(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
}
