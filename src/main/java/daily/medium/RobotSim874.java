package daily.medium;

import java.util.HashSet;
import java.util.Set;

public class RobotSim874 {
    public static void main(String[] args) {
        System.out.println(robotSim(new int[]{4,-1,3}, new int[][]{}));
    }

//    simulation; time: O(n + k), space: O(k)
    public static int robotSim(int[] commands, int[][] obstacles) {
//        directional vectors for north, east, south and west
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int d = 0; // starts facing north (index 0)
        int x = 0, y = 0;
        int maxDistSquared = 0;

//        encode obstacles into a single long for O(1) lookup
        Set<Long> obstacleSet = new HashSet<>();
        for(int[] obs : obstacles) {
            long ox = obs[0];
            long oy = obs[1];
//            shift x 32 bits and merge with y using bitwise OR [prevent steamrolling, do not forget 'L']
            obstacleSet.add((ox << 32) | (oy & 0xffffffffL));
        }

            for(int cmd : commands) {
                if(cmd == -1)
                    d = (d + 1) % 4; // turn right 90 degrees
                else if(cmd == -2)
                    d = (d + 3) % 4; // turn left 90 degrees
                else {
//                    move forward 'cmd' steps, one unit at a time
                    for(int step = 0 ; step < cmd ; step++) {
                        int nextX = x + dirs[d][0];
                        int nextY = y + dirs[d][1];
//                        encode the theoretical next position to check against obstacles
                        long nextPos = ((long) nextX << 32) | (nextY & 0xffffffffL);
                        if(obstacleSet.contains(nextPos))
                            break; // blocked; stop moving for this specific command

//                        safe to move
                        x = nextX;
                        y = nextY;

//                        track the maximum squared distance globally
                        maxDistSquared = Math.max(maxDistSquared, x * x + y * y);
                    }
                }
            }

            return maxDistSquared;
    }
}


/*
A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot receives an array of integers commands, which represents a sequence of moves that it needs to execute. There are only three possible types of instructions the robot can receive:
-2: Turn left 90 degrees.
-1: Turn right 90 degrees.
1 <= k <= 9: Move forward k units, one unit at a time.
Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, it will stay in its current location (on the block adjacent to the obstacle) and move onto the next command.
Return the maximum squared Euclidean distance that the robot reaches at any point in its path (i.e. if the distance is 5, return 25).
Note:
There can be an obstacle at (0, 0). If this happens, the robot will ignore the obstacle until it has moved off the origin. However, it will be unable to return to (0, 0) due to the obstacle.
North means +Y direction.
East means +X direction.
South means -Y direction.
West means -X direction.
Example 1:
Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation:
The robot starts at (0, 0):
Move north 4 units to (0, 4).
Turn right.
Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
Example 2:
Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation:
The robot starts at (0, 0):
Move north 4 units to (0, 4).
Turn right.
Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
Turn left.
Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
Example 3:
Input: commands = [6,-1,-1,6], obstacles = [[0,0]]
Output: 36
Explanation:
The robot starts at (0, 0):
Move north 6 units to (0, 6).
Turn right.
Turn right.
Move south 5 units and get blocked by the obstacle at (0,0), robot is at (0, 1).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
Constraints:
1 <= commands.length <= 104
commands[i] is either -2, -1, or an integer in the range [1, 9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
The answer is guaranteed to be less than 231.
 */


/*
The Solution: The Bitmask (& 0xFFFFFFFFL)
The bitmask 0xFFFFFFFFL is a 64-bit long where the lower 32 bits are 1s and the upper 32 bits are 0s:
0x00000000 FFFFFFFF
When we do nextY & 0xFFFFFFFFL, the bitwise AND (&) forces the upper 32 bits of the promoted long to be 0, regardless of whether nextY was positive or negative.
It isolates the exact 32-bit pattern of nextY into the lower half, leaving a clean canvas of 0s in the upper half.
0xFFFFFFFF FFFFFFFF  (nextY promoted to long)
& 0x00000000 FFFFFFFF  (Our bitmask)
-----------------------
  0x00000000 FFFFFFFF  (Masked Y)
0x00000005 00000000  (nextX << 32)
| 0x00000000 FFFFFFFF  (Masked nextY)
-----------------------
  0x00000005 FFFFFFFF  (Success!)
Gemini said
Actually, it’s the other way around in the code: -1 is for turning Right and -2 is for turning Left. But I'm glad you asked, because the math behind why we use +3 instead of -1 is a classic trick for dealing with "Modular Arithmetic" in programming.

The Directional Circle
We represent our directions in an array where the index d maps like this:
0: North
1: East
2: South
3: West
1. Turning Right (Command -1)
Turning right is straightforward. If you are at North (0) and turn right, you want to go to East (1).
If you are at West (3) and turn right, you want to loop back to North (0).
The Math: (d + 1) % 4
Example: (3 + 1) % 4 = 0 (West to North).
Complexity Analysis
Time Complexity: O(N + K) where N is the length of the commands array and K is the number of obstacles. Encoding the obstacles takes O(K) time. In the worst case, the robot moves 9 steps per command, meaning the inner loop runs a maximum of 9 * N times. This reduces to O(N). Overall time is strictly linear.
Space Complexity: O(K) to store the obstacles in the HashSet. By using Long primitives instead of instantiated String objects, the space overhead is kept to an absolute minimum.

ExpressionTypeNumerical ValueEffect on Masking
0xFFFFFFFFint-1Fails.
It doesn't clear the top bits because it's only 32 bits wide itself.
0xFFFFFFFFLlong4,294,967,295Succeeds. It's 64 bits wide;
its top 32 bits are 0, so it effectively "wipes" the top half of any long it's ANDed with.

 */