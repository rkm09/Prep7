package daily.medium;

import common.ListNode;

public class RotateList61 {
    public static void main(String[] args) {

    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        ListNode curr = head;
        int n = 1, count = 1;
        while(curr.next != null) {
            curr = curr.next;
            n++;
        }
        k %= n;
//        this step is crucial
        if(k == 0) return head;
        curr = head;
        while(count <= (n - k)) {
            if(count == n - k) {
//                save the link before the cut
                ListNode temp = curr.next;
//                cut
                curr.next = null;
//                reset curr to point to the beginning
                curr = head;
//                make the final head point at the right position
                head = temp;
                while(temp.next != null)
                    temp = temp.next;
//                attach
                temp.next = curr;
                break;
            } else
                curr = curr.next;
            count++;
        }

        return head;
    }
}

/*
Given the head of a linked list, rotate the list to the right by k places.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]
Constraints:
The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 10^9
 */