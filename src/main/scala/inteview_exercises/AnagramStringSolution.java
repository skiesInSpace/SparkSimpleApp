package inteview_exercises;

public class AnagramStringSolution {

    public static void main(String[] args) {
        System.out.println(isAnagram("", ""));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        String replaced = s;
        for (int i = 0; i < t.toCharArray().length; i++) {
            replaced = replaced.replaceFirst(String.valueOf(t.charAt(i)), "");
        }
        return replaced.isEmpty();
    }
}
