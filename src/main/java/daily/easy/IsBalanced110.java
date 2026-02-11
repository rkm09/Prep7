package daily.easy;

import common.TreeNode;

public class IsBalanced110 {
    public static void main(String[] args) {

    }

    public static boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    private static int dfsHeight(TreeNode node) {
        if(node == null) return 0;
        int leftHeight = dfsHeight(node.left);
        if(leftHeight == -1) return -1;
        int rightHeight = dfsHeight(node.right);
        if(rightHeight == -1) return -1;
        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
}

/*
Given a binary tree, determine if it is height-balanced(A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.).
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:
Input: root = []
Output: true
Constraints:
The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104
 */