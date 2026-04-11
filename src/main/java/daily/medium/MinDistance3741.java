package daily.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinDistance3741 {
    public static void main(String[] args) {
        System.out.println(minimumDistance(new int[]{1,2,1,1,3}));
    }

    public static int minimumDistance(int[] nums) {
        int n = nums.length, minTotalDistance = Integer.MAX_VALUE;
        boolean found = false;
//        group indices by value
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for(int i = 0; i < n; i++)
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        for(List<Integer> indices : indexMap.values()) {
//            a good tuple requires at least three occurrences
            if(indices.size() < 3) continue;
            for(int i = 0; i < indices.size() - 2; i++) {
                int first = indices.get(i);
                int third = indices.get(i + 2);
//                distance formula 2 * (k - i)
                int currentDistance = 2 * (third - first);
                minTotalDistance = Math.min(minTotalDistance, currentDistance);
                found = true;
            }
        }

        return found ? minTotalDistance : -1;
    }
}

/*
You are given an integer array nums.
A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
Example 1:
Input: nums = [1,2,1,1,3]
Output: 6
Explanation:
The minimum distance is achieved by the good tuple (0, 2, 3).
(0, 2, 3) is a good tuple because nums[0] == nums[2] == nums[3] == 1. Its distance is abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6.
Example 2:
Input: nums = [1,1,2,3,2,1,2]
Output: 8
Explanation:
The minimum distance is achieved by the good tuple (2, 4, 6).
(2, 4, 6) is a good tuple because nums[2] == nums[4] == nums[6] == 2. Its distance is abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8.
Example 3:
Input: nums = [1]
Output: -1
Explanation:
There are no good tuples. Therefore, the answer is -1.
Constraints:
1 <= n == nums.length <= 105
1 <= nums[i] <= n
 */
