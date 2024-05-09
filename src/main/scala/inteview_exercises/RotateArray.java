package inteview_exercises;

import java.util.Arrays;

/*
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class RotateArray {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        int steps = 3;
        rotate(arr, steps);
        System.out.println();
    }

    public static void rotate(int[] nums, int k) {
        int[] resultArr = new int[nums.length];
        int j = nums.length - k;
        // i - result position
        // j - initial arr position
        for (int i = 0; j < nums.length; i++, j++) {
            resultArr[i] = nums[j];
        }
        j = 0;
        for (int i = k; i < nums.length; i++, j++) {
            resultArr[i] = nums[j];
        }
        nums = resultArr;
    }
}
