package daily.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinDistance3740 {
    public static void main(String[] args) {
        System.out.println(minimumDistance(new int[]{1,2,1,1,3}));
    }

//    using hashmap and distance formula; time: O(n), space: O(n)
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

//    brute force with distance formula; time: O(n^3), space: O(1)
    public static int minimumDistance1(int[] nums) {
        int n = nums.length, ans = n + 1;
        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                if(nums[i] != nums[j])
                    continue;
                for(int k = j + 1; k < n; k++) {
                    if(nums[j] == nums[k]) {
                        ans = Math.min(ans, k - i);
                        break;
                    }
                }
            }
        }

        return ans == n + 1 ? -1 : ans * 2;
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
1 <= n == nums.length <= 100
1 <= nums[i] <= n
 */

/*
First, consider the formula for the sum of absolute differences.
It can be observed that this value is equivalent to the sum of the three sides of a generalized triangle.
Regardless of the order of the three selected indices, the total distance always simplifies to twice the length of the segment formed by the two endpoints. In other words, if the leftmost index is i and the rightmost index is k, then the required distance is 2×(k−i).
considering: i < j < k
 - abs(i - j) = j - i;
 - abs(j - k) = k - j;
 - abs(k - i) = k - i;
 total distance = (j - i) + (k - j) + (k - i) = (j - j) + (k  + k) - (i + i) = 2 * (k - i);

 Time Complexity for Hash approach is O(n): [Linear scan of a partitioned input]
 If each partition were actually size N for every iteration of an outer loop that also runs N times, you would indeed have O(N^2).
 However, in this specific problem, it is mathematically impossible for that to happen. The total number of elements stored across
 all partitions is constrained by the size of the original array.The Mathematical Constraint If the outer loop runs V times (where
 V is the number of unique values), and each sublist has a size s_i, the total number of operations is sum{i=1} to{V} s_i.Because
 every index from the original array 0 to N-1 is placed into exactly one sublist:sum_{i=1} to {V} s_i = N.
 */