package inteview_exercises;

import static java.util.Objects.nonNull;

/*
You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such
that x is in one of the ranges but not in nums.
*/
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        rootNode.val = 1;
        rootNode.left = new TreeNode(2);
        TreeNode rightTwo = new TreeNode(2);
        rootNode.right = rightTwo;

        TreeNode rightTwoLeft = new TreeNode(3);
        rightTwo.left = rightTwoLeft;
        TreeNode rightTwoRight = new TreeNode(3);
        rightTwo.right = rightTwoRight;

        TreeNode rightThree = rightTwoRight;
        TreeNode rightThreeLeft = new TreeNode(4);
        rightThree.left = rightThreeLeft;
        TreeNode rightThreeRight = new TreeNode(4);
        rightThree.right = rightThreeRight;

        int i = maxDepth(rootNode);
        System.out.println(i);
    }

    public static int maxDepth(TreeNode root) {

        if(root == null){
            return 0;
        }
        int leftCount = maxDepth(root.left);
        int rightCount = maxDepth(root.right);

        return Math.max(leftCount, rightCount) + 1;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

