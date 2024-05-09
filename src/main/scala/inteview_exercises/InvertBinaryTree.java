package inteview_exercises;


/*
Get Deepest node
 */
public class InvertBinaryTree {

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

        TreeNode i = invertTree(rootNode);
        System.out.println(i.val);
    }

    public static TreeNode invertTree(TreeNode node) {
        if (node != null) {
            swapTree(node, node.left, node.right);
        }
        return node;
    }

    private static void swapTree(TreeNode node, TreeNode left, TreeNode right) {
        node.left = right;
        node.right = left;
        if (left != null) {
            swapTree(left, left.left, left.right);
        }
        if (right != null) {
            swapTree(right, right.left, right.right);
        }
    }


}

