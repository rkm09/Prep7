package daily.medium;

import java.util.Arrays;

public class MinAbsoluteDiff3527 {
    public static void main(String[] args) {
        int[][] grid = {{1,8},{3,-2}};
        int[][] res = minAbsDiff(grid, 2);
    }

//    sorting; time: O(M.N.K^2.logK), space: O(k^2)
//    enumerate: m.n, within that: each submatrix contains k^2 elements, then we sort them k^2 logK
    public static int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m - k + 1][ n - k + 1];
        int[] window = new int[k * k];

        for(int i = 0 ; i < m - k + 1; i++) {
            for(int j = 0 ; j < n - k + 1; j++) {
//                1. fill the window array
                int idx = 0;
                for(int r = i ; r < i + k ; r++) {
                    for(int c = j ; c < j + k ; c++) {
                        window[idx++] = grid[r][c];
                    }
                }
//                2. sort to find the minimum difference
                Arrays.sort(window);
                int minDiff = Integer.MAX_VALUE;
                for(int t = 1 ; t < window.length ; t++) {
//                    only consider distinct values
                    if(window[t] != window[t - 1])
                        minDiff = Math.min(minDiff, window[t] - window[t - 1]);
                }
//                3. If no two values are distinct, it stays zero
                res[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }

        return res;
    }
}

/*
You are given an m x n integer matrix grid and an integer k.
For every contiguous k x k submatrix of grid, compute the minimum absolute difference between any two distinct values within that submatrix.
Return a 2D array ans of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute difference in the submatrix whose top-left corner is (i, j) in grid.
Note: If all elements in the submatrix have the same value, the answer will be 0.
A submatrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells matrix[x][y] where x1 <= x <= x2 and y1 <= y <= y2.
Example 1:
Input: grid = [[1,8],[3,-2]], k = 2
Output: [[2]]
Explanation:
There is only one possible k x k submatrix: [[1, 8], [3, -2]].
Distinct values in the submatrix are [1, 8, 3, -2].
The minimum absolute difference in the submatrix is |1 - 3| = 2. Thus, the answer is [[2]].
Example 2:
Input: grid = [[3,-1]], k = 1
Output: [[0,0]]
Explanation:
Both k x k submatrix has only one distinct element.
Thus, the answer is [[0, 0]].
Example 3:
Input: grid = [[1,-2,3],[2,3,5]], k = 2
Output: [[1,2]]
Explanation:
There are two possible k × k submatrix:
Starting at (0, 0): [[1, -2], [2, 3]].
Distinct values in the submatrix are [1, -2, 2, 3].
The minimum absolute difference in the submatrix is |1 - 2| = 1.
Starting at (0, 1): [[-2, 3], [3, 5]].
Distinct values in the submatrix are [-2, 3, 5].
The minimum absolute difference in the submatrix is |3 - 5| = 2.
Thus, the answer is [[1, 2]].
Constraints:
1 <= m == grid.length <= 30
1 <= n == grid[i].length <= 30
-105 <= grid[i][j] <= 105
1 <= k <= min(m, n)
*/