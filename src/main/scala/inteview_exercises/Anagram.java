package inteview_exercises;

public class Anagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("rat", "car"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[300];
        char[] charArr1 = s.toCharArray();
        char[] charArr2 = t.toCharArray();
        for (char c : charArr1) {
            arr[c] = arr[c]+1;
        }
        for (char c : charArr2) {
            arr[c] = arr[c]-1;
        }
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
