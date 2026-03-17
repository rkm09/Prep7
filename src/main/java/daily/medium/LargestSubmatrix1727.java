package daily.medium;

import java.util.Arrays;

public class LargestSubmatrix1727 {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,0},{1,0,1}};
        System.out.println(largestSubmatrix(matrix));
    }

//    calculate heights and maximize area by sorting; time: O(M.NlogN), space: O(N)
    public static int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
//        use a separate array to store heights to avoid modifying input
        int[] heights = new int[n];

        for(int i = 0 ; i < m ; i++) {
//            Step 1: Update heights for the current row
            for(int j = 0 ; j < n ; j++) {
//                note: reset is needed for else, as height is carrying previous iteration value
                if(matrix[i][j] == 1)
                    heights[j]++;
                else
                    heights[j] = 0;
            }

//            Step 2: Create a copy of the current heights to sort
            int[] sortedHeights = heights.clone();
            Arrays.sort(sortedHeights);

//            Step 3: Calculate the max area for this row configuration
            for(int k = 1 ; k <= n ; k++) {
                int height = sortedHeights[n - k];
                maxArea = Math.max(maxArea, height * k);
            }
        }

        return maxArea;
    }
}

/*
You are given a binary matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.
Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
Constraints:
m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is either 0 or 1.
 */
