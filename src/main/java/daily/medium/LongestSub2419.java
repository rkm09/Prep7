package daily.medium;

public class LongestSub2419 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,2,2};
        System.out.println(longestSubarray(nums));
    }

//    longest consecutive sequence of the maximum value; time: O(n), space: O(1)
    public static int longestSubarray(int[] nums) {
        int maxVal = 0, res = 0, currentStreak = 0;
        for(int num : nums) {
            if(maxVal < num) {
                maxVal = num;
                res = currentStreak = 0;
            }
            if(maxVal == num) {
                currentStreak++;
            } else {
                currentStreak = 0;
            }
            res = Math.max(res, currentStreak);
        }
        return res;
    }
}

/*
You are given an integer array nums of size n.
Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
Return the length of the longest such subarray.
The bitwise AND of an array is the bitwise AND of all the numbers in it.
A subarray is a contiguous sequence of elements within an array.
Example 1:
Input: nums = [1,2,3,3,2,2]
Output: 2
Explanation:
The maximum possible bitwise AND of a subarray is 3.
The longest subarray with that value is [3,3], so we return 2.
Example 2:
Input: nums = [1,2,3,4]
Output: 1
Explanation:
The maximum possible bitwise AND of a subarray is 4.
The longest subarray with that value is [4], so we return 1.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
 */

/*
The maximum possible bitwise AND of a subarray would be the maximum number in the array itself.
This is because the bitwise AND operation with a larger number and a smaller number would always result in a number less than or equal to the smaller number.
Therefore, the maximum possible bitwise AND of a subarray can only be achieved when all the numbers in the subarray are equal to the maximum number in the array.
 */