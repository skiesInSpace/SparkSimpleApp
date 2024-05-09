package inteview_exercises;

/*
Given two strings needle and haystack,
return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class FindTheIndexOfTheFirstOccurrenceInString {

    static String input = "12345";

    public static void main(String[] args) {
        String str1 = "leetcode";
        String str2 = "leet";
        System.out.println(strStr(str1, str2));
    }

    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
