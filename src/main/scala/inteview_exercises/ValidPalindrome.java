package inteview_exercises;

public class ValidPalindrome {

/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.
 */

    static String input = ".a";

    public static void main(String[] args) {
        boolean palindrome = isPalindrome(input);
        System.out.println(palindrome);
    }

    //ab_a
    public static boolean isPalindrome(String s) {
//        String clearString = s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
        if (s.isEmpty() || s.length()==1) {
            return true;
        }
        char[] charArray = s.toCharArray();
        int i = charArray.length - 1;
        int j = 0;
        while (i > -1 && j<charArray.length) {
            String tailStr = String.valueOf(charArray[i]);
            String headStr = String.valueOf(charArray[j]);
            if (headStr.matches("[^a-zA-Z0-9]")) {
                j++;
                continue;
            }
            if (tailStr.matches("[^a-zA-Z0-9]")) {
                i--;
                continue;
            }
            if (!headStr.equalsIgnoreCase(tailStr)) {
                return false;
            }
            i--;
            j++;
        }
        return true;

    }
}
