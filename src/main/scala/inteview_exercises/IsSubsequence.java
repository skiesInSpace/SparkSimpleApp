package inteview_exercises;

/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string
by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
(i.e., "ace" is a subsequence of "abcde" while "aec" is not).
*/
public class IsSubsequence {
    public static void main(String[] args) {

        System.out.println(isSubsequence("ace", "abcda"));
    }

    public static boolean isSubsequence(String s, String t) {
        if(s.isEmpty()){
            return true;
        }
        char[] subString = s.toCharArray();
        int currentPos = 0;
        boolean finded = false;
        for (char c : subString) {
            finded = false;
            System.out.println(c);
            char[] fullString = t.toCharArray();
            for (int i = currentPos; i < fullString.length; i++) {
                System.out.println(currentPos);
                currentPos = i + 1;
                if (fullString[i] == c) {
                    finded = true;
                    break;
                }
            }

        }
        return finded;
    }
}
