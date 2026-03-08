package daily.medium;

public class MinFlips1888 {
    public static void main(String[] args) {
        System.out.println(minFlips("010"));
    }

//    sliding window; time: O(n), space: O(n)
    public static int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;
//        target patterns for the length of the doubled string
        StringBuilder target1 = new StringBuilder();
        StringBuilder target2 = new StringBuilder();
        for(int i = 0 ; i < 2 * n ; i++) {
            target1.append(i % 2 == 0 ? '0' : '1');
            target2.append(i % 2 == 0 ? '1' : '0');
        }

        int diff1 = 0, diff2 = 0, minFlips = Integer.MAX_VALUE;
        for(int i = 0 ; i < 2 * n ; i++) {
            if(doubled.charAt(i) != target1.charAt(i)) diff1++;
            if(doubled.charAt(i) != target2. charAt(i)) diff2++;
//            if the window size exceeds n, remove the character leaving the window
            if(i >= n) {
                if(doubled.charAt(i - n) != target1.charAt(i - n)) diff1--;
                if(doubled.charAt(i - n) != target2.charAt(i - n)) diff2--;
            }
//            once the window size is exactly n, track the minFlips
            if(i >= n - 1)
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
        }

        return minFlips;
    }
}

/*
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
The string is called alternating if no two adjacent characters are equal.
For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
Example 1:
Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:
Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:
Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
Constraints:
1 <= s.length <= 105
s[i] is either '0' or '1'.
 */

/*
Analyzing Alternation Minimization
I'm currently focused on minimizing Type-2 operations (bit flips) in a binary string. The Type-1 operations (rotations) don't seem to have a direct impact on the count, so I'm primarily exploring algorithms to efficiently find the optimal alternation pattern that requires the fewest bit flips, given any number of cyclic shifts. I'm considering dynamic programming and greedy approaches.
Exploring Optimal Rotations
I've refined my approach to focus on efficient rotation analysis. The sliding window technique, combined with concatenating the string with itself, lets me assess all cyclic shifts with a single pass. I'm now comparing each window against both 0101... and 1010... targets within the concatenated string to find the minimal bit flips required across all rotations.
Refining Mismatch Calculation. I've developed a more streamlined method for computing mismatches. I'm concatenating the original string with itself to simulate rotations. I compare this combined string against two alternating patterns, 0101... and 1010.... I'm using a sliding window to efficiently calculate and track the differences (bit flips) between the string and the target patterns, considering how characters enter and leave the window. The crucial insight is that each window represents a unique rotation. I now have the method fully implemented.
This problem asks for the minimum number of bit flips (Type-2 operations) required to make a binary string alternating, given that we can rotate the string (Type-1 operations) as many times as we want.
The most efficient way to solve this is by using a sliding window approach on a concatenated version of the string.
The Logic
Concatenation: To simulate all possible rotations (Type-1 operations) without actually moving characters, we concatenate the string s with itself (s+s). Any window of length n (the original length) within this new string represents one possible rotation of s.
Target Patterns: An alternating string of length n can only look like one of two patterns:
Pattern A: 010101...
Pattern B: 101010...
Sliding Window: We slide a window of size n across the doubled string.
For each window, we count how many characters differ from Pattern A and Pattern B.
The minimum difference found across all windows is our answer.
 */