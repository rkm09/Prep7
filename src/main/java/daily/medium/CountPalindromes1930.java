package daily.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountPalindromes1930 {
    public static void main(String[] args) {
        String s = "aabca";
        System.out.println(countPalindromicSubsequence(s));
    }

//    precompute first and last indices; time: O(n), space: O(1)
    public static int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for(int i = 0 ; i < s.length() ; i++) {
            int curr = s.charAt(i) - 'a';
            if(first[curr] == - 1)
                first[curr] = i;
            last[curr] = i;
        }
        int ans = 0;
        for(int i = 0 ; i < 26 ; i++){
            if(first[i] == -1) continue;
            Set<Character> between = new HashSet<>();
            for(int j = first[i] + 1 ; j < last[i] ; j++)
                between.add(s.charAt(j));
            ans += between.size();
        }
        return ans;
    }
}

/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
A palindrome is a string that reads the same forwards and backwards.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
Example 1:
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:
Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")
Constraints:
3 <= s.length <= 105
s consists of only lowercase English letters.
 */