package inteview_exercises;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = {1, 4, 8, 0, 0};
        int[] nums2 = {2, 4};
        int m = 3;
        int n = 2;
        merge(nums1, m, nums2, n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int pMerge = m + n - 1;

        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[pMerge--] = nums1[p1--];
            } else {
                nums1[pMerge--] = nums2[p2--];
            }
        }
        System.out.println("debug");
    }
}
