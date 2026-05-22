package daily.medium;

public class Search33 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{3,5,1},3));
    }

//    binary search; time: O(logN), space: O(1)
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) >>> 1;

            if(nums[mid] == target)
                return mid;

//            equal to considered since left & mid can essentially be pointing at the same index
//            note we can do a micro optimization at line 21, also we could check for right instead first
            if(nums[left] <= nums[mid]) {
//                left side is normally sorted
                if(target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if(target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }
}

/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:
Input: nums = [1], target = 0
Output: -1
Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 10^4
 */

/*
That is a very logical optimization to think about. If target == nums[left], we could instantly return left right then and there.
However, explicitly checking for it doesn't change the outcome or speed up the algorithm in a meaningful way, and keeping it as >= allows us to write cleaner, more streamlined code.
While this saves a couple of loop iterations in the specific scenario where the target sits exactly at the left pointer, it adds an extra conditional branch calculation to every single loop iteration for all other cases. Standard binary search design leans toward keeping the inner loop body as lean as possible, letting the standard nums[mid] == target block do the heavy lifting for all equality returns.
When target is not known, and you are just on the lookout for the minimum and so the inflexion point, only right is trustable.
 */