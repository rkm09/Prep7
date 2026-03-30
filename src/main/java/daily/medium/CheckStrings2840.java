package daily.medium;

import java.util.Arrays;

public class CheckStrings2840 {
    public static void main(String[] args) {
        System.out.println(checkStrings("abe","bea"));
    }

//    freq array; time: O(n), space: O(1)
    public static boolean checkStrings(String s1, String s2) {
        int n = s1.length();
//        frequency arrays to track characters counts at even and odd positions
        int[] evenFreq = new int[26];
        int[] oddFreq = new int[26];

        for(int i = 0 ; i < n ; i++) {
//            if the index is even, update the even frequency array
            if(i % 2 == 0) {
                evenFreq[s1.charAt(i) - 'a']++;
                evenFreq[s2.charAt(i) - 'a']--;
            } else {
                oddFreq[s1.charAt(i) - 'a']++;
                oddFreq[s2.charAt(i) - 'a']--;
            }
        }

//        check if all character frequencies balance out to 0
        for(int i = 0 ; i < 26 ; i++) {
            if(evenFreq[i] != 0 || oddFreq[i] != 0)
                return false;
        }

        return true;
    }
}

/*
You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
You can apply the following operation on any of the two strings any number of times:
Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.
Return true if you can make the strings s1 and s2 equal, and false otherwise.
Example 1:
Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.
Example 2:
Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: It is not possible to make the two strings equal.
Constraints:
n == s1.length == s2.length
1 <= n <= 105
s1 and s2 consist only of lowercase English letters.
 */



/*
The Logic:
The rule states that you can swap two characters if the difference between their indices is even.
An even number minus an even number is even (e.g., 4 - 2 = 2).
An odd number minus an odd number is even (e.g., 5 - 1 = 4).
An even number minus an odd number (or vice versa) is always odd.
This means that characters at even indices can only ever be swapped with other characters at even indices. Similarly, characters at odd indices can only be swapped with other characters at odd indices.
Therefore, for string s1 to be transformed into s2, both strings must have the exact same characters (and frequencies of those characters) in their even positions, and the exact same characters in their odd positions.
 */