package daily.easy;

import java.util.Arrays;

public class MinDiff1984 {
    public static void main(String[] args) {
        int[] nums = {9,4,1,7};
        System.out.println(minimumDifference(nums, 2));
    }

//    sorting; time: O(nlogn), space: O(logn)
    public static int minimumDifference(int[] nums, int k) {
        int n = nums.length, res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i = 0 ; i + k - 1 < n ; i++) {
            res = Math.min(res, nums[i + k - 1] - nums[i]);
        }
        return res;
    }
}

/*
You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also given an integer k.
Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k scores is minimized.
Return the minimum possible difference.
Example 1:
Input: nums = [90], k = 1
Output: 0
Explanation: There is one way to pick score(s) of one student:
- [90]. The difference between the highest and lowest score is 90 - 90 = 0.
The minimum possible difference is 0.
Example 2:
Input: nums = [9,4,1,7], k = 2
Output: 2
Explanation: There are six ways to pick score(s) of two students:
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
- [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
The minimum possible difference is 2.
Constraints:
1 <= k <= nums.length <= 1000
0 <= nums[i] <= 105
 */

/*
To minimize the difference between the highest and lowest scores among the selected k students, we must select them continuously from the sorted array. This is because if we skip a certain index i during the selection, replacing the current highest score in the selection with nums[i] will not increase the highest score. Consequently, the difference between the highest and lowest scores will also not increase. Therefore, there must exist an optimal selection scheme in which k consecutive elements are chosen from the sorted array.
Based on this observation, we first sort the array nums in ascending order. Then, we traverse nums using a sliding window of fixed size k.
 */