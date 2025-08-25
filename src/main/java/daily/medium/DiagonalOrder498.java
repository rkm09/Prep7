package daily.medium;

import java.util.Arrays;

public class DiagonalOrder498 {
    public static void main(String[] args) {
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.toString(mat));
    }

//    simulation; time: O(m*n), space: O(1)
    public static int[] findDiagonalOrder(int[][] mat) {
//        check for empty
        if(mat == null || mat.length == 0)
            return new int[0];
        int m = mat.length, n = mat[0].length;
//        indices to track progress; direction: current diagonal direction
        int row = 0, col = 0, r = 0, direction = 1;
        int[] res = new int[m*n];
//        track all elements
        while(row < m && col < n) {
//            update result
            res[r++] = mat[row][col];
//            based on direction, update the next row and col
            int newRow = row + (direction == 1 ? -1 : 1);
            int newCol = col + (direction == 1 ? 1 : -1);

//            if we are out of bounds
            if(newRow < 0 || newRow == m || newCol < 0 || newCol == n) {
//                if we are processing in upward direction
                if(direction == 1) {
                    row += (col + 1 == n ? 1 : 0);
                    col += (col + 1 < n ? 1 : 0);
                } else {
                    col += (row + 1 == m ? 1 : 0);
                    row += (row + 1 < m ? 1 : 0);
                }
//                flip direction
                direction = 1 - direction;
            } else {
                row = newRow;
                col = newCol;
            }
        }
        return res;
    }
}

/*
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
Example 1:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:
Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105
 */