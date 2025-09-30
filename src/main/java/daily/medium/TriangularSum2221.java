package daily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriangularSum2221 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(triangularSum(nums));
    }

//    simulation; time: O(n^2), space: O(n)
    public static int triangularSum(int[] nums) {
        List<Integer> currentList = Arrays.stream(nums)
                .boxed()
                .toList();
        while(currentList.size() > 1) {
            List<Integer> newList = new ArrayList<>();
            for(int i = 0 ; i < currentList.size() - 1 ; i++) {
                newList.add((currentList.get(i) + currentList.get(i + 1)) % 10);
            }
            currentList = newList;
        }
        return currentList.getFirst();
    }
}

/*
You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
The triangular sum of nums is the value of the only element present in nums after the following process terminates:
Let nums consist of n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
Replace the array nums with newNums.
Repeat the entire process starting from step 1.
Return the triangular sum of nums.
Example 1:
Input: nums = [1,2,3,4,5]
Output: 8
Explanation:
The above diagram depicts the process from which we obtain the triangular sum of the array.
Example 2:
Input: nums = [5]
Output: 5
Explanation:
Since there is only one element in nums, the triangular sum is the value of that element itself.
Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 9
 */