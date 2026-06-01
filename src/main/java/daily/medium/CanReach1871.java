package daily.medium;


public class CanReach1871 {
    public static void main(String[] args) {
        System.out.println(canReach("011010", 2, 3));
    }

//    bottom up dp + prefix sum; time: O(n), space: O(n)
    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
//        if the last character is a '1', we can never reach it
        if (s.charAt(n - 1) != '0')
            return false;

//        dp[i] will be 1, if index i is reachable, 0 otherwise
        int[] dp = new int[n];
//        prefixSum[i] stores the prefix sum of the dp array
        int[] prefixSum = new int[n];
//        base case: we start at 0
        dp[0] = 1;
        prefixSum[0] = 1;

        for (int i = 1; i < n; i++) {
//            if the current character is '0', check if it is reachable
            if (s.charAt(i) == '0') {
//                define search boundaries
                int left = i - maxJump;
                int right = i - minJump;
//                ensure window boundaries are valid
                if (right >= 0) {
                    left = Math.max(left, 0);
//                    count reachable positions in the window [left, right] using prefix sums
                    int reachableInWindow = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
                    if (reachableInWindow > 0)
                        dp[i] = 1;
                }
            }
//            maintain the prefix sums sequentially
            prefixSum[i] = prefixSum[i - 1] + dp[i];
        }

        return dp[n - 1] == 1;
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
2 <= s.length <= 10^5
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
 */
