package inteview_exercises;

import org.jetbrains.annotations.NotNull;

/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
*/
public class SameTree {
    public static void main(String[] args) {

        TreeNode rootNode = buildTreeNodes(5);
        TreeNode rootNode2 = buildTreeNodes(6);

      /*  TreeNode rightThree = rightTwoRight;
        TreeNode rightThreeLeft = new TreeNode(6);
        rightThree.left = rightThreeLeft;
        TreeNode rightThreeRight = new TreeNode(7);
        rightThree.right = rightThreeRight;*/

        boolean i = isSameTree(rootNode, rootNode2);
        System.out.println(i);
    }

    @NotNull
    private static TreeNode buildTreeNodes(int i) {
        TreeNode rootNode = new TreeNode(1);
        rootNode.val = 1;
        rootNode.left = new TreeNode(2);
        TreeNode rightTwo = new TreeNode(3);
        rootNode.right = rightTwo;

        TreeNode rightTwoLeft = new TreeNode(4);
        rightTwo.left = rightTwoLeft;
        TreeNode rightTwoRight = new TreeNode(i);
        rightTwo.right = rightTwoRight;
        return rootNode;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val == q.val) {
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        } else {
            return false;
        }
    }
}



