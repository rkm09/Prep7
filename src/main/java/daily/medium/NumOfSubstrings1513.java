package daily.medium;

public class NumOfSubstrings1513 {
    private static final int MOD = (int) 1e9 + 7;
    public static void main(String[] args) {
        String s = "0110111";
        System.out.println(numSub(s));
    }

//    traverse to find the longest substring; time: O(n), space: O(1)
    public static int numSub(String s) {
        int n = s.length();
        long total = 0, consecutiveOnes = 0;
        for(int i = 0 ; i < n ; i++) {
            char c = s.charAt(i);
            if(c == '0') {
                total += (consecutiveOnes * (consecutiveOnes + 1)) / 2;
                total %= MOD;
                consecutiveOnes = 0;
            } else
                consecutiveOnes++;
        }
        total += (consecutiveOnes * (consecutiveOnes + 1)) / 2;
        total %= MOD;
        return (int) total;
    }
}

/*
Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.
Example 1:
Input: s = "0110111"
Output: 9
Explanation: There are 9 substring in total with only 1's characters.
"1" -> 5 times.
"11" -> 3 times.
"111" -> 1 time.
Example 2:
Input: s = "101"
Output: 2
Explanation: Substring "1" is shown 2 times in s.
Example 3:
Input: s = "111111"
Output: 21
Explanation: Each substring contains only 1's characters.
Constraints:
1 <= s.length <= 105
s[i] is either '0' or '1'.
 */

/*
Approach: Traverse The String To Find The Longest Substring
If the length of a string consisting entirely of the character 1 is k, then the number of substrings consisting of all 1 characters (including the string itself) is
(k×(k+1))/2
First, find all the longest substrings that contain only the character 1. The phrase “the longest substring that contains only the character 1” means that, assuming the substring’s index range is [i,j] (inclusive), where i≤j, all characters within the substring are 1. Additionally, the index i must either be at the leftmost position of the string s or the character at index i−1 must be 0, and the index j must either be at the rightmost position of s or the character at index j+1 must be 0.
After identifying all such longest substrings containing only the character 1, the total number of substrings consisting entirely of 1 can be calculated.
The specific method is to traverse the string from left to right. Whenever a character 1 is encountered, count the number of consecutive 1s. When a character 0 is encountered, it indicates that the traversal of the current substring containing only 1s has ended. Compute the number of substrings based on the length of this substring, then reset the counter for consecutive 1s. After the traversal is complete, if the counter for consecutive 1s is still greater than zero, it means there is one final substring consisting entirely of 1s, so the number of substrings for it should also be calculated.
 */