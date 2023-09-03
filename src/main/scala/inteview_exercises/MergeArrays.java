package inteview_exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeArrays {

    /*
    Merge two sorted arrays: Write a Java function that takes two sorted arrays as input
    and returns a new array that contains all the elements of both arrays, also sorted in ascending order.
     */

    static Integer[] mergeArrays(int[] arr, int[] arr2) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            list.add(arr2[i]);
        }
        list.sort(Comparator.comparingInt(Integer::intValue));
        return list.toArray(new Integer[]{});
    }

    public static void main(String[] args) {
        Object[] objects = mergeArrays(new int[]{1, 2, 8}, new int[]{5, 6, 7});
        System.out.println(Arrays.toString(objects));

    }
}
