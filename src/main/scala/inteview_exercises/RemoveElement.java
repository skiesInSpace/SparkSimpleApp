package inteview_exercises;

/*
Tricky algorithm, very simple from code perspective, but complicated for understanding.
j stops if i finds bad element, i - goes through all elements without stop and uses them for replacement of elements on j position
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = {6, 2, 3, 5, 2, 2, 2, 4, 2};
        int j = removeElement(arr, 2);
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
