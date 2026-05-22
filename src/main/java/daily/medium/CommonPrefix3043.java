package daily.medium;

import java.util.HashSet;
import java.util.Set;

public class CommonPrefix3043 {
//  since we know the cap is at 10^8;
    private static final int[] POW10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
    public static void main(String[] args) {
        CommonPrefix3043 c = new CommonPrefix3043();
        System.out.println(c.longestCommonPrefix(new int[]{1,10,100}, new int[]{1000}));
    }

//    optimised trie (speed up with l1/l2 cache); time: O(m + n), space: O(m)
//    no heap allocation (num/divisor, num % divisor), no array copying (no numStr.toCharArray allocation)
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();
        for(int num : arr1)
            trie.insert(num);

        int longestPrefix = 0;
        for(int num : arr2) {
            int len = trie.findLongestPrefix(num);
            longestPrefix = Math.max(longestPrefix, len);
        }

        return longestPrefix;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[10];
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(int num) {
            TrieNode node = root;
            int log = (int) Math.log10(num);
//            int divisor = (int) Math.pow(10, log);
            int divisor = POW10[log];

            while(divisor > 0) {
                int idx = num / divisor;
                num  %= divisor;
                divisor /= 10;
                if(node.children[idx] == null)
                    node.children[idx] = new TrieNode();

                node = node.children[idx];
            }
        }

        int findLongestPrefix(int num) {
            TrieNode node = root;
            int log = (int) Math.log10(num);
//            int divisor = (int) Math.pow(10, log); // e.g. 10^4
            int divisor = POW10[log];
            int len = 0;

            while(divisor > 0) {
                int idx = num / divisor;
                num %= divisor;
                divisor /= 10;

                if(node.children[idx] != null) {
                    len++;
                    node = node.children[idx];
                } else
                    break;
            }

            return len;
        }
    }

//    trie (gc due to object creation and string conversion); time: O(m.d + n.d) -> O(m + n), space: O(n)
//    pro: fewer total inserts
    public int longestCommonPrefix1(int[] arr1, int[] arr2) {
        Trie1 trie = new Trie1();
        for(int num : arr1)
            trie.insert(num);

        int longestPrefix = 0;
        for(int num : arr2) {
            int len = trie.findLongestPrefix(num);
            longestPrefix = Math.max(longestPrefix, len);
        }

        return longestPrefix;
    }


    static class Trie1 {
        TrieNode root = new TrieNode();

        void insert(int num) {
            TrieNode node = root;
            String numStr = Integer.toString(num);
            for(char digit : numStr.toCharArray()) {
                int idx = digit - '0';
                if(node.children[idx] == null)
                    node.children[idx] = new TrieNode();

                node = node.children[idx];
            }
        }

        int findLongestPrefix(int num) {
            TrieNode node = root;
            String numStr = Integer.toString(num);
            int len = 0;

            for(char digit : numStr.toCharArray()) {
                int idx = digit - '0';
                if(node.children[idx] != null) {
                    len++;
                    node = node.children[idx];
                } else
                    break;
            }

            return len;
        }
    }

    //  optimized hashset(but prone to hash collision); time: O(N + M), space: O(N)
    public int longestCommonPrefix2(int[] arr1, int[] arr2) {
        int ans = 0;
        Set<Integer> prefixSet = new HashSet<>();
        for(int num : arr1) {
            while(num > 0) {
                prefixSet.add(num);
                num /= 10;
            }
        }

        for(int num : arr2) {
            while(num > 0) {
                if(prefixSet.contains(num)) {
                    int len = (int) Math.log10(num) + 1;
                    ans = Math.max(ans, len);
                    break;
                }
                num /= 10;
            }
        }

        return ans;
    }

//    hashset;
    public int longestCommonPrefix3(int[] arr1, int[] arr2) {
        int ans = 0;
        Set<Integer> prefixSet1 = new HashSet<>();
        for(int num : arr1) {
            while(num > 0) {
                prefixSet1.add(num);
                num /= 10;
            }
        }

        Set<Integer> prefixSet2 = new HashSet<>();
        for(int num : arr2) {
            while(num > 0) {
                prefixSet2.add(num);
                num /= 10;
            }
        }

        for(int prefix : prefixSet2) {
            if(prefixSet1.contains(prefix)) {
                int len = String.valueOf(prefix).length();
                ans = Math.max(ans, len);
            }
        }

        return ans;
    }

//    TLE [10^8] ops. brute force;
    public int longestCommonPrefixX(int[] arr1, int[] arr2) {
        int ans = 0;
        for(int num1 : arr1) {
            for (int num2 : arr2) {
                int res = lcp(String.valueOf(num1), String.valueOf(num2));
                if(res != 0)
                    ans = Math.max(ans, res);
            }
        }

        return ans;
    }

    private int lcp(String s1, String s2) {
        int i = 0, j = 0, count = 0;
        while(i < s1.length() && j < s2.length()) {
            if(s1.charAt(i) == s2.charAt(j)) {
                i++; j++; count++;
            } else
                return count;
        }

        return count;
    }
}

/*
You are given two arrays with positive integers arr1 and arr2.
A prefix of a positive integer is an integer formed by one or more of its digits, starting from its leftmost digit. For example, 123 is a prefix of the integer 12345, while 234 is not.
A common prefix of two integers a and b is an integer c, such that c is a prefix of both a and b. For example, 5655359 and 56554 have common prefixes 565 and 5655 while 1223 and 43456 do not have a common prefix.
You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x belongs to arr1 and y belongs to arr2.
Return the length of the longest common prefix among all pairs. If no common prefix exists among them, return 0.
Example 1:
Input: arr1 = [1,10,100], arr2 = [1000]
Output: 3
Explanation: There are 3 pairs (arr1[i], arr2[j]):
- The longest common prefix of (1, 1000) is 1.
- The longest common prefix of (10, 1000) is 10.
- The longest common prefix of (100, 1000) is 100.
The longest common prefix is 100 with a length of 3.
Example 2:
Input: arr1 = [1,2,3], arr2 = [4,4,4]
Output: 0
Explanation: There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
Note that common prefixes between elements of the same array do not count.
Constraints:
1 <= arr1.length, arr2.length <= 5 * 10^4
1 <= arr1[i], arr2[i] <= 10^8
 */