package inteview_exercises;

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
public class SymmetricTree {

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

        boolean i = isSymmetric(rootNode);
        System.out.println(i);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                return true;
            }
            if (root.left == null || root.right == null || root.left.val != root.right.val) {
                return false;
            } else {
                return isSymmetric(root.left, root.right);
            }
        } else {
            return true;
        }
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

}
