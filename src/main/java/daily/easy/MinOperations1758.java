package daily.easy;

public class MinOperations1758 {
    public static void main(String[] args) {
        System.out.println(minOperations("10010100")); // 3
    }

//    start with 0 or start with 1; time: O(n), space: O(1)
//    ps: greedy approach using string builder will not work for all inputs
    public static int minOperations(String s) {
        int start1 = 0, start0 = 0, n = s.length();
        for(int i = 0 ; i < n ; i++) {
            if(i % 2 == 0) {
                if(s.charAt(i) == '0')
                    start1++;
                else
                    start0++;
            } else {
                if(s.charAt(i) == '1')
                    start1++;
                else
                    start0++;
            }
        }

        return Math.min(start0, start1);
    }
}

/*
You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
Return the minimum number of operations needed to make s alternating.
Example 1:
Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.
Example 2:
Input: s = "10"
Output: 0
Explanation: s is already alternating.
Example 3:
Input: s = "1111"
Output: 2
Explanation: You need two operations to reach "0101" or "1010".
Constraints:
1 <= s.length <= 104
s[i] is either '0' or '1'.
 */