package daily.easy;

import java.util.Arrays;

public class LeftRightDiff2574 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(leftRightDifference(new int[]{10,4,8,3})));
    }

//    prefix sum; time: O(n), space: O(n)
    public static int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        int[] res = new int[n];
        res[0] = prefixSum[n - 1] - prefixSum[0];
        for (int i = 1; i < n; i++) {
            res[i] = Math.abs(prefixSum[i - 1] - (prefixSum[n - 1] - prefixSum[i])) ;
        }

        return res;
    }

//    prefix sum; time: O(n), space: O(1)
    public static int[] leftRightDifference1(int[] nums) {
        int n = nums.length, leftSum = 0, rightSum = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftSum;
            leftSum += nums[i];
        }
        for (int j = n - 1; j >= 0; j--) {
            res[j] = Math.abs(res[j] - rightSum);
            rightSum += nums[j];
        }

        return res;
    }
}

/*
You are given a 0-indexed integer array nums of size n.
Define two arrays leftSum and rightSum where:
leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
Return an integer array answer of size n where answer[i] = |leftSum[i] - rightSum[i]|.
Example 1:
Input: nums = [10,4,8,3]
Output: [15,1,11,22]
Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
Example 2:
Input: nums = [1]
Output: [0]
Explanation: The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 105
 */