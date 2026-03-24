package daily.medium;

public class MaxProductPath1594 {
    private static final long MOD = (long) 1e9 + 7;
    public static void main(String[] args) {
        int[][] grid = {{1,-2,1},{1,-2,1},{3,-4,1}};
        System.out.println(maxProductPath(grid));
    }

//    dp; time: O(m.n), space: O(m.n)
    public static int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
//        use long to prevent overflow before applying modulo
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];

//        initialize starting point
        maxDp[0][0] = minDp[0][0] = grid[0][0];

//        initialize the first column (only one way move: down)
        for(int i = 1 ; i < m ; i++)
            minDp[i][0] = maxDp[i][0] = maxDp[i - 1][0] * grid[i][0];

//        initialize the first row (only one way move: right)
        for(int j = 1 ; j < n ; j++)
            minDp[0][j] = maxDp[0][j] = maxDp[0][j - 1] * grid[0][j];

//        fill the dp table
        for(int i = 1 ; i < m ; i++) {
            for(int j = 1 ; j < n ; j++) {
                long val = grid[i][j];
//                if the current value is positive, multiply it by the max/min from above and left
                if(val >= 0) {
                    maxDp[i][j] = Math.max(maxDp[i - 1][j], maxDp[i][j - 1]) * val;
                    minDp[i][j] = Math.min(minDp[i - 1][j], minDp[i][j - 1]) * val;
                }
//                if negative, the smallest previous value becomes the largest
                else {
                    maxDp[i][j] = Math.min(minDp[i - 1][j], minDp[i][j - 1]) * val;
                    minDp[i][j] = Math.max(maxDp[i - 1][j], maxDp[i][j - 1]) * val;
                }
            }
        }

        long result = maxDp[m - 1][n - 1];
        return result < 0 ? -1 : (int) (result % MOD);
    }
}

/*
You are given an m x n matrix grid. Initially, you are in the top left corner (0, 0), and in each step, you can only move right or down in the matrix.
Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
Notice that the modulo is performed after getting the maximum product.
Example 1:
Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
Output: -1
Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
Example 2:
Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
Example 3:
Input: grid = [[1,3],[0,-4]]
Output: 0
Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 15
-4 <= grid[i][j] <= 4
*/

/*
This is a classic "don't trust the sign" problem. Since multiplying two negative numbers results in a positive one, a path that currently has a very small
(highly negative) product could suddenly become the maximum product if it hits another negative number.
If grid[i][j] is positive, it keeps the max as max and min as min.
If grid[i][j] is negative, it flips them: the previous min becomes the new max, and the previous max becomes the new min.
If grid[i][j] is zero, both become zero.
 */