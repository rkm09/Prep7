package daily.medium;

import common.ListNode;

public class DeleteMiddle2095 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(1, null);
        ListNode next1 = new ListNode(2, next2);
        ListNode next = new ListNode(4, next1);
        ListNode ln = new ListNode(5, next);
        ListNode res = deleteMiddle(ln);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    using 2 instead of 2 pointer references; time: O(n), space: O(1)
    public static ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;

        return head;
    }

//  simulation using fast and slow; time: O(n), space: O(1)
    public static ListNode deleteMiddle1(ListNode head) {
        if (head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;

        return head;
    }
}


/*
You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
Example 1:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.
Example 2:
Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:
Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.

Constraints:
The number of nodes in the list is in the range [1, 10^5].
1 <= Node.val <= 10^5
 */