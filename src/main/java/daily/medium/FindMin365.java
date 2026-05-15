package daily.medium;

public class FindMin365 {
    public static void main(String[] args) {
        FindMin365 f = new FindMin365();
        System.out.println(f.findMin(new int[]{3,4,5,1,2}));
    }

//    binary search; time: O(logN), space: O(1)
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2; // int mid = (left + right) >>> 1;
            if(nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }

        return nums[left];
    }
}

/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.
Example 1:
Input: nums = [3,4,5,1,2
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
 */

/*
Framework TypeLoop ConditionLeft UpdateRight UpdateTerminating StateBest Used For
Type 1: The Exact Match
while (left <= right) left = mid + 1 right = mid - 1 left > right (No element left) Searching for a specific target value (e.g., standard Binary Search). Returns -1 if not found.
Type 2: The Boundary Hunter
while (left < right)left = mid + 1right = midleft == right (1 element left)Finding a turning point, minimum, or first/last occurrence. (What we used here)
Type 3: The Neighborhood Watch
while (left + 1 < right)left = midright = midleft + 1 == right (2 elements left)Great when you need to compare an element to both its left and right neighbors without going out of bounds. You evaluate left and right manually after the loop.Summary Checklist for Type 2 (Our Code)Next time you write a boundary-seeking binary search, just ask yourself these two questions:Can mid be the answer? If yes, your pointer must include it (right = mid or left = mid). If no, exclude it (mid + 1 or mid - 1).If 2 elements are left, will it shrink? If mid rounds down to left, then a right = mid update will collapse right onto left. Loop terminates. No infinite loop possible.
 */