package daily.medium;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxProduct1339 {
    private static final long MOD = (long) 1e9 + 7;
    public static void main(String[] args) {
        MaxProduct1339 m = new MaxProduct1339();
    }

    public int maxProduct(TreeNode root) {
        long total = dfs(root), ans = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) continue;
            long curr = (total - node.val) * node.val ;
            ans = Math.max(ans, curr);

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return (int) (ans % MOD);
    }

    private long dfs(TreeNode node) {
        if(node == null) return 0;
        node.val = (int) (node.val + dfs(node.left) + dfs(node.right));
        return node.val;
    }
}


/*
Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
Note that you need to maximize the answer before taking the mod and not after taking it.
Example 1:
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
Example 2:
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
Constraints:
The number of nodes in the tree is in the range [2, 5 * 104].
1 <= Node.val <= 104
 */