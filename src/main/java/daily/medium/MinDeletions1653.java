package daily.medium;

public class MinDeletions1653 {
    public static void main(String[] args) {
        String s = "aababbab";
        System.out.println(minimumDeletions(s));
    }

//    dp; time: O(n), space: O(n)
    public static int minimumDeletions(String s) {
        int n = s.length(), bCount = 0;
        int[] dp = new int[n + 1];
        for(int i = 0 ; i < n ; i++) {
            if(s.charAt(i) == 'b') {
                dp[i + 1] = dp[i];
                bCount++;
            } else {
//                 either keep 'a' or remove 'a'
                dp[i + 1] = Math.min(dp[i] + 1, bCount);
            }
        }
        return dp[n];
    }

//    optimized dp; time: O(n), space: O(1)
    public static int minimumDeletions1(String s) {
        int n = s.length(), minDeletions = 0, bCount = 0;
        for(int i = 0 ; i < n ; i++) {
            if(s.charAt(i) == 'b')
                bCount++;
            else
                minDeletions = Math.min(minDeletions + 1, bCount);
        }
        return minDeletions;
    }
}

/*
You are given a string s consisting only of characters 'a' and 'b'.
You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
Return the minimum number of deletions needed to make s balanced.
Example 1:
Input: s = "aababbab"
Output: 2
Explanation: You can either:
Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
Example 2:
Input: s = "bbaaaaabb"
Output: 2
Explanation: The only solution is to delete the first two characters.
Constraints:
1 <= s.length <= 105
s[i] is 'a' or 'b'.
 */