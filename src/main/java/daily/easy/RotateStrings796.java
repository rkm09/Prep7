package daily.easy;

public class RotateStrings796 {
    public static void main(String[] args) {
        RotateStrings796 r = new RotateStrings796();
        System.out.println(r.rotateString("abcde","cdeab"));
    }

//    concatenation check; time: O(n), space: O(n)
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) return false;
        String doubledStr = s + s;
        return doubledStr.contains(goal);
    }
}

/*
Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
A shift on s consists of moving the leftmost character of s to the rightmost position.
For example, if s = "abcde", then it will be "bcdea" after one shift.
Example 1:
Input: s = "abcde", goal = "cdeab"
Output: true
Example 2:
Input: s = "abcde", goal = "abced"
Output: false
Constraints:
1 <= s.length, goal.length <= 100
s and goal consist of lowercase English letters.
 */
