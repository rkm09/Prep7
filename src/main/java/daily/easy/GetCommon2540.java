package daily.easy;

import java.util.HashSet;
import java.util.Set;

public class GetCommon2540 {
    public static void main(String[] args) {
        System.out.println(getCommon(new int[]{1,2,3}, new int[]{2,4}));
    }

//    two pointer; time: O(m + n), space: O(1) [fastest]
    public static int getCommon(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int first = 0, second = 0;
        while(first < n1 && second < n2) {
            if(nums1[first] < nums2[second])
                first++;
            else if(nums1[first] > nums2[second])
                second++;
            else
                return nums1[first];
        }

        return -1;
    }

//    binary search; time: O(NlogM), space: O(1)
    public static int getCommon1(int[] nums1, int[] nums2) {
        for(int num : nums1) {
            if(binarySearch(nums2, num))
                return num;
        }

        return -1;
    }

    private static boolean binarySearch(int[] nums2, int val) {
        int left = 0, right = nums2.length - 1;
        while(left <= right) {
            int mid = (left + right) >>> 1;
            if(nums2[mid] > val)
                right = mid - 1;
            else if(nums2[mid] < val)
                left = mid + 1;
            else
                return true;
        }

        return false;
    }

    //    hashset; time: O(n + m), space: O(n) [slowest] though hashset contains should take O(1), still turns out to be slow
    public static int getCommon2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int num : nums1)
            set1.add(num);
        for(int num : nums2) {
            if(set1.contains(num))
                return num;
        }

        return -1;
    }

}

/*
Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.
Example 2:
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
Constraints:
1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109
Both nums1 and nums2 are sorted in non-decreasing order.
 */