package inteview_exercises;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/*
Get Deepest node
 */
public class DeepestNode {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        rootNode.val = 1;
        rootNode.left = new TreeNode(2);
        TreeNode rightTwo = new TreeNode(3);
        rootNode.right = rightTwo;

//        TreeNode rightTwoLeft = new TreeNode(4);
//        rightTwo.left = rightTwoLeft;
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

        TreeNode i = getDeepest(rootNode);
        System.out.println(i.val);
    }

    public static TreeNode getDeepest(TreeNode node) {
        if (node == null) {
            return null;
        }
        return getDeepest(new ImmutablePair<>(node, 1)).getKey();
    }

    public static Pair<TreeNode, Integer> getDeepest(Pair<TreeNode, Integer> pair) {
        //3, 2
        //
        TreeNode current = pair.getKey();
        Integer index = pair.getValue();
        if (current.left == null && current.right == null) {
            return pair;
        }

        Pair<TreeNode, Integer> left = null;
        Pair<TreeNode, Integer> right = null;
        if (current.left != null) {
            left = getDeepest(new ImmutablePair<>(current.left, index++));
        }
        if (current.right != null) {
            right = getDeepest(new ImmutablePair<>(current.right, index++));
        }
        if (left == null && right == null) {
            return pair;
        }
        if (left == null) {
            return getDeepest(new ImmutablePair<>(current.right, index++));
        }
        if (right == null) {
            return getDeepest(new ImmutablePair<>(current.left, index++));
        }
        return left.getValue() >= right.getValue() ? left : right;
    }

}

