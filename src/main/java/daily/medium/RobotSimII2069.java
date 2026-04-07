package daily.medium;

public class RobotSimII2069 {
    public static void main(String[] args) {

    }
}

class Robot {
    private final int w, h, perimeter;
    private int pos = 0;
    private boolean moved = false;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
//        the total number of steps to complete one full lap
        perimeter = 2 * (w - 1) + 2 * (h - 1);
    }

    public void step(int num) {
        moved = true;
//        use modulo to handle large number of steps efficiently
        pos = (pos + num) % perimeter;
    }

    public int[] getPos() {
//        map the 1d 'pos' back to 2d (x, y) coordinates
        if(pos <= w - 1)
            return new int[]{pos, 0};
        else if(pos <= w + h - 2)
            return new int[]{w - 1, pos - (w - 1)};
        else if(pos <= 2 * w + h - 3)
            return new int[]{(w - 1) - (pos - (w + h - 2)), h - 1};
        else
            return new int[]{0, (h - 1) - (pos - (2 * w + h - 3))};
    }

    public String getDir() {
//        condition 1: starting state before any movement
        if(!moved) return "East";
//        condition 2: check ranges for the perimeter index
//        still on the bottom edge, facing east
        if(pos >= 1 && pos <= w - 1) return "East";
//        on the right edge, facing north
        if(pos >= w && pos <= w + h - 2) return "North";
//        on the left edge, facing west
        if(pos >= w + h - 1 && pos <= 2 * w + h - 3) return "West";
//        else, including pos = 0 after moving, we are on the left edge facing south
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */


/*
A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1). The grid is aligned with the four cardinal directions ("North", "East", "South", and "West"). A robot is initially at cell (0, 0) facing direction "East".
The robot can be instructed to move for a specific number of steps. For each step, it does the following.
Attempts to move forward one cell in the direction it is facing.
If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
Implement the Robot class:
Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
void step(int num) Instructs the robot to move forward num steps.
int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
Example 1:
example-1
Input
["Robot", "step", "step", "getPos", "getDir", "step", "step", "step", "getPos", "getDir"]
[[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
Output
[null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
Explanation
Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
robot.step(2);  // It moves two steps East to (2, 0), and faces East.
robot.step(2);  // It moves two steps East to (4, 0), and faces East.
robot.getPos(); // return [4, 0]
robot.getDir(); // return "East"
robot.step(2);  // It moves one step East to (5, 0), and faces East.
                // Moving the next step East would be out of bounds, so it turns and faces North.
                // Then, it moves one step North to (5, 1), and faces North.
robot.step(1);  // It moves one step North to (5, 2), and faces North (not West).
robot.step(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
                // Then, it moves four steps West to (1, 2), and faces West.
robot.getPos(); // return [1, 2]
robot.getDir(); // return "West"
Constraints:
2 <= width, height <= 100
1 <= num <= 105
At most 104 calls in total will be made to step, getPos, and getDir.
 */

/*
We created a priority queue for the directions:
The "Never Moved" Exception: If moved is false, we are East. Period.
The "In-Between" Ranges: If we are anywhere on a straight line between corners, the direction is obvious.
The "Corner" Logic: This is the secret sauce. By using pos >= w to start North, we ensure that the exact moment pos hits w (the corner), the robot is already considered "facing North."
The "South" Catch-all: This handles everything else, including pos = 0 (after moving) and the entire left edge. Because pos = 0 doesn't fit into the East, North, or West ranges, it falls into South.
The Real Culprit: The Perimeter Loop
The only way the two versions behave differently is if the robot is at pos = 0.
If it's a new robot, it's "East".
If it's a moved robot, it's "South".
The test case that failed likely looked like this:
Robot(6, 3)
step(14) (One full lap, pos becomes 0)
getDir() -> Should be "South".
step(0)
getDir() -> Should still be "South".
By changing the very first line to if (!moved), you created a permanent "Gate" that only allows the robot to be "East" at the origin exactly once in its entire life. In the previous version, the logic was trying to "guess" based on the pos value, which can be reset to 0 many times.
By saying if (!moved) return "East", you simplified the state machine:
State A: Brand New (East)
State B: Everything else (Determined by the loop)
That "State A" is a unique property that only exists before the very first step() call, and that's usually the nuance those last 3 test cases are hunting for!
*/