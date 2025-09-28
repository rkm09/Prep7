package daily.easy;

import java.util.Arrays;

public class LargestPerimeter976 {
    public static void main(String[] args) {
        int[] nums = {2,1,2};
        System.out.println(largestPerimeter(nums));
    }

//    sort; time: O(nlogn), space: O(1)
    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = n - 3 ; i >= 0 ; i--) {
            if(nums[i] + nums[i + 1] > nums[i + 2])
                return nums[i] + nums[i + 1] + nums[i + 2];
        }
        return 0;
    }
}

/*
Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.
Example 1:
Input: nums = [2,1,2]
Output: 5
Explanation: You can form a triangle with three side lengths: 1, 2, and 2.
Example 2:
Input: nums = [1,2,1,10]
Output: 0
Explanation:
You cannot use the side lengths 1, 1, and 2 to form a triangle.
You cannot use the side lengths 1, 1, and 10 to form a triangle.
You cannot use the side lengths 1, 2, and 10 to form a triangle.
As we cannot use any three side lengths to form a triangle of non-zero area, we return 0.
Constraints:
3 <= nums.length <= 104
1 <= nums[i] <= 106
 */


/*
Without loss of generality, say the side lengths of the triangle are a≤b≤c.
The necessary and sufficient condition for these lengths to form a triangle of non-zero area is a+b>c.
Sort the array. For any c in the array, we choose the largest possible a≤b≤c: these are just the two values adjacent to c.
If this forms a triangle, we return the answer.
 */