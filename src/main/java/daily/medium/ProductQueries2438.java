package daily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductQueries2438 {
    private static final int MOD = (int) 1e9 + 7;
    public static void main(String[] args) {
        int[][] queries = {{0,1},{2,2},{0,3}};
        System.out.println(Arrays.toString(productQueries(15, queries)));
    }

//    binary decomposition + direct calculation; time: O(QlogN), space: O(logN) where Q is the query length
    public static int[] productQueries(int n, int[][] queries) {
        List<Integer> powers = new ArrayList<>();
        int rep = 1;
        while(n > 0) {
            if(n % 2 == 1) {
                powers.add(rep);
            }
            n /= 2;
            rep *= 2;
        }
        int[] res = new int[queries.length];
        for(int i = 0 ; i < queries.length ; i++) {
            long curr = 1;
            int start = queries[i][0];
            int end = queries[i][1];
            for(int j = start ; j <= end ; j++) {
                curr = (curr * powers.get(j)) % MOD;
            }
            res[i] = (int) curr;
        }
        return res;
    }
}

/*
Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.
You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i] represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.
Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the answer to the ith query may be too large, each answers[i] should be returned modulo 109 + 7.
Example 1:
Input: n = 15, queries = [[0,1],[2,2],[0,3]]
Output: [2,4,64]
Explanation:
For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a smaller size.
Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
Answer to 2nd query: powers[2] = 4.
Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64.
Each answer modulo 109 + 7 yields the same answer, so [2,4,64] is returned.
Example 2:
Input: n = 2, queries = [[0,0]]
Output: [2]
Explanation:
For n = 2, powers = [2].
The answer to the only query is powers[0] = 2. The answer modulo 109 + 7 is the same, so [2] is returned.

Constraints:
1 <= n <= 109
1 <= queries.length <= 105
0 <= starti <= endi < powers.length
 */

/*
We need to decompose n into the smallest number of powers of 2, which suggests writing n in binary form. If the k-th binary digit from low to high (where k≥0) is 1, then 2
k is included in the decomposition.
After obtaining the decomposition of n, and since the problem guarantees that n ≥ 10^9 < 2^30 −1,
the decomposition array will contain at most 29 elements. For each query [left,right], we can directly traverse the corresponding elements
in the decomposition array and compute the answer, with the time complexity of a single query being O(logn).
Complexity Analysis
Let q be the length of the array queries.

Time complexity: O(q×logn)
For each query, we need to traverse the binary bits of n.
Space complexity: O(logn)
This is the space required for the binary decomposition of n.
 */