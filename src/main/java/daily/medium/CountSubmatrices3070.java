package daily.medium;

public class CountSubmatrices3070 {
    public static void main(String[] args) {
        int[][] grid = {{7,6,3},{6,6,1}};
        System.out.println(countSubmatrices(grid, 18));
    }

//    prefix sum; time: O(mn), space: O(n)
    public static int countSubmatrices(int[][] grid, int k) {
        int n = grid[0].length, count = 0;
        int[] cols = new int[n];
        for (int[] elems : grid) {
            int rows = 0;
            for (int j = 0; j < n; j++) {
                cols[j] += elems[j];
                rows += cols[j];
                if (rows <= k)
                    count++;
            }
        }
        return count;
    }
}

/*
You are given a 0-indexed integer matrix grid and an integer k.
Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.
Example 1:
Input: grid = [[7,6,3],[6,6,1]], k = 18
Output: 4
Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.
Example 2:
Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
Output: 6
Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 20.
Constraints:
m == grid.length
n == grid[i].length
1 <= n, m <= 1000
0 <= grid[i][j] <= 1000
1 <= k <= 109
 */

/*
We start from the top-left corner and traverse the matrix in row-major order, treating each position (i,j) as the bottom-right corner of a submatrix. To efficiently compute submatrix sums in a single pass, we maintain an array cols[j] that stores the sum of elements in column j up to the current row.
While processing row i, we iterate over columns j from left to right. For each column, we add grid[i][j] to cols[j], and then accumulate cols[j] into a running sum for the current row. If the accumulated sum is ≤k, we increment the result by 1.
 */