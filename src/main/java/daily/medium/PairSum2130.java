package daily.medium;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class PairSum2130 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(1, null);
        ListNode next1 = new ListNode(2, next2);
        ListNode next = new ListNode(4, next1);
        ListNode ln = new ListNode(5, next);
        System.out.println(pairSum(ln));
    }

//    using pointers; time: O(n), space: O(1)
    public static int pairSum(ListNode head) {
        if (head == null) return 0;
//        get the mid using slow and fast pointers
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

//        reverse the second half
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

//        add the elements from first and second half and return the max twin sum
        int maxSum = 0;
        ListNode firstHalf = head;
        ListNode secondHalf = prev;  // head of the reversed half
        while (secondHalf != null) {
            maxSum = Math.max(maxSum, firstHalf.val + secondHalf.val);
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return maxSum;
    }

//    simulation; time: O(n), space: O(n)
    public static int pairSum1(ListNode head) {
        List<Integer> li = new ArrayList<>();
        ListNode node = head;
        while(node != null) {
            li.add(node.val);
            node = node.next;
        }

        int res = 0, n = li.size();
        for (int i = 0; i < n / 2; i++) {
            res = Math.max(res, li.get(i) + li.get(n - i - 1));
        }

        return res;
    }
}

/*
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.
Given the head of a linked list with even length, return the maximum twin sum of the linked list.
Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.
Example 2:
Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
Example 3:
Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.

Constraints:
The number of nodes in the list is an even integer in the range [2, 10^5].
1 <= Node.val <= 10^5
 */
