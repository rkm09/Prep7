package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestBalanced3714 {
    public static void main(String[] args) {
        String s = "abbac";
        System.out.println(longestBalanced(s));
    }

    public static int longestBalanced(String s) {
        int n = s.length(), ans = 0;
        if(n == 0) return 0;
        char[] arr = s.toCharArray();

//        1 character case
        ans = Math.max(ans, countOne(arr));

//        2 characters case
        ans = Math.max(ans, countTwo('a', 'b', arr));
        ans = Math.max(ans, countTwo('a', 'c', arr));
        ans = Math.max(ans, countTwo('b', 'c', arr));

//        3 characters case
        ans = Math.max(ans, countThree(arr));
        return ans;
    }

    private static int countOne(char[] arr) {
        int ans = 0, count = 0;
        char curr = arr[0];
        for(char val : arr) {
            if(val == curr)
                count++;
            else {
                curr = val;
                count = 1;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    private static int countTwo(char c1, char c2, char[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0, sum = 0, n = arr.length;
        for(int i = 0 ; i < n ; i++) {
            if(arr[i] == c1)
                sum++;
            else if(arr[i] == c2)
                sum--;
            else {
                map = new HashMap<>();
                sum = 0;
                map.put(0, i);
                continue;
            }
            if(map.containsKey(sum))
                ans = Math.max(ans, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return ans;
    }

    private static int countThree(char[] arr) {
        Map<String, Integer> map = new HashMap<>();
        map.put("0#0", -1);
        int a = 0, b = 0, c = 0, maxLength = 0, n = arr.length;
        for(int i = 0 ; i < n ; i++) {
            if(arr[i] == 'a') a++;
            else if(arr[i] == 'b') b++;
            else c++;
            int diff1 = a - b;
            int diff2 = a - c;
            String key = diff1 + "#" + diff2;
            if(map.containsKey(key))
                maxLength = Math.max(maxLength, i - map.get(key));
            else
                map.put(key, i);
        }
        return maxLength;
    }
}

/*
You are given a string s consisting only of the characters 'a', 'b', and 'c'.
A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
Return the length of the longest balanced substring of s.
Example 1:
Input: s = "abbac"
Output: 4
Explanation:
The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.
Example 2:
Input: s = "aabcc"
Output: 3
Explanation:
The longest balanced substring is "abc" because all distinct characters 'a', 'b' and 'c' each appear exactly 1 time.
Example 3:
Input: s = "aba"
Output: 2
Explanation:
One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".
Constraints:
1 <= s.length <= 105
s contains only the characters 'a', 'b', and 'c'.
 */
