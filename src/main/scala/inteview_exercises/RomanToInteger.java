package inteview_exercises;


import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("XIIIV"));
    }


}

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int finalNumber = 0;
        char[] arr = s.toCharArray();
        char nextChar;

        int i = arr.length-1;
        while (i > -1) {
            char currentChar = arr[i];
            if (i != 0) {
                nextChar = arr[i - 1];
            } else {
                nextChar = currentChar;
            }
            Integer currentNumber = map.get(currentChar);
            Integer nextNumber = map.get(nextChar);
            if (nextNumber < currentNumber) {
                finalNumber += (currentNumber - nextNumber);
                i--;
            } else {
                finalNumber += currentNumber;
            }
            i--;
        }

        return finalNumber;
    }
}