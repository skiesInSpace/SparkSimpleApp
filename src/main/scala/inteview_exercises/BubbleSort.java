package inteview_exercises;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 1, 1, 4, 2, 3};
        sort(arr);
        System.out.println();
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }

        }
    }
}
