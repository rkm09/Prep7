package daily.medium;

import common.TreeNode;

public class InorderSuccessor285 {
    public static void main(String[] args) {

    }

//    using BST property; time: O(n), space: O(1)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while(root != null) {
            if(p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}

/*
Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.
The successor of a node p is the node with the smallest key greater than p.val.
Example 1:
Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
Example 2:
Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.

Constraints:
The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
All Nodes will have unique values.
 */

/*
the optimal solution for this problem comes from utilizing those properties and that's what we will explore in this solution. Specifically, we'll make use of the standard BST property where the left descendants have smaller values than the current node and the right descendants have larger values than the current node. We don't need to handle any specific cases here and we can start with the root node directly and reach our inorder successor.
Complexity Analysis
Time Complexity: O(N) since we might end up encountering a skewed tree and in that case, we will just be discarding one node at a time. For a balanced binary search tree, however, the time complexity will be O(logN) which is what we usually find in practice.
Space Complexity: O(1) since we don't use recursion or any other data structures for getting our successor.
 */