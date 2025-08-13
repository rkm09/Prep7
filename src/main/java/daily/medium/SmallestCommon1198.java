package daily.medium;

public class SmallestCommon1198 {
    public static void main(String[] args) {
        int[][] mat = {{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
        System.out.println(smallestCommonElement(mat));
    }

//    def; time: O(n^3)
    public static int smallestCommonElement(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for(int i = 0 ; i < n ; i++) {
            int count = 1;
            int tar = mat[0][i];
            for(int j = 1 ; j < m ; j++) {
                if(mat[j][0] > tar) break;
                for(int k = 0 ; k < n ; k++) {
                    if(mat[j][k] > tar) break;
                    if(mat[j][k] == tar) {
                        count++; break;
                    }
                }
            }
            if(count == m)
                return tar;
        }
        return -1;
    }
}

/*
Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
If there is no common element, return -1.
Example 1:
Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
Output: 5
Example 2:
Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
Output: 2

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 104
mat[i] is sorted in strictly increasing order.
 */