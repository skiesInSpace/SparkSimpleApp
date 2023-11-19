package inteview_exercises;

import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array. */
public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {6, 6,6,7,7};
        ;
        System.out.println(majorityElement(arr));
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : nums) {
            if (map.containsKey(number)) {
                map.compute(number, (integer, integer2) -> integer2 + 1);
            } else {
                map.put(number, 1);
            }
        }
        int majorKey = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                majorKey = entry.getKey();
            }
        }
        return majorKey;
    }
}
