package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestBalanced3719 {
    public static void main(String[] args) {
        int[] nums = {2,5,4,3};
        System.out.println(longestBalanced(nums));
    }

//    brute force; time: O(n^2), space: O(n)
    public static int longestBalanced(int[] nums) {
        int len = 0, n = nums.length;
        for(int i = 0 ; i < n ; i++) {
            Map<Integer, Integer> oddMap = new HashMap<>();
            Map<Integer, Integer> evenMap = new HashMap<>();
            for(int j = i ; j < n ; j++){
                Map<Integer, Integer> currMap = (nums[j] & 1) == 1 ? oddMap : evenMap;
                currMap.put(nums[j], currMap.getOrDefault(nums[j], 0) + 1);
                if(oddMap.size() == evenMap.size())
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }
}

/*
You are given an integer array nums.
A subarray(a contiguous non-empty sequence of elements within an array) is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.
Return the length of the longest balanced subarray.
Example 1:
Input: nums = [2,5,4,3]
Output: 4
Explanation:
The longest balanced subarray is [2, 5, 4, 3].
It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.
Example 2:
Input: nums = [3,2,2,5,4]
Output: 5
Explanation:
The longest balanced subarray is [3, 2, 2, 5, 4].
It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5]. Thus, the answer is 5.
Example 3:
Input: nums = [1,2,3,2]
Output: 3
Explanation:
The longest balanced subarray is [2, 3, 2].
It has 1 distinct even number [2] and 1 distinct odd number [3]. Thus, the answer is 3.
Constraints:
1 <= nums.length <= 1500
1 <= nums[i] <= 105
 */