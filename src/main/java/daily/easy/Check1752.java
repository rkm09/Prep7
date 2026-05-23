package daily.easy;

import java.util.Arrays;

public class Check1752 {
    public static void main(String[] args) {
        // [6,10,6] (true); [10,1,1,10] (true) // [5,1,5,1] (false)
        System.out.println(check(new int[]{3,4,5,1,2}));
    }

//    inflexion count should be 1; time: O(n), space: O(1)
    public static boolean check(int[] nums) {
        int n = nums.length;
        if(n <= 1) return true;

        int inflexionCount = 0;
        for(int i = 1; i < n; i++) {
            if(nums[i] < nums[i - 1])
                inflexionCount++;
            if(inflexionCount > 1) return false;
        }

//        premise is rotated array
        if(nums[0] < nums[n - 1])
            inflexionCount++;

        return inflexionCount <= 1;
    }

//    sorting and rotation offset; time: O(n^2), space: O(n)
    public static boolean check1(int[] nums) {
        int size = nums.length;

        int[] sortedArr = nums.clone();
        Arrays.sort(sortedArr);

        for(int rotationOffset = 0; rotationOffset < size; rotationOffset++) {
            boolean isMatch = true;
            for(int index = 0; index < size; index++) {
                if(sortedArr[(rotationOffset + index) % size] != nums[index]) {
                    isMatch = false;
                    break;
                }
            }
            if(isMatch) return true;
        }

        return false;
    }

}

/*
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.
There may be duplicates in the original array.
Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.
Example 1:
Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].
Example 2:
Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.
Example 3:
Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
 */