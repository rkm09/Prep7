package daily.hard;

public class JumpGame1340 {
    public static void main(String[] args) {
        JumpGame1340 j = new JumpGame1340();
        int[] arr = {6,4,14,6,8,13,9,7,10,6,12};
        System.out.println(j.maxJumps(arr, 2));
    }

//  top down dp with memoization; time: O(N * D), space: O(N)
    public int maxJumps(int[] arr, int d) {
        int n = arr.length, maxVisited = 0;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            maxVisited = Math.max(maxVisited, dfs(arr, d, i, dp));

        return maxVisited;
    }

    private int dfs(int[] arr, int d, int i, int[] dp) {
//        If already calculated return value;
        if(dp[i] != 0) return dp[i];
        int n = arr.length;
//        Base case: visiting just index i itself
        int currentMax = 1;
//        1. Check jumps to the right
        for(int x = 1; x <= d && i + x < n; x++) {
            int j = i + x;
//            Stop at barrier
            if(arr[i] <= arr[j])
                break;
            currentMax = Math.max(currentMax, 1 + dfs(arr, d, j, dp));
        }
//        2. Check jumps to the left
        for(int x = 1; x <= d && i - x >= 0; x++) {
            int j = i - x;
//            Stop at barrier
            if(arr[i] <= arr[j])
                break;
            currentMax = Math.max(currentMax, 1+ dfs(arr, d, j, dp));
        }

//        Cache the result before returning
        dp[i] = currentMax;
        return dp[i];
    }
}

/*
Given an array of integers arr and an integer d. In one step you can jump from index i to index:
i + x where: i + x < arr.length and  0 < x <= d.
i - x where: i - x >= 0 and  0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
Notice that you can not jump outside the array at any time.
Example 1:
Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly, you cannot jump from index 3 to index 2 or index 1.
Example 2:
Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You cannot jump to any index.
Example 3:
Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indices.
Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
 */

/*
The key insight is to realize that the choices you can make from any index i depend only on the values in the array and the jump limit d.
Since you can only jump to a strictly smaller value (arr[i] > arr[j]), there are no cycles.
This means the problem can be modeled as a Directed Acyclic Graph (DAG), making it perfect for memoization.

Summary Table:      Tabulation vs. Memoization
Feature             Top-Down (Memoization)                           Bottom-Up (Tabulation)
Core Concept    Recursive + Remembering results                 Iterative + Building up results
State Order     Solved as needed ("On-Demand")                  Solved sequentially (0, 1, 2...)
Storage         A cache/memo table checked during recursion     A table filled out step-by-step
Overhead        Uses recursion stack space                      No recursion overhead, just loops

Memoization specifically refers to caching results inside a
recursive process so you don't repeat a branch of a tree you've already walked down.
 */