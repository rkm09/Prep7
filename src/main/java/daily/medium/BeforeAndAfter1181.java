package daily.medium;

import java.util.*;

public class BeforeAndAfter1181 {
    public static void main(String[] args) {
        String[] phrases = {"writing code", "code rocks"};
        System.out.println(beforeAndAfterPuzzles(phrases));
    }

//    hashset + sorting; time: O(N^2.K(logN + logK))
    public static List<String> beforeAndAfterPuzzles(String[] phrases) {
        int n = phrases.length;
        List<String> res;
        String[][] keys = new String[n][2];
        for(int i = 0 ; i < n ; i++) {
            String[] words = phrases[i].split(" ");
            keys[i][0] = words[0];
            keys[i][1] = words[words.length - 1];
        }
        Set<String> set = new HashSet<>();
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(i == j) continue;
                if(keys[i][0].equals(keys[j][1])) {
                    String merged = phrases[j] + phrases[i].substring(keys[i][0].length());
                    set.add(merged);
                }
            }
        }
        res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}

/*
Given a list of phrases, generate a list of Before and After puzzles.
A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.
Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase is the same as the first word of the second phrase.
Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases matters, we want to consider both orders.
You should return a list of distinct strings sorted lexicographically.
Example 1:
Input: phrases = ["writing code","code rocks"]
Output: ["writing code rocks"]
Example 2:
Input: phrases = ["mission statement",
                  "a quick bite to eat",
                  "a chip off the old block",
                  "chocolate bar",
                  "mission impossible",
                  "a man on a mission",
                  "block party",
                  "eat my words",
                  "bar of soap"]
Output: ["a chip off the old block party",
         "a man on a mission impossible",
         "a man on a mission statement",
         "a quick bite to eat my words",
         "chocolate bar of soap"]
Example 3:
Input: phrases = ["a","b","a"]
Output: ["a"]

Constraints:
1 <= phrases.length <= 100
1 <= phrases[i].length <= 100
 */

/*
Complexity Analysis
Let N be the length of the string array phrases, and K be the average length of the string phrases.
Time complexity: O(N^2.K(logN+logK))
The double traversal of the array costs O(N^2.K).
The hash table can store up to N(N−1) concatenated phrases, where the maximum length of each phrase is 2K−C, with C being the length of the overlapping word. Sorting these strings takes O(N(N−1)(2K−C)(log(N(N−1))+log(2K−C))), which simplifies to the result
Space complexity: O(N^2.K)
The hash table can store up to N(N−1) entries, each occupying O(K) space. In addition, the two-dimensional array sp has a maximum size proportional to the input array phrases.
 */