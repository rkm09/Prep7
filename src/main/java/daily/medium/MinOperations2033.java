package daily.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinOperations2033 {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{2,4},{6,8}}, 2));
    }


//    sorting + median; time: O(nlogn), space: O(n)
    public static int minOperations(int[][] grid, int x) {
        List<Integer> numsList = new ArrayList<>();
        int result = 0;
//        flatten the array
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                numsList.add(grid[i][j]);
            }
        }
//        sort the numbers
        Collections.sort(numsList);
        int finalCommonNum = numsList.get(numsList.size() / 2);
//        iterate over the list
        for(int num : numsList) {
//            if the remainder when divided by x is different, return -1
            if(num % x != finalCommonNum % x)
                return -1;
//            add the number of operations needed to make the current number equal to the finalNum
            result += Math.abs(finalCommonNum - num) / x;
        }

        return result;
    }
}


/*
You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.
A uni-value grid is a grid where all the elements of it are equal.
Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.
Example 1:
Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following:
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
Example 2:
Input: grid = [[1,5],[2,3]], x = 1
Output: 5
Explanation: We can make every element equal to 3.
Example 3:
Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= x, grid[i][j] <= 10^4
 */