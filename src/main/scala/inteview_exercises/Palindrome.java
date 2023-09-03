package inteview_exercises;

public class Palindrome {

//    Check if a string is a palindrome: Write a Java function that takes a string as input
//    and returns true if the string is a palindrome (reads the same forwards and backwards), and false otherwise.

    static String input = "aba";
    public static void main(String[] args) {
        System.out.println(new StringBuilder(input).reverse().toString().equals(input));
    }
}
