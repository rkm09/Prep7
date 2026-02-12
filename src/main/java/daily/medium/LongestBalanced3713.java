package daily.medium;

import java.util.Arrays;

public class LongestBalanced3713 {
    public static void main(String[] args) {
        String s = "abbac";
        System.out.println(longestBalanced(s));
    }

//    enumeration; time: O(CN^2), space: O(C)
    public static int longestBalanced(String s) {
        int n = s.length(), res = 0;
        int[] cnt = new int[26];
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(cnt, 0);
            for(int j = i ; j < n ; j++) {
                boolean flag = true;
                int c = s.charAt(j) - 'a';
                cnt[c]++;
                for(int x : cnt) {
                    if(x > 0 && x != cnt[c]) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }
}

/*
You are given a string s consisting of lowercase English letters.
A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
Return the length of the longest balanced substring of s.
Example 1:
Input: s = "abbac"
Output: 4
Explanation:
The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.
Example 2:
Input: s = "zzabccy"
Output: 4
Explanation:
The longest balanced substring is "zabc" because the distinct characters 'z', 'a', 'b', and 'c' each appear exactly 1 time.
Example 3:
Input: s = "aba"
Output: 2
Explanation:
One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".
Constraints:
1 <= s.length <= 1000
s consists of lowercase English letters.
 */

/*
Let C be the size of the character set, which is 26 in this case, and let n be the length of the string s.
Time complexity: O(Cn^2).Enumerating all substrings takes O(n^2) time, and checking whether a substring is balanced takes O(C) time.
Space complexity: O(C).
 */