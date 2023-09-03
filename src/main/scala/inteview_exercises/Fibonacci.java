package inteview_exercises;

public class Fibonacci {
    /*
    Find the nth Fibonacci number: Write a Java function that takes an integer n as input
    and returns the nth number in the Fibonacci sequence.
    The Fibonacci sequence is defined as follows: the first two numbers are 0 and 1,
    and each subsequent number is the sum of the previous two.

    0 1 1 2 3 5 8 13 21 34
     */

    public static int fib(int input) {
        int prev = 1;
        int beforePrev = 0;
        if (input == 1) {
            return 0;
        }
        if (input == 2) {
            return 1;
        }
        //input 6 -> 5
        prev = fib(input - 1);
        beforePrev = fib(input - 2);
        return prev + beforePrev;
    }

    public static void main(String[] args) {
        System.out.println(fib(8));
    }
}
