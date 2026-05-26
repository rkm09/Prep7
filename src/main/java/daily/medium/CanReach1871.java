package daily.medium;

public class CanReach1871 {
    public static void main(String[] args) {
        System.out.println(canReach("011010", 2, 3));
    }

    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if(s.charAt(n - 1) != '0')
            return false;
        boolean[] dp = new boolean[n];
        return dfs(s.toCharArray(), minJump, maxJump, dp, 0);
    }

    private static boolean dfs(char[] c, int minJump, int maxJump, boolean[] dp, int idx) {
        if(idx == c.length - 1)
            return true;
            for(int j = idx + minJump ; j <= idx + maxJump; j++) {
                if(c[j] != '0') continue;

            }

    }
}

/*
You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'.
You can move from index i to index j if the following conditions are fulfilled:
i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.
Example 1:
Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3.
In the second step, move from index 3 to index 5.
Example 2:
Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
Constraints:
2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
 */
