package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class MinSubArray1590 {
    public static void main(String[] args) {
        int[] nums = {3,1,4,2};
        System.out.println(minSubarray(nums, 6));
    }

//    prefix sum modulo; time: O(n), space: O(n)
    public static int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int totalSum = 0;
        for(int num : nums)
            totalSum = (totalSum + num) % p;
        int target = totalSum % p;
        if(target == 0) return 0;
        int minLength = n, currentSum = 0;
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, -1);
        for(int i = 0 ; i < n ; i++) {
            currentSum = (currentSum + nums[i]) % p;
            int needed = (currentSum - target + p) % p;
            if(modMap.containsKey(needed))
                minLength = Math.min(minLength, i - modMap.get(needed));
            modMap.put(currentSum, i);
        }
        return minLength == n ? -1 : minLength;
    }

//    brute force; TLE; time: O(n^2)
    public static int minSubarrayX(int[] nums, int p) {
        int n = nums.length;
        long totalSum = 0;
        for(int num : nums)
            totalSum += num;
        if(totalSum % p == 0) return 0;
        int minLength = n;
        for(int start = 0 ; start < n ; start++) {
            long subSum = 0;
            for(int end = start ; end < n ; end++) {
                subSum += nums[end];
                long remainingSum = (totalSum - subSum) % p;
                if(remainingSum == 0)
                    minLength = Math.min(minLength, end - start + 1);
            }
        }
        return minLength == n ? -1 : minLength;
    }
}

/*
Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
A subarray is defined as a contiguous block of elements in the array.
Example 1:
Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
Example 2:
Input: nums = [6,3,5,2], p = 9
Output: 2
Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
Example 3:
Input: nums = [1,2,3], p = 3
Output: 0
Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= p <= 109
 */
