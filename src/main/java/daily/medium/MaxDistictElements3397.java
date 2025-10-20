package daily.medium;

import java.util.Arrays;

public class MaxDistictElements3397 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,9,16,22};
        System.out.println(maxDistinctElements(nums, 2));
    }

//    greedy; time: O(nlogn), space: O(logn)
    public static int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;

//        initialize lastMod to a value smaller than the smallest possible modified value
        long lastMod = (long)nums[0] - k - 1;

//        greedy assignment
        for(int num : nums) {
//            range
            long minVal = (long) num - k;
            long maxVal = (long) num + k;

//        the smallest possible distinct value(newModValue) must satisfy two conditions:
//        1. be strictly greater than the last assigned value: lastMod + 1
//        2. be within the element's range: minVal (otherwise for a sample case like [2, 10, 20], k = 2 it will not count)
//        we choose the maximum of these two floors
            long newModValue = Math.max(lastMod + 1, minVal);

//        check if the chosen newModValue is within the element's upper bound
            if (newModValue <= maxVal) {
//                we have successfully assigned a new distinct element
                lastMod = newModValue;
                count++;
            }
        }

        return count;
    }
}

/*
You are given an integer array nums and an integer k.
You are allowed to perform the following operation on each element of the array at most once:
Add an integer in the range [-k, k] to the element.
Return the maximum possible number of distinct elements in nums after performing the operations.
Example 1:
Input: nums = [1,2,2,3,3,4], k = 2
Output: 6
Explanation:
nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.
Example 2:
Input: nums = [4,4,4,4], k = 1
Output: 3
Explanation:
By adding -1 to nums[0] and 1 to nums[1], nums changes to [3, 5, 4, 4].
Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
0 <= k <= 109
 */

/*
Sorting:
Processing elements in a sorted order, will ensure the range if the elements tends to be further right on the number line,
making it easier for them to select a new, larger distinct value.
Greedy:
This greedy strategy of picking the smallest sufficient new value ensures the largest possible gap between the current modified value and the current element's upper range limit ($\text{num} + k$), maximizing the chance for subsequent elements to find a unique, larger modified value.
 */