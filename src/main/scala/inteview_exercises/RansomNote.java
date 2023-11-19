package inteview_exercises;

/*
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters
from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.
*/
public class RansomNote {
    public static void main(String[] args) {

        System.out.println(canConstruct("aape", "aabmdpe"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] count = new int[26];
        for (char ch : magazine.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            if (count[ch - 'a'] == 0) return false;
            count[ch - 'a']--;
        }
        return true;
    }
}
