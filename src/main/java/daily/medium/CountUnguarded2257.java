package daily.medium;

public class CountUnguarded2257 {
    private static final int UNGUARDED = 0;
    private static final int GUARDED = 1;
    private static final int GUARD = 2;
    private static final int WALL = 3;

    public static void main(String[] args) {
        int[][] guards = {{0,0},{1,1},{2,3}};
        int[][] walls = {{0,1},{2,2},{1,4}};
        CountUnguarded2257 c = new CountUnguarded2257();
        System.out.println(c.countUnguarded(4,6,guards,walls));
    }

//    simulation(iteration); time: O(m.n), space: O(m.n)
    public  int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int count = 0;
//        mark guards positions
        for(int[] guard : guards)
            grid[guard[0]][guard[1]] = GUARD;
//        mark walls positions
        for(int[] wall : walls)
            grid[wall[0]][wall[1]] = WALL;
//        mark cells as guarded by traversing from each guard
        for(int[] guard : guards)
            markGuarded(guard[0], guard[1], grid);
//        count unguarded
        for(int[] row : grid) {
            for(int cell : row) {
                if(cell == UNGUARDED)
                    count++;
            }
        }
        return count;
    }

    private void markGuarded(int row, int col, int[][] grid) {
//        traverse upwards
        for(int r = row - 1 ; r >= 0 ; r--) {
            if((grid[r][col] == GUARD) || (grid[r][col] == WALL))
                break;
            grid[r][col] = GUARDED;
        }
//        traverse downwards
        for(int r = row + 1 ; r < grid.length ; r++) {
            if((grid[r][col] == GUARD) || (grid[r][col] == WALL))
                break;
            grid[r][col] = GUARDED;
        }
//        traverse left
        for(int c = col - 1 ; c >= 0 ; c--) {
            if((grid[row][c] == GUARD) || (grid[row][c] == WALL))
                break;
            grid[row][c] = GUARDED;
        }
//        traverse right
        for(int c = col + 1 ; c < grid[0].length ; c++) {
            if((grid[row][c] == GUARD) || (grid[row][c] == WALL))
                break;
            grid[row][c] = GUARDED;
        }
    }


//    recursion; time: O(m.n), space: O(m.n)
    public  int countUnguarded1(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int count = 0;
    //        mark guards positions
        for(int[] guard : guards)
            grid[guard[0]][guard[1]] = GUARD;
    //        mark walls positions
        for(int[] wall : walls)
            grid[wall[0]][wall[1]] = WALL;
    //        mark cells as guarded by traversing from each guard
        for(int[] guard : guards) {
            recurse(guard[0] - 1, guard[1], grid, 'U');
            recurse(guard[0] + 1, guard[1], grid, 'D');
            recurse(guard[0], guard[1] - 1, grid, 'L');
            recurse(guard[0], guard[1] + 1, grid, 'R');
        }
    //        count unguarded
        for(int[] row : grid) {
            for(int cell : row) {
                if(cell == UNGUARDED)
                    count++;
            }
        }
        return count;
    }

//    depth first search
    private void recurse(int row, int col, int[][] grid, char direction) {
        if(row < 0 || row == grid.length || col < 0 || col == grid[0].length ||
        grid[row][col] == WALL || grid[row][col] == GUARD)
            return;
//        mark cell as guarded
        grid[row][col] = GUARDED;
//        traverse
        if(direction == 'U') // UP
            recurse(row - 1, col, grid, 'U');
        else if(direction == 'D') // DOWN
            recurse(row + 1, col, grid, 'D');
        else if(direction == 'L') // LEFT
            recurse(row, col - 1, grid, 'L');
        else // RIGHT
            recurse(row, col + 1, grid, 'R');
    }
}

/*
You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
Return the number of unoccupied cells that are not guarded.
Example 1:
Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.
Example 2:
Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.
Constraints:
1 <= m, n <= 105
2 <= m * n <= 105
1 <= guards.length, walls.length <= 5 * 104
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.
 */