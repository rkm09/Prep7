package daily.medium;

import common.ListNode;

import java.util.HashSet;

public class ModifiedList3217 {
    public static void main(String[] args) {
//        listNode: [2,10,9], array: {9,2,5}, expected: [10]
        ListNode next2 = new ListNode(9);
        ListNode next1 = new ListNode(10, next2);
        ListNode head = new ListNode(2, next1);
        int[] nums = {9,2,5};
        ListNode res = modifiedList(nums, head);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    hashset; time: O(m + n), space: O(m)
    public static ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> hs = new HashSet<>();
        for(int num : nums)
            hs.add(num);
        ListNode prev = new ListNode();
        ListNode res = prev;
        while(head != null) {
            if(!hs.contains(head.val)) {
                prev.next = new ListNode(head.val);
                prev = prev.next;
            }
            head = head.next;
        }
        return res.next;
    }
}

/*
You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.
Example 1:
Input: nums = [1,2,3], head = [1,2,3,4,5]
Output: [4,5]
Explanation:
Remove the nodes with values 1, 2, and 3.
Example 2:
Input: nums = [1], head = [1,2,1,2,1,2]
Output: [2,2,2]
Explanation:
Remove the nodes with value 1.
Example 3:
Input: nums = [5], head = [1,2,3,4]
Output: [1,2,3,4]
Explanation:
No node has value 5.
Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are unique.
The number of nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
The input is generated such that there is at least one node in the linked list that has a value not present in nums.
 */