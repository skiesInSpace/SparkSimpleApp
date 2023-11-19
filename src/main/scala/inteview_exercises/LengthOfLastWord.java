package inteview_exercises;

/*
Given a string s consisting of words and spaces, return the length of the last word in the string.

A word is a maximal
substring
 consisting of non-space characters only.
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        String str = "a";
        System.out.println(lengthOfLastWord(str));
    }

    public static int lengthOfLastWord(String s) {
        char[] arr = s.toCharArray();
        int counter = 0;
        for (int i = arr.length - 1; i > -1; i--) {
            if (arr[i] != 32) {
                counter++;
            }
            if (counter != 0 && arr[i] == 32) {
                return counter;
            }
        }
        return counter;
    }
}
