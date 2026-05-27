package daily.medium;

import java.util.*;

public class NumSpecial3121 {
    public static void main(String[] args) {
        System.out.println(numberOfSpecialChars("aaAbcBC"));
    }

//    record last & first; time: O(n), space: O(1)
    public static int numberOfSpecialChars(String word) {
        int[] lastLow = new int[26];
        int[] firstUp = new int[26];
        Arrays.fill(lastLow, -1);
        Arrays.fill(firstUp, -1);

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(Character.isLowerCase(c))
                lastLow[c - 'a'] = i;
            else if(firstUp[c - 'A'] == -1)
                firstUp[c - 'A'] = i;
        }

        int count = 0;
        for(int i = 0; i < 26; i++)
            if(lastLow[i] != -1 && firstUp[i] != -1 && lastLow[i] < firstUp[i])
                count++;

        return count;
    }

//    hashmap; time: O(n), space: O(n)
    public static int numberOfSpecialChars1(String word) {
        Map<Character, List<Integer>> freqMap = new HashMap<>();
        int count = 0;
        for(int i = 0; i < word.length() ; i++)
            freqMap.computeIfAbsent(word.charAt(i), k -> new ArrayList<>())
                    .add(i);
        for(char lower = 'a'; lower <= 'z'; lower++) {
            char upper = (char)(lower - 'a' + 'A');
            if(freqMap.containsKey(lower) && freqMap.containsKey(upper)) {
                if(freqMap.get(lower).getLast() < freqMap.get(upper).getFirst())
                    count++;
            }
        }

        return count;
    }
}

/*
You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
Return the number of special letters in word.
Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters are 'a', 'b', and 'c'.
Example 2:
Input: word = "abc"
Output: 0
Explanation:
There are no special characters in word.
Example 3:
Input: word = "AbBCab"
Output: 0
Explanation:
There are no special characters in word.
Constraints:
1 <= word.length <= 2 * 10^5
word consists of only lowercase and uppercase English letters.
 */