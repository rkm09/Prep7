package daily.hard;

public class MinValue154 {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,3,1,3}));
    }

//    binary search; time: O(logn) [thought worst case where all are duplicates of just one number O(n)], space: O(1)
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) >>> 1;
            if(nums[mid] > nums[right])
                left = mid + 1;
            else if(nums[mid] < nums[right])
                right = mid;
            else
                right--;
        }

        return nums[left];
    }
}

/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
You must decrease the overall operation steps as much as possible.
Example 1:
Input: nums = [1,3,5]
Output: 1
Example 2:
Input: nums = [2,2,2,0,1]
Output: 0
Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.
Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */


/*
If nums[mid] == nums[right], we are stuck in a flat plateau. We cannot throw away the left half, and we cannot throw away the right half.
However, because nums[mid] has the exact same value as nums[right], the element at right is effectively redundant. Even if nums[right] was the minimum element, nums[mid] is still there to preserve that minimum value within our remaining search space.
By executing right--, we shrink the window by just 1 element. On the next loop iteration, mid will be recalculated, shifting away from the plateau and hopefully landing on a spot that gives us a clear > or < directional signal again.
Also note, we can never get a definitive answer if we compare with nums[left] instead. eg. [3,4,5,1,2]
*/