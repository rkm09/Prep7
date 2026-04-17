package daily.medium;

import java.util.*;

public class SolveQueries3488 {
    public static void main(String[] args) {
        System.out.println(solveQueries(new int[]{1,2,3,4}, new int[]{0,1,2,3}));
    }

//    binary search & hashmap; time: O(n), space: O(n)
    public static List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>(queries.length);
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
//        same as below: use intentional lambda
        for(int i = 0; i < n; i++)
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>())
                    .add(i);
        for(int qIdx : queries) {
            List<Integer> indices = indexMap.get(nums[qIdx]);
            if(indices.size() == 1) {
                res.add(-1); continue;
            }
//            efficiently find the position of qIdx in the sorted list of indices
//            int pos = Collections.binarySearch(indices, qIdx);
            int pos = binarySearch(indices, qIdx);
            int m = indices.size();

//            neighbor to the left (with wrap-around)
            int leftIdx = indices.get((pos - 1 + m) % m);
            int leftDist = Math.abs(qIdx - leftIdx);
            int cirLeft = Math.min(leftDist, n - leftDist);

//            neighbor to the right (with wrap-around)
            int rightIdx = indices.get((pos + 1) % m);
            int rightDist = Math.abs(qIdx - rightIdx);
            int cirRight = Math.min(rightDist, n - rightDist);

            res.add(Math.min(cirLeft, cirRight));
        }

        return res;
    }


//    Time limit exceeded XX
    public static List<Integer> solveQueriesX(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
//        note: it is absolutely crucial here, to use lambda instead of method reference,
//        lest the key will be taken as size for array list and throw a whooping MLE.
        for(int i = 0; i < n; i++)
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>())
                    .add(i);
        for(int query : queries) {
            List<Integer> dist = indexMap.get(nums[query]);
            if(dist.size() == 1) {
                res.add(-1); continue;
            }
            int minDist = Integer.MAX_VALUE;
            for(int currIdx: dist) {
                if(query == currIdx) continue;
                int currDist = Math.abs(query - currIdx);
                minDist = Math.min(minDist, Math.min(currDist, n - currDist));
            }
            res.add(minDist);
        }

        return res;
    }

    private static int binarySearch(List<Integer> li, int idx) {
        int left = 0, right = li.size() - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(li.get(mid) == idx)
                return mid;
            if(li.get(mid) < idx)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}

/*
You are given a circular array nums and an array queries.
For each query i, you have to find the following:
The minimum distance between the element at index queries[i] and any other index j in the circular array, where nums[j] == nums[queries[i]]. If no such index exists, the answer for that query should be -1.
Return an array answer of the same size as queries, where answer[i] represents the result for query i.
Example 1:
Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
Output: [2,-1,3]
Explanation:
Query 0: The element at queries[0] = 0 is nums[0] = 1. The nearest index with the same value is 2, and the distance between them is 2.
Query 1: The element at queries[1] = 3 is nums[3] = 4. No other index contains 4, so the result is -1.
Query 2: The element at queries[2] = 5 is nums[5] = 3. The nearest index with the same value is 1, and the distance between them is 3 (following the circular path: 5 -> 6 -> 0 -> 1).
Example 2:
Input: nums = [1,2,3,4], queries = [0,1,2,3]
Output: [-1,-1,-1,-1]
Explanation:
Each value in nums is unique, so no index shares the same value as the queried element. This results in -1 for all queries.
Constraints:
1 <= queries.length <= nums.length <= 105
1 <= nums[i] <= 106
0 <= queries[i] < nums.length
 */