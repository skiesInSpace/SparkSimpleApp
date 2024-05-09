package inteview_exercises;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

/*
Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 */

    static String pattern = "abba";
    static String input = "dog cat cat dog";

    public static void main(String[] args) {
        boolean isPattern = wordPattern(pattern, input);
        System.out.println(isPattern);
    }

    //ab_a
    public static boolean wordPattern(String pattern, String s) {
        // word -> interator position
        // in each map word and letter number are keys, and index position is value
        Map<String, Integer> wordMap = new HashMap<>();
        int[] letterMap = new int[256];

        String[] words = s.split(" ");
        char[] letters = pattern.toCharArray();

        if (words.length != letters.length) {
            return false;
        }

        for (int i = 0; i < letters.length; i++) {
            Integer currentWordPosition = wordMap.get(words[i]);
            currentWordPosition = wordMap.get(words[i]) == null ? 0 : wordMap.get(words[i]);
            int currentLetterPosition = letterMap[letters[i]];
            if (currentWordPosition != currentLetterPosition) {
                return false;
            }
            wordMap.put(words[i], i + 1);
            char letter = letters[i];
            letterMap[letter] = i + 1;

        }

        return true;

    }
}
