package daily.easy;

public class FindRotation1886 {
    public static void main(String[] args) {
        int[][] mat = {{0,1},{1,0}};
        int[][] target = {{1,0},{0,1}};
        System.out.println(findRotation(mat, target));
    }

//    four-way-check strategy(without in-place or separate mutation); time: O(n^2), space: O(1)
    public static boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean[] rot = {true, true, true, true}; // 0, 90, 180, 270
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
//                check 0 degree rotation
                if(mat[i][j] != target[i][j]) rot[0] = false;
//                check 90 degree rotation
                if(mat[i][j] != target[j][n - 1 - i]) rot[1] = false;
//                check 180 degree rotation
                if(mat[i][j] != target[n - 1 - i][n - 1 - j]) rot[2] = false;
//                check 270 degree rotation
                if(mat[i][j] != target[n - 1 - j][i]) rot[3] = false;
            }
        }
//        if any of the rotations are still true, it is possible
        return rot[0] || rot[1] || rot[2] || rot[3];
    }
}

/*
Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
Example 1:
Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
Example 2:
Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.
Example 3:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
Constraints:
n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1.
 */

/*
The Math Behind the Mapping:
For a matrix of size n * n and a cell at (i, j):
90:: The row index i becomes the distance from the right edge (n-1-i), and the column index j becomes the new row index.
Formula: (i, j) to (j, n-1-i)
180:: Both indices are simply flipped relative to the center.
Formula: (i, j) to (n-1-i, n-1-j)
270:: This is just a 90 rotation in the opposite direction.
Formula: (i, j) to (n-1-j, i)
 */