package daily.easy;

import java.util.Arrays;

public class CanBeEqual2839 {
    public static void main(String[] args) {
        System.out.println(canBeEqual("abcd","cdab"));
    }

//    time: O(1), space: O(1)
    public static boolean canBeEqual(String s1, String s2) {
//        group characters by index parity
        char[] s1Even = {s1.charAt(0), s1.charAt(2)};
        char[] s1Odd = {s1.charAt(1), s1.charAt(3)};

        char[] s2Even = {s2.charAt(0), s2.charAt(2)};
        char[] s2Odd = {s2.charAt(1), s2.charAt(3)};

        Arrays.sort(s1Even); Arrays.sort(s1Odd);
        Arrays.sort(s2Even); Arrays.sort(s2Odd);

        return Arrays.equals(s1Even, s2Even) && Arrays.equals(s1Odd, s2Odd);
    }
}

/*
You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
You can apply the following operation on any of the two strings any number of times:
Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
Return true if you can make the strings s1 and s2 equal, and false otherwise.
Example 1:
Input: s1 = "abcd", s2 = "cdab"
Output: true
Explanation: We can do the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbad".
- Choose the indices i = 1, j = 3. The resulting string is s1 = "cdab" = s2.
Example 2:
Input: s1 = "abcd", s2 = "dacb"
Output: false
Explanation: It is not possible to make the two strings equal.
Constraints:
s1.length == s2.length == 4
s1 and s2 consist only of lowercase English letters.
 */

/*
To solve this problem, we need to look at the restriction of the swap operation. Since you can only swap indices where $j - i = 2$, it means:Characters at even indices (0 and 2) can only be swapped with each other.Characters at odd indices (1 and 3) can only be swapped with each other.Essentially, the even-indexed characters form one independent group, and the odd-indexed characters form another.
Because the strings are always length 4, we don't need complex frequency maps. We can simply compare the sorted characters of the even positions and the sorted characters of the odd positions.
 */