package inteview_exercises;

public class StringReverse {

    static String input = "12345";

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(input.length() - i - 1);
            stringBuilder.append(c);
        }
        System.out.println(stringBuilder);
    }
}
