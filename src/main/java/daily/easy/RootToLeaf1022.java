package daily.easy;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RootToLeaf1022 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(0);
        TreeNode right1 = new TreeNode(1);
        TreeNode left2 = new TreeNode(0);
        TreeNode right2 = new TreeNode(1);
        TreeNode leftChild = new TreeNode(0, left1, right1);
        TreeNode rightChild = new TreeNode(1, left2, right2);
        TreeNode root = new TreeNode(1, leftChild, rightChild);
        RootToLeaf1022 l = new RootToLeaf1022();
        System.out.println(l.sumRootToLeaf(root));
    }

    public int sumRootToLeaf(TreeNode root) {
        int sum = 0;
        List<String> list = new ArrayList<>();
        dfs(root, list, String.valueOf(root.val));
        for(String val : list)
            sum += Integer.parseInt(val, 2);
        return sum;
    }

    private void dfs(TreeNode node, List<String> list, String num) {
        if (node.left == null && node.right == null) {
            list.add(num);
            return;
        }
        if (node.left != null)
            dfs(node.left, list, num + node.left.val);
        if (node.right != null)
            dfs(node.right, list, num + node.right.val);
    }
}

/*
You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a binary number starting with the most significant bit.
For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these numbers.
The test cases are generated so that the answer fits in a 32-bits integer.
Example 1:
Input: root = [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
Example 2:
Input: root = [0]
Output: 0
Constraints:
The number of nodes in the tree is in the range [1, 1000].
Node.val is 0 or 1.
 */