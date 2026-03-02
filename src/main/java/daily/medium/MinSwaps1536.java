package daily.medium;

public class MinSwaps1536 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1},{1,1,0},{1,0,0}};
        System.out.println(minSwaps(grid));
    }

//    greedy approach; time: O(n^2), space: O(n)
    public static int minSwaps(int[][] grid) {
        int n = grid.length, totalSwaps = 0;
        int[] trailingZeros = new int[n];

//        Step 1: count trailing zeros for each row
        for(int i = 0 ; i < n ; i++) {
            int count = 0;
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(grid[i][j] == 0)
                    count++;
                else
                    break;
            }
            trailingZeros[i] = count;
        }

//        Step 2: Greedy approach to move rows
        for(int i = 0 ; i < n ; i++) {
            int targetRequirement = n - 1 - i;
            int foundRowIdx = -1;
//            find the first row that satisfies the condition
            for(int j = i ; j < n ; j++) {
                if(trailingZeros[j] >= targetRequirement) {
                    foundRowIdx = j;
                    break;
                }
            }
//            if no such row exists, return -1
            if(foundRowIdx == -1) return -1;

//            move the row up to i and count the adjacent swaps
            for(int j = foundRowIdx ; j > i ; j--) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;
                totalSwaps++;
            }
        }

        return totalSwaps;
    }
}

/*
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
A grid is said to be valid if all the cells above the main diagonal are zeros.
Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
Example 1:
Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3
Example 2:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.
Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0
Constraints:
n == grid.length == grid[i].length
1 <= n <= 200
grid[i][j] is either 0 or 1
 */