package daily.medium;

public class Cycles1559 {
    public static void main(String[] args) {
        Cycles1559 c = new Cycles1559();
        char[][] grid = {{'a', 'b', 'b'}, {'b', 'z', 'b'}, {'b', 'b', 'a'}};
        System.out.println(c.containsCycle(grid));
    }

    //    union find; time: O(M.N.alpha(M.N)), space: O(M.N). [best for this type]
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind dsu = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                flatten 2d into 1d index
                int currentCell = i * n + j;
//                check top neighbor
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    int topCell = (i - 1) * n + j;
                    if (dsu.isConnected(currentCell, topCell))
                        return true; // cycle detected!
                    dsu.union(currentCell, topCell);
                }
//                check left neighbor
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    int leftCell = i * n + (j - 1);
                    if (dsu.isConnected(currentCell, leftCell))
                        return true; // cycle detected!
                    dsu.union(currentCell, leftCell);
                }
            }
        }

        return false;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        //        path compression
        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        //        union
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                if (rank[rootA] > rank[rootB])
                    parent[rootB] = rootA;
                else if (rank[rootA] < rank[rootB])
                    parent[rootA] = rootB;
                else {
                    parent[rootB] = rootA;
                    rank[rootA]++;
                }
            }
        }

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }


//    dfs; time: O(M.N), space: O(M.N)
    public boolean containsCycle1(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
//        iterate through every cell in the grid
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                if the cell hasn't been visited yet, start a new dfs traversal
                if(!visited[i][j]) {
//                    no parent for the starting cell of the path, so we pass -1,-1
                    if (dfs(i, j, -1, -1, grid[i][j], grid, visited))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int r, int c, int prevR, int prevC, char target, char[][] grid, boolean[][] visited) {
//        mark the current cell as visited
        visited[r][c] = true;
//        direction vectors for moving up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
//            check boundaries, check whether neighbor has exact same value
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == target) {
//                skip the cell we just came from
                if (nr == prevR && nc == prevC)
                    continue;
//                if the neighbor is already visited and not the parent then we have looped back into a cycle
                if (visited[nr][nc])
                    return true;
//                otherwise, continue the depth first search down this path
                if (dfs(nr, nc, r, c, target, grid, visited))
                    return true;
            }
        }

        return false;
    }

//    optimised dfs; time: O(m.n), space: O(m.n)
    public boolean containsCycle2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int totalCells = m * n;
//        flattened 1d visited array for cache friendliness
        boolean[] visited = new boolean[totalCells];
//        a single, fixed size primitive long array to serve as our explicit stack, we only need one due to bit packing
        long[] stack = new long[totalCells];
        int top = -1;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int start = 0; start < totalCells; start++) {
            if (visited[start]) continue;

            int startR = start / n;
            int startC = start % n;
//            push initial state: currR = startR, currC = startC, prevR = 0xFFFF, prevC = 0xFFFF (-1 mask)
            top++;
            stack[top] = pack(startR, startC, 0xFFFF, 0xFFFF);
            visited[start] = true;

            while (top >= 0) {
//                pop the bit packed 64 bit state from our primitive stack
                long state = stack[top];
                top--;
//                unpack the 4 coordinates instantly using fast bitwise shifts and masks
                int currR = (int) ((state >> 48) & 0xFFFF);
                int currC = (int) ((state >> 32) & 0xFFFF);
                int prevR = (int) ((state >> 16) & 0xFFFF);
                int prevC = (int) (state & 0xFFFF);

//                convert the 0xFFFF boundary placeholder to -1
               // if(prevR == 0xFFFF) prevR = -1; not needed since even 65535 is unique
               // if(prevC == 0xFFFF) prevC = -1;

                char targetChar = grid[currR][currC];

                for (int i = 0; i < 4; i++) {
                    int nR = currR + dr[i];
                    int nC = currC + dc[i];

                    if (nR >= 0 && nR < m && nC >= 0 && nC < n && grid[nR][nC] == targetChar) {
//                            rule check: skip the cell we immediately stepped out of
                        if (nR == prevR && nC == prevC)
                            continue;

                        int neighbor1D = nR * n + nC;
//                        cycle detected
                        if (visited[neighbor1D])
                            return true;

                        visited[neighbor1D] = true;

//                        push the packed next state to our primitive stack
                        top++;
                        stack[top] = pack(nR, nC, currR, currC);
                    }
                }
            }
        }

        return false;
    }

    private long pack(int currR, int currC, int prevR, int prevC) {
        return ((long) (currR & 0xFFFF) << 48) |
                ((long) (currC & 0xFFFF) << 32) |
                ((long) (prevR & 0xFFFF) << 16) |
                (long) ((prevC & 0xFFFF));
    }

}

/*
Given a 2D array of character grid of size m x n, you need to find if there exists any cycle consisting of the same value in the grid.
A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
Return true if any cycle of the same value exists in grid, otherwise, return false.
Example 1:
Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:
Example 2:
Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:
Example 3:
Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.
 */

/*
Union Find:

Time complexity:
The Union-Find structure uses path compression and union by size or rank, resulting in an amortized cost of α(mn) per operation.
Each position participates in at most two union operations, leading to a total complexity of O(mn⋅α(mn)).

Since union find is one d, we flatten the structure.
(i * n) + j;
Think of it like reading a book. If you are on row i, it means you have completely finished reading all the rows before it (from row 0 to row i - 1).
Since each row contains exactly n elements (columns), you have skipped past exactly i * n elements.
Once you are at the start of row i, you move j steps to the right to reach your specific column.

The "Length 4 or More" Cycle Catch:
It is completely intuitive to look at dsu.isConnected(currentCell, neighbor) and think, "Wait, aren't we just checking if two adjacent cells are connected?
Doesn't that just mean a path of length 2?"
The trick here is understanding what isConnected actually means in Union-Find. It doesn't mean "are these two cells next to each other?"
It means "do these two cells already belong to the exact same connected chain through some other round-about path?"
Because of the way grids work, a cycle of length 2 or 3 is geometrically impossible if you cannot step backward.

Walkthrough of a 4-Cell Cycle:
Imagine a $2 \times 2$ block of identical characters:
(0,0) - (0,1)
  |       |
(1,0) - (1,1)
Process (0,0): No top or left neighbors.
Process (0,1): Left neighbor is (0,0). They aren't connected yet, so we unite them.
Current Chain: (0,0) --- (0,1)
Process (1,0): Top neighbor is (0,0). They aren't connected yet, so we unite them.
Current Chain: (1,0) --- (0,0) --- (0,1)
Process (1,1): * First, we look Up at (0,1). They aren't connected to our chain yet, so we unite them.
Current Chain: (1,0) --- (0,0) --- (0,1) --- (1,1)
Next, we look Left at (1,0). We ask: dsu.isConnected((1,1), (1,0))?
Yes! They are already connected through the long history of the chain:
(1,1) is connected to (0,1), which is connected to (0,0), which is connected to (1,0).
Because they are already connected via a path of 3 existing edges, adding this 4th edge closes the loop.
Summary: Union-Find doesn't count the length because the grid geometry guarantees that the shortest possible loop
you can close by moving only up, down, left, and right is a $2 \times 2$ square—which is exactly 4 cells long.
 */