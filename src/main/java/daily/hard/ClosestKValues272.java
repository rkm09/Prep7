package daily.hard;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ClosestKValues272 {
    public static void main(String[] args) {

    }

//    sort with custom comparator; time: O(nlogn), space: O(n)
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> resList = new ArrayList<>();
        dfs(root, resList);
        resList.sort((a,b) -> Math.abs(a - target) <= Math.abs(b - target) ? -1 : 1);
        return resList.subList(0, k);
    }

    private static void dfs(TreeNode node, List<Integer> resList) {
        if(node == null)
            return;
        resList.add(node.val);
        dfs(node.left, resList);
        dfs(node.right, resList);
    }
}

/*
Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example 1:
Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]
Example 2:
Input: root = [1], target = 0.000000, k = 1
Output: [1]
Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 104.
0 <= Node.val <= 109
-109 <= target <= 109
Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
 */
