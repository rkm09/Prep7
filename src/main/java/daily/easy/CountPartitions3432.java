package daily.easy;

public class CountPartitions3432 {
    public static void main(String[] args) {
        int[] nums = {10,10,3,7,6};
        System.out.println(countPartitions(nums));
    }

//    maths; time: O(n); time: O(1)
    public static int countPartitions(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum  += num;
        return sum % 2 == 0 ? nums.length - 1: 0;
    }

//    def;
    public static int countPartitions1(int[] nums) {
        int count = 0, sum = 0, sub1 = 0, sub2, n = nums.length;
        for(int num : nums)
            sum += num;
        for(int i = 0 ; i < n - 1 ; i++) {
            sub1 += nums[i];
            sub2 = sum - sub1;
            if((sub1 - sub2) % 2 == 0) count++;
        }
        return count;
    }
}

/*
You are given an integer array nums of length n.
A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty subarrays such that:
Left subarray contains indices [0, i].
Right subarray contains indices [i + 1, n - 1].
Return the number of partitions where the difference between the sum of the left and right subarrays is even.
Example 1:
Input: nums = [10,10,3,7,6]
Output: 4
Explanation:
The 4 partitions are:
[10], [10, 3, 7, 6] with a sum difference of 10 - 26 = -16, which is even.
[10, 10], [3, 7, 6] with a sum difference of 20 - 16 = 4, which is even.
[10, 10, 3], [7, 6] with a sum difference of 23 - 13 = 10, which is even.
[10, 10, 3, 7], [6] with a sum difference of 30 - 6 = 24, which is even.
Example 2:
Input: nums = [1,2,2]
Output: 0
Explanation:
No partition results in an even sum difference.
Example 3:
Input: nums = [2,4,6,8]
Output: 3
Explanation:
All partitions result in an even sum difference.
Constraints:
2 <= n == nums.length <= 100
1 <= nums[i] <= 100
 */

/*
Diff = L - R
     = L - (S - L)
2K = 2L - S
S = 2 * (L - K)
=> If sum is even every partition is even, else if sum is odd every partition is odd
 */