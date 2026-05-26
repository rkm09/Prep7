package daily.easy;

import java.util.HashSet;
import java.util.Set;

public class SpecialChars3120 {
    public static void main(String[] args) {
        System.out.println(numberOfSpecialChars("aaAbcBC"));
    }

//    hashset; time: O(n), space: O(n)
    public static int numberOfSpecialChars(String word) {
        Set<Character> set = new HashSet<>();
        for(char c : word.toCharArray())
            set.add(c);
        int ans = 0;
        for(char c = 'a'; c <= 'z'; c++) {
            if(set.contains(c) && set.contains((char) (c - 'a' + 'A')))
                ans++;
        }

        return ans;
    }
}

/*
You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
Return the number of special letters in word.
Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters in word are 'a', 'b', and 'c'.
Example 2:
Input: word = "abc"
Output: 0
Explanation:
No character in word appears in uppercase.
Example 3:
Input: word = "abBCab"
Output: 1
Explanation:
The only special character in word is 'b'.
Constraints:
1 <= word.length <= 50
word consists of only lowercase and uppercase English letters.
 */