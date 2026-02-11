package daily.medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceBST1382 {
    public static void main(String[] args) {

    }

//    inorder + recursive; time: O(n), space: O(n)
    public TreeNode balanceBST(TreeNode root) {
//        create a list to store the inorder traversal of the BST
        List<Integer> inorder = new ArrayList<>();
        inOrderTraversal(root, inorder);
//        construct and return the balanced binary tree
        return createBalancedBST(inorder, 0, inorder.size() - 1);
    }

    private void inOrderTraversal(TreeNode root, List<Integer> inorder) {
//        perform an inorder traversal to store the elements in sorted order
        if(root == null) return;
        inOrderTraversal(root.left, inorder);
        inorder.add(root.val);
        inOrderTraversal(root.right, inorder);
    }

    private TreeNode createBalancedBST(List<Integer> inorder, int start, int end) {
//        base case: if start index is greater than the end index, return null
        if(start > end) return null;
//        find the middle element of the current range
        int mid = start + (end - start) / 2;
//        recursively construct the left and the right subtrees
        TreeNode leftSubtree = createBalancedBST(inorder, start, mid - 1);
        TreeNode rightSubtree = createBalancedBST(inorder, mid + 1, end);
//        create a new node with middle element and attach the left and the right subtrees
        return new TreeNode(
                inorder.get(mid),
                leftSubtree,
                rightSubtree
        );
    }
}

/*
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:
Input: root = [2,1,3]
Output: [2,1,3]
Constraints:
The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
 */

/*
We need to balance a binary search tree rooted at the root such that the difference between the depths of the two subtrees of every node never exceeds one. As a reminder, the depth of a given node in a tree is the number of edges from the root of the tree to that node.
Note: Binary search trees (BSTs) are structured such that the value of each node is greater than all values in its left subtree and less than all values in its right subtree.
We call such BSTs balanced BSTs. Balanced BSTs are efficient because they keep the tree height low, usually in logarithmic proportion to the number of nodes. This balance allows operations like insertion, deletion, and lookup to be done in logarithmic time on average. Keeping the tree balanced prevents it from becoming too deep, which would otherwise slow these operations down to linear time. This efficiency makes balanced BSTs ideal for tasks that need fast updates and quick searches.
There are two main approaches to balance a BST.
The first approach is to traverse and store all the BST nodes in a sorted array, then reconstruct the BST from scratch. Storing the values in sorted order ensures the new tree maintains the BST properties, where each node's left subtree contains only values less than the node's value, and the right subtree contains only values greater.
The second approach is to balance the BST in-place by restructuring it without additional storage. This involves performing rotations and rearrangements directly on the existing nodes to achieve balance while preserving BST properties.
This approach is more complex and is unlikely to be asked in an interview setting. However, it's worth understanding for deeper insights into tree rotations, balancing techniques, and the workings of self-balancing trees like AVL and Red-Black trees.

Inorder + recursive:
The stored values in the array have a convenient property: for any given element that serves as the root, all elements to its left belong to the left subtree, and all elements to its right belong to the right subtree. To construct a balanced BST, we pick the middle element of the array as the root, ensuring the number of elements in the left and right subtrees differs by at most one. We then recursively apply the same process to the left and right subarrays to build the left and right subtrees. This approach ensures the balanced property of the BST.
Let n be the number of nodes in the BST.
Time Complexity: O(n)
The inorderTraversal function visits each node exactly once, resulting in a time complexity of O(n).
Constructing the balanced BST with the createBalancedBST function also involves visiting each node exactly once, resulting in a time complexity of O(n).
Therefore, the overall time complexity is O(n).
Space Complexity: O(n)
The inorderTraversal function uses an additional array to store the inorder traversal, which requires O(n) space.
The recursive calls in the inorderTraversal and createBalancedBST functions contribute to the space complexity. In the worst case, the recursion stack can grow to O(n) for a skewed tree.
Therefore, the overall space complexity is O(n).

 */