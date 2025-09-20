package daily.easy;

import java.util.HashMap;
import java.util.Map;

public class IsMajority1150 {
    public static void main(String[] args) {
        int[] nums = {2,4,5,5,5,5,5,6,6};
        IsMajority1150 i = new IsMajority1150();
        System.out.println(i.isMajorityElement(nums, 5));
    }


    //    frequency count; time: O(n), space: O(1)
    public boolean isMajorityElement2(int[] nums, int target) {
        int count = 0;
        for(int num : nums) {
            count = num == target ? count + 1 : count;
        }
        return count > nums.length / 2;
    }

//    one pass binary search; time: O(logn), space: O(1)
//    we only need to find if there are more than num.length / 2 instances of target, not the exact count of target. If at num.length / 2 places ahead of the first index, the element is equal to target, then we know target is a majority element in the given list.
    public boolean isMajorityElement(int[] nums, int target) {
        int firstIndex = lowerBound(nums, target);
        int n = nums.length;
        return (firstIndex + n / 2 < n) && (nums[firstIndex + n / 2] == target);
    }

//    binary search(two pass); time: O(logn), space: O(1)
    public boolean isMajorityElement1(int[] nums, int target) {
        int firstIndex = lowerBound(nums, target);
        int nextToLastIndex = upperBound(nums, target);
        return nextToLastIndex - firstIndex > nums.length / 2;
    }

//    returns the first index greater than or equal to the target; if not found returns the length of the array
    public int lowerBound(int[] nums, int target) {
        int n = nums.length;
        int start = 0, index = n;
        int end = n - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] >= target) {
                end = mid - 1;
                index = mid;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

//    returns the first index greater than the target; if not found returns the length of the array
    public int upperBound(int[] nums, int target) {
        int n = nums.length;
        int start = 0, index = n;
        int end = n - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] > target) {
                end = mid - 1;
                index = mid;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }


//    map; time: O(n), space: O(n)
    public boolean isMajorityElementN(int[] nums, int target) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        return freq.getOrDefault(target, 0) > n / 2;
    }


}

/*
Given an integer array nums sorted in non-decreasing order and an integer target, return true if target is a majority element, or false otherwise.
A majority element in an array nums is an element that appears more than nums.length / 2 times in the array.
Example 1:
Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
Explanation: The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 > 9/2 is true.
Example 2:
Input: nums = [10,100,101,101], target = 101
Output: false
Explanation: The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 > 4/2 is false.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i], target <= 109
nums is sorted in non-decreasing order.
 */