package daily.medium;

import java.util.*;

public class Distance2615 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(distance(new int[]{1,3,1,1,2})));
    }


//    prefix sum; time: O(n), space: O(n)
    public static long[] distance(int[] nums) {
        int n = nums.length;
//        prevent resizing/rehashing during initial grouping phase
        Map<Integer, List<Integer>> indexMap = new HashMap<>((int) (n / 0.75) + 1);
        for(int i = 0; i < n; i++)
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>())
                    .add(i);

        long[] result = new long[n];
        indexMap.forEach((num, indices) -> {
            int m = indices.size();
            if(m <= 1) return;   // lambda's equivalent for continue;

            long totalSum = indices.stream().mapToLong(Integer::longValue).sum();
            long prefixSum = 0;

            for(int i = 0; i < m; i++) {
                int currentIdx = indices.get(i);
                long countLeft = i;
                long countRight = m - 1 - i;
                long suffixSum = totalSum - prefixSum - currentIdx;

                result[currentIdx] = (countLeft * currentIdx - prefixSum) +
                        (suffixSum - countRight * currentIdx);

                prefixSum += currentIdx;
            }

        });

        return result;
    }

//    TLE; 10^10, timeout at 10^8
    public static long[] distanceX(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for(int i = 0; i < n; i++)
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>())
                    .add(i);

        long[] result = new long[n];
        for(int i = 0; i < n; i++) {
            List<Integer> indices = indexMap.get(nums[i]);
            long sum = 0;
            for(int index : indices)
                sum += Math.abs(index - i);
            result[i] = sum;
        }

        return result;
    }
}

/*
You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
Return the array arr.
Example 1:
Input: nums = [1,3,1,1,2]
Output: [5,0,3,4,0]
Explanation:
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
When i = 4, arr[4] = 0 because there is no other index with value 2.
Example 2:
Input: nums = [0,5,3]
Output: [0,0,0]
Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
 */


/*
1. The "Total Work" Principle ($O(N)$)Even though there is a loop inside the forEach,
we are iterating over disjoint sets of indices.Every index from the original array nums exists in exactly one list
in the indexMap.Therefore, the inner for loop executes exactly $N$ times across the entire execution of the method.
Total Complexity: $O(N)$ for grouping + $O(N)$ for calculating = $O(N)$.
 */