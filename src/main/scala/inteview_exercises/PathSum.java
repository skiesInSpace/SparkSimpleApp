package inteview_exercises;


/*
Given the root of a binary tree and an integer targetSum, return true if the tree has
a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        rootNode.val = 1;
        rootNode.left = new TreeNode(2);
        TreeNode rightTwo = new TreeNode(3);
        rootNode.right = rightTwo;

        TreeNode rightTwoLeft = new TreeNode(4);
        rightTwo.left = rightTwoLeft;
        TreeNode rightTwoRight = new TreeNode(5);
        rightTwo.right = rightTwoRight;
//
        TreeNode rightThree = rightTwoRight;
        TreeNode rightThreeLeft = new TreeNode(6);
        rightThree.left = rightThreeLeft;
//        TreeNode rightThreeRight = new TreeNode(7);
//        rightThree.right = rightThreeRight;
//
//        TreeNode sixToEight = new TreeNode(8);
//        rightThreeLeft.left = sixToEight;

        System.out.println(hasPathSum(rootNode, 25));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {

        return false;
    }
}

