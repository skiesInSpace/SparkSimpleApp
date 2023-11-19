package inteview_exercises;

public class NumberPalindrome {

    // there are two most important things to remember
    // to get most right number, we can do 'num % 10'
    // to remove most right number, we can do 'num / 10'
    // both will work for any numbers
    public static void main(String[] args) {

        System.out.println(isPol(545));
    }

    public static boolean isPol(int number) {

        int numberCopy = number;
        int reminder = 0;
        int result = 0;

        while (number > 0) {
            reminder = number % 10;
            result = (result * 10) + reminder;
            number = number / 10;
        }
        return result==numberCopy;
    }
}
