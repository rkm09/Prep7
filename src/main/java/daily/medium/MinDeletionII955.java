package daily.medium;

import java.util.Arrays;

public class MinDeletionII955 {
    public static void main(String[] args) {
        String[] strs = {"ca","bb","ac"};
        System.out.println(minDeletionSize(strs));
    }

//    greedy; time: O(n^2), space: O(n)
    public static int minDeletionSize(String[] strs) {
        int rows = strs.length, cols = strs[0].length();
        int minDeletions = 0;
        String[] curr = new String[rows];
        for(int c = 0 ; c < cols ; c++) {
            String[] curr2 = Arrays.copyOf(curr, rows);
            for(int r = 0 ; r < rows ; r++)
                curr2[r] += strs[r].charAt(c);
            if(isSorted(curr2))
                curr = curr2;
            else
                minDeletions++;
        }
        return minDeletions;
    }

    private static boolean isSorted(String[] arr) {
        for(int i = 0 ; i < arr.length - 1 ; i++)
            if(arr[i].compareTo(arr[i + 1]) > 0)
                return false;
        return true;
    }
}

/*
You are given an array of n strings strs, all of the same length.
We may choose any deletion indices, and we delete all the characters in those indices for each string.
For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"].
Suppose we chose a set of deletion indices answer such that after deletions, the final array has its elements in lexicographic order (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]). Return the minimum possible value of answer.length.
Example 1:
Input: strs = ["ca","bb","ac"]
Output: 1
Explanation:
After deleting the first column, strs = ["a", "b", "c"].
Now strs is in lexicographic order (ie. strs[0] <= strs[1] <= strs[2]).
We require at least 1 deletion since initially strs was not in lexicographic order, so the answer is 1.
Example 2:
Input: strs = ["xc","yb","za"]
Output: 0
Explanation:
strs is already in lexicographic order, so we do not need to delete anything.
Note that the rows of strs are not necessarily in lexicographic order:
i.e., it is NOT necessarily true that (strs[0][0] <= strs[0][1] <= ...)
Example 3:
Input: strs = ["zyx","wvu","tsr"]
Output: 3
Explanation: We have to delete every column.
Constraints:
n == strs.length
1 <= n <= 100
1 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */

/*
Greedy choice: safe and optimal
We only delete a column when keeping it would break the order
Once str[i][col] < str[i+1][col], the order between these two strings is locked forever and future columns cannot change
 */