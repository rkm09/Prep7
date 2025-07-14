package daily.easy;

import common.ListNode;

public class GetDecimalValue1290 {
    public static void main(String[] args) {
        ListNode l3 = new ListNode(1);
        ListNode l2 = new ListNode(0, l3);
        ListNode l1 = new ListNode(1, l2);
        System.out.println(getDecimalValue(l1));
    }

//    bit manipulation; time: O(n), space: O(1)
    public static int getDecimalValue(ListNode head) {
        int num = head.val;
        while(head.next != null) {
            num = (num << 1) | head.next.val;
            head = head.next;
        }
        return num;
    }

//    basic arithmetic; time:O(n), space: O(1)
    public static int getDecimalValue1(ListNode head) {
        int num = head.val;
        while(head.next != null) {
            num = (2 * num) + head.next.val;
            head = head.next;
        }
        return num;
    }
}


/*
Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
Return the decimal value of the number in the linked list.
The most significant bit is at the head of the linked list.

Example 1:
Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
Example 2:
Input: head = [0]
Output: 0

Constraints:
The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.
 */

/*
To parse a non-empty linked list and to retrieve the digit sequence that represents a binary number.
To convert this sequence into the number in decimal representation.
The first sub problem is easy because the linked list is guaranteed to be non-empty.
 */