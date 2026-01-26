package daily.easy;

public class KLengthApart1437 {
    public static void main(String[] args) {
        int[] nums = {1,0,0,0,1,0,0,1};
        System.out.println(kLengthApart(nums, 2));
    }

    public static boolean kLengthApart(int[] nums, int k) {
        int lastIndex = -1, n = nums.length;
        for(int i = 0 ; i < n ; i++) {
            if(nums[i] == 1) {
                if(lastIndex != - 1 && i - lastIndex - 1 < k)
                    return false;
                lastIndex = i;
            }
        }
        return true;
    }
}

/*
Given a binary array nums and an integer k, return true if all 1's are at least k places away from each other, otherwise return false.
Example 1:
Input: nums = [1,0,0,0,1,0,0,1], k = 2
Output: true
Explanation: Each of the 1s are at least 2 places away from each other.
Example 2:
Input: nums = [1,0,0,1,0,1], k = 2
Output: false
Explanation: The second 1 and third 1 are only one apart from each other.
Constraints:
1 <= nums.length <= 105
0 <= k <= nums.length
nums[i] is 0 or 1
 */