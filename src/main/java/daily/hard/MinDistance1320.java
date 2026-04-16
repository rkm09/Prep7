package daily.hard;

public class MinDistance1320 {
    public static void main(String[] args) {
        System.out.println(minimumDistance("CAKE"));
    }

//    dp; optimized(2d); time: O(n * 26), space: O(n * 26)
    public static int minimumDistance(String word) {
//        dp[i][j] represents the minimum distance to reach index i
//        with the 'other' finger at character 'j'
//        j = 26, represents hovering state, 0 cost
        int n = word.length();
        int[][] dp = new int[n][27];

//        initialize
        for(int i = 0; i < n; i++)
            for(int j = 0; j < 27; j++)
                dp[i][j] = Integer.MAX_VALUE;

//        base case: 0 difference at the start, other finger is hovering
        dp[0][26] = 0;

        for(int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';

            for(int other = 0; other <= 26; other++) {
                if(dp[i][other] == Integer.MAX_VALUE) continue;

//                move finger from 'curr' to 'next'
                dp[i + 1][other] = Math.min(dp[i + 1][other], dp[i][other] + getDist(curr, next));

//                move 'other' finger to 'next'
                int moveOtherCost = other == 26 ? 0 : getDist(other, next);
                dp[i + 1][curr] = Math.min(dp[i + 1][curr], dp[i][other] + moveOtherCost);
            }
        }

        int minTotalDistance = Integer.MAX_VALUE;
        for(int j = 0; j <= 26; j++)
            minTotalDistance = Math.min(minTotalDistance, dp[n - 1][j]);

        return minTotalDistance;
    }

//    dp; non optimized(3d); time: O(n * 26 * 26), space: O(n * 26 * 26)
    public static int minimumDistance1(String word) {
        int n = word.length();
//        dp[index][finger1_pos][finger2_pos]
//        26 represents the hovering starting position
        int[][][] dp = new int[n][27][27];

//        initialize with infinity
        for(int i = 0; i < n; i++)
            for(int f1 = 0; f1 < 27; f1++)
                for(int f2 = 0; f2 < 27; f2++)
                    dp[i][f1][f2] = Integer.MAX_VALUE / 2;

//        base case: for the first character we can use either finger for 0 cost
        int firstChar = word.charAt(0) - 'A';
        dp[0][firstChar][26] = 0;  // finger 1 at 0, finger 2 hovering
        dp[0][26][firstChar] = 0;  // finger 2 at 0, finger 1 hovering

        for(int i = 0; i < n - 1; i++) {
            int nextChar = word.charAt(i + 1) - 'A';
            for(int f1 = 0; f1 < 27; f1++) {
                for(int f2 = 0; f2 < 27; f2++) {
                    if(dp[i][f1][f2] == Integer.MAX_VALUE) continue;

//                    option 1: move finger 1 to next char
                    int cost1 = (f1 == 26) ? 0 : getDist(f1, nextChar);
                    dp[i + 1][nextChar][f2] = Math.min(dp[i + 1][nextChar][f2],
                            dp[i][f1][f2] + cost1);

//                    option 2: move finger 2 to next char
                    int cost2 = (f2 == 26) ? 0 : getDist(f2, nextChar);
                    dp[i + 1][f1][nextChar] = Math.min(dp[i + 1][f1][nextChar],
                            dp[i][f1][f2] + cost2);
                }
            }
        }

//        result: the minimum value in the last index layer
        int res = Integer.MAX_VALUE;
        for(int f1 = 0; f1 < 27; f1++)
            for(int f2 = 0; f2 < 27; f2++)
                res = Math.min(res, dp[n - 1][f1][f2]);

        return res;
    }

    private static int getDist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}


/*
You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.
For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
Given the string word, return the minimum total distance to type such string using only two fingers.
The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
Example 1:
Input: word = "CAKE"
Output: 3
Explanation: Using two fingers, one optimal way to type "CAKE" is:
Finger 1 on letter 'C' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
Finger 2 on letter 'K' -> cost = 0
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
Total distance = 3
Example 2:
Input: word = "HAPPY"
Output: 6
Explanation: Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6
Constraints:
2 <= word.length <= 300
word consists of uppercase English letters.
 */


/*

 */