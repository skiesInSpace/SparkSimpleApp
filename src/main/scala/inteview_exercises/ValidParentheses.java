package inteview_exercises;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such
that x is in one of the ranges but not in nums.
*/
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("[([]])"));
    }

    public static boolean isValid(String s) {
        Map<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put('(', ')');
        bracketsMap.put('{', '}');
        bracketsMap.put('[', ']');

        Deque<Character> deque = new ArrayDeque<>();
        for (Character current : s.toCharArray()) {
            Character last = deque.peekLast();
            if (bracketsMap.get(last) == current) {
                deque.removeLastOccurrence(last);
            } else {
                deque.add(current);
            }
        }

        return deque.isEmpty();
    }
}
