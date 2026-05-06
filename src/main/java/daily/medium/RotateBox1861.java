package daily.medium;

public class RotateBox1861 {
    public static void main(String[] args) {
        char[][] boxGrid = {{'#'},{'.'},{'#'}};
        char[][] res = rotateTheBox(boxGrid);
    }

//    combine rotation and gravity; time: O(m.n), space: O(1)
    public static char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] result = new char[n][m];
//        initialise
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                result[i][j] = '.';
//        apply gravity to let stones fall to the lowest empty cell in each column
        for(int i = 0; i < m; i++) {
            int lowestRowWithEmptyCell = n - 1;
            for(int j = n - 1; j >= 0; j--) {
//                found a stone
                if(boxGrid[i][j] == '#') {
                    result[lowestRowWithEmptyCell][m - 1 - i] = '#';
                    lowestRowWithEmptyCell--;
                }
                else if(boxGrid[i][j] == '*') {
                    result[j][m - 1 - i] = '*';
                    lowestRowWithEmptyCell = j - 1;
                }
            }
        }

        return result;
    }
}

/*
You are given an m x n matrix of characters boxGrid representing a side-view of a box. Each cell of the box is one of the following:
A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
It is guaranteed that each stone in boxGrid rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.
Example 1:
Input: boxGrid = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:
Input: boxGrid = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:
Input: boxGrid = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]

Constraints:
m == boxGrid.length
n == boxGrid[i].length
1 <= m, n <= 500
boxGrid[i][j] is either '#', '*', or '.'.
 */