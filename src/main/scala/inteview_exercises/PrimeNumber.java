package inteview_exercises;

public class PrimeNumber {
    // A prime number is a number that can only be divided by itself and 1 without remainder.
    // 2
    public static void main(String[] args) {

        boolean prime = isPrime(13);
        System.out.println(prime);

    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
