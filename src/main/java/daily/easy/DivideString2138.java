package daily.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideString2138 {
    public static void main(String[] args) {
        String s = "abcdefghij";
        System.out.println(Arrays.toString(divideString(s, 3, 'x')));
    }

//    search for starting index; time: O(max(n,k)), space: O(1)
    public static String[] divideString(String s, int k, char fill) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        int curr = 0; // starting index of each group
        while(curr < n) {
            int end = Math.min(curr + k, n);
            res.add(s.substring(curr, end));
            curr += k;
        }
//        fill the last group if required
        StringBuilder sb = new StringBuilder(res.getLast());
        int len = sb.length();
        if(len < k) {
            sb.append(String.valueOf(fill).repeat(k - len));
        }
        res.set(res.size() - 1, sb.toString());
        return res.toArray(new String[0]);
    }

//    def; time: O(max(n,k)), space: O(1)
    public static String[] divideString1(String s, int k, char fill) {
        int n = s.length(), i, j = 0;
        int length = (int) Math.ceil((double) n/k);
        String[] res = new String[length];
        for(i = 0 ; i < n - k + 1 ; ) {
            String sub = s.substring(i, i + k);
            res[j++]= sub;
            i += k;
        }
        if(n % k != 0) {
            StringBuilder sb = new StringBuilder();
            String sub = s.substring(i);
            sb.append(sub);
            int l = 0;
            while(l++ < k - sub.length()) {
                sb.append(fill);
            }
            res[j] = sb.toString();
        }

        return res;
    }
}

/*
A string s can be partitioned into groups of size k using the following procedure:
The first group consists of the first k characters of the string, the second group consists of the next k characters of the string, and so on. Each element can be a part of exactly one group.
For the last group, if the string does not have k characters remaining, a character fill is used to complete the group.
Note that the partition is done so that after removing the fill character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be s.
Given the string s, the size of each group k and the character fill, return a string array denoting the composition of every group s has been divided into, using the above procedure.

Example 1:
Input: s = "abcdefghi", k = 3, fill = "x"
Output: ["abc","def","ghi"]
Explanation:
The first 3 characters "abc" form the first group.
The next 3 characters "def" form the second group.
The last 3 characters "ghi" form the third group.
Since all groups can be completely filled by characters from the string, we do not need to use fill.
Thus, the groups formed are "abc", "def", and "ghi".
Example 2:
Input: s = "abcdefghij", k = 3, fill = "x"
Output: ["abc","def","ghi","jxx"]
Explanation:
Similar to the previous example, we are forming the first three groups "abc", "def", and "ghi".
For the last group, we can only use the character 'j' from the string. To complete this group, we add 'x' twice.
Thus, the 4 groups formed are "abc", "def", "ghi", and "jxx".

Constraints:
1 <= s.length <= 100
s consists of lowercase English letters only.
1 <= k <= 100
fill is a lowercase English letter.
 */