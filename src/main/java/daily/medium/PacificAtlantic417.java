package daily.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PacificAtlantic417 {
    private static final int[][] DIRECTIONS = {{0,1},{1,0},{0,-1},{-1,0}};
    private int numRows, numCols;
    public static void main(String[] args) {
        PacificAtlantic417 p = new PacificAtlantic417();
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{3,2,3,4,4},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(p.pacificAtlantic(heights));
    }

//    bfs; time: O(M.N), space: O(M.N)
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
       numRows = heights.length;
       numCols = heights[0].length;
       Deque<int[]> pacificQueue = new ArrayDeque<>();
       Deque<int[]> atlanticQueue = new ArrayDeque<>();

//       set up each queue with cells adjacent to their respective ocean
       for(int i = 0 ; i < numRows ; i++) {
           pacificQueue.offer(new int[] {i, 0});
           atlanticQueue.offer(new int[] {i, numCols - 1});
       }
       for(int i = 0 ; i < numCols ; i++) {
           pacificQueue.offer(new int[] {0, i});
           atlanticQueue.offer(new int[] {numRows - 1, i});
       }

//       perform a bfs for each ocean, to find all the cells accessible by that ocean
       boolean[][] pacificReachable = bfs(pacificQueue, heights);
       boolean[][] atlanticReachable = bfs(atlanticQueue, heights);

//       find all cells that can reach both oceans
        List<List<Integer>> commonCells = new ArrayList<>();
        for(int i = 0 ; i < numRows ; i++) {
            for(int j = 0 ; j < numCols ; j++) {
                if(pacificReachable[i][j] && atlanticReachable[i][j])
                    commonCells.add(List.of(i, j));
            }
        }
        return commonCells;
    }

    private boolean[][] bfs(Deque<int[]> queue, int[][] heights) {
        boolean[][] reachable = new boolean[numRows][numCols];
        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
//            this cell is reachable, so mark it
            reachable[cell[0]][cell[1]] = true;
//            check all four directions
            for(int[] dir : DIRECTIONS) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
//                check if the new cell is within bounds
                if(newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols)
                    continue;
//                check that the new cell hasn't already been visited
                if(reachable[newRow][newCol])
                    continue;
//                check that the new cell has higher or equal height, so that water can flow to the new cell from the old one
                if(heights[newRow][newCol] < heights[cell[0]][cell[1]])
                    continue;
//                if we have gotten this far, that means the new cell is reachable
                queue.offer(new int[] {newRow, newCol});
            }
        }
        return reachable;
    }

//    DFS; time: O(M.N), space: O(M.N)
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        numRows = heights.length;
        numCols = heights[0].length;
        boolean[][] pacificReachable = new boolean[numRows][numCols];
        boolean[][] atlanticReachable = new boolean[numRows][numCols];
//        set up each queue with cells adjacent to their respective ocean
        for(int i = 0 ; i < numRows ; i++) {
            dfs(i,0, pacificReachable, heights);
            dfs(i, numCols - 1, atlanticReachable, heights);
        }
        for(int i = 0 ; i < numCols ; i++) {
            dfs(0, i, pacificReachable, heights);
            dfs(numRows - 1, i, atlanticReachable, heights);
        }

//        find all cells that can reach both oceans
        List<List<Integer>> commonCells = new ArrayList<>();
        for(int i = 0 ; i < numRows ; i++) {
            for(int j = 0 ; j < numCols ; j++) {
                if(pacificReachable[i][j] && atlanticReachable[i][j])
                    commonCells.add(List.of(i, j));
            }
        }
        return commonCells;
    }

    private void dfs(int row, int col, boolean[][] reachable, int[][] heights) {
//        the cell is reachable, so mark it
        reachable[row][col] = true;
//        check all four directions
        for(int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
//            check if the new cell is within bounds
            if(newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols)
                continue;
//            check that the new cell hasn't already been visited
            if(reachable[newRow][newCol])
                continue;
//            check that the new cell has higher or equal height, so that water can flow to the new cell from the old one
            if(heights[newRow][newCol] < heights[row][col])
                continue;
//                if we have gotten this far, that means the new cell is reachable
            dfs(newRow, newCol, reachable, heights);
        }
    }
}

/*
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
Example 1:
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:
Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
Constraints:
m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105
 */

/*
BFS:
It will be much faster because when we start a traversal at a cell, whatever result we end up with can be applied to only that cell. However, when we start from the ocean and work backwards, we already know that every cell we visit must be connected to the ocean.
If we start traversing from the ocean and flip the condition (check for higher height instead of lower height), then we know that every cell we visit during the traversal can flow into that ocean. Let's start a BFS traversal from every cell that is immediately beside the Pacific ocean, and figure out what cells can flow into the Pacific. Then, let's do the exact same thing with the Atlantic ocean. At the end, the cells that end up connected to both oceans will be our answer.
DFS:
Intuitively, BFS makes more sense for this problem since water flows in the same manner. However, we can also use DFS, and it doesn't really make much of a difference.
DFS is very similar to BFS. Instead of using a queue and working iteratively, we'll use recursion. Our dfs method will be called for every reachable cell. Note: we could also work iteratively with DFS, in which case we would simply use a stack instead of a queue like in the Approach 1 code, with mostly everything else being identical to the BFS approach.
 */