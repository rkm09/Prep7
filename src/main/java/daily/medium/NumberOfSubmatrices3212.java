package daily.medium;

public class NumberOfSubmatrices3212 {
    public static void main(String[] args) {
        char[][] grid = {{'X','X'},{'X','Y'}};
        System.out.println(numberOfSubmatrices(grid));
    }

//    prefix sum; time: O(m.n), space: O(n)
    public static int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int resultCount = 0;
//        vertical accumulators for each column
        int[] colX = new int[n];
        int[] colY = new int[n];

        for(int i = 0 ; i < m ; i++) {
//          horizontal accumulators for current submatrix row
            int currentX = 0, currentY = 0;
            for(int j = 0 ; j < n ; j++) {
//                1. update the vertical count for this column
                if(grid[i][j] == 'X') colX[j]++;
                else if(grid[i][j] == 'Y') colY[j]++;
//                2. add the vertical column sum to our horizontal running total
                currentX += colX[j];
                currentY += colY[j];
//                3. check conditions for the submatrix ending at (i,j)
                if(currentX == currentY && currentX > 0)
                    resultCount++;
            }
        }
        return resultCount;
    }
}

/*
Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:
grid[0][0] an equal frequency of 'X' and 'Y', at least one 'X'.
Example 1:
Input: grid = [["X","Y","."],["Y",".","."]]
Output: 3
Explanation:
Example 2:
Input: grid = [["X","X"],["X","Y"]]
Output: 0
Explanation:
No submatrix has an equal frequency of 'X' and 'Y'.
Example 3:
Input: grid = [[".","."],[".","."]]
Output: 0
Explanation:
No submatrix has at least one 'X'.
Constraints:
1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'.
 */