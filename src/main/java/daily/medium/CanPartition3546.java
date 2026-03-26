package daily.medium;

public class CanPartition3546 {
    public static void main(String[] args) {
        int[][] grid = {{1,4},{2,3}};
        System.out.println(canPartitionGrid(grid));
    }

//    prefix sum; time: O(m.n), space: O(m + n)
    public static boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long totalSum = 0;
        long[] rowSums = new long[m];
        long[] colSums = new long[n];

//        Step 1: Calculate total sum
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                rowSums[i] += grid[i][j];
                colSums[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }

//        if total sum is odd, we can't split it into two equal integers
        if(totalSum % 2 != 0) return false;
        long target = totalSum / 2;

//        Step 2: Check for horizontal cut
        long currentRowsSum = 0;
        for(int i = 0 ; i < m - 1 ; i++) {
//            we stop at m - 1, since each section must be non-empty
            currentRowsSum += rowSums[i];
            if(currentRowsSum == target) return true;
        }

//        Step 3: Check for vertical cut
        long currentColsSum = 0;
        for(int j = 0 ; j < n - 1 ; j++) {
//            we stop at n - 1, since each section must be non-empty
            currentColsSum += colSums[j];
            if(currentColsSum == target) return true;
        }

        return false;
    }
}

/*
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.
Example 1:
Input: grid = [[1,4],[2,3]]
Output: true
Explanation:
A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.
Example 2:
Input: grid = [[1,3],[2,4]]
Output: false
Explanation:
No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.
Constraints:
1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105
 */