package daily.medium;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeights3558 {
    private static final int MOD = (int) 1e9 + 7;
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{3,4},{3,5}};
        System.out.println(assignEdgeWeights(edges));
    }

//    dfs; time: O(n), space: O(n)
    public static int assignEdgeWeights(int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        int n = edges.length + 1;
        for (int i = 0; i <= n; i++)
            adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int maxDepth = dfs(1, 0, adjList);
        return qPow(2, maxDepth - 1);
    }

    private static int dfs(int node, int prev, List<List<Integer>> adjList) {
        int maxDepth = 0;
        for (int neighbor : adjList.get(node)) {
            if (neighbor == prev) continue;
            maxDepth = Math.max(maxDepth, dfs(neighbor, node, adjList) + 1);
        }

        return maxDepth;
    }

//    binary exponential(quick power)
    private static int qPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
//            is exponent is odd
            if((exp & 1) == 1)
                res = (res * base) % MOD;
            base = (base * base) % MOD;
//            exp/2
            exp >>= 1;
        }

        return (int) res;
    }
}

/*
There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.
Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
Select any one node x at the maximum depth. Return the number of ways to assign edge weights in the path from node 1 to x such that its total cost is odd.
Since the answer may be large, return it modulo 10^9 + 7.
Note: Ignore all edges not in the path from node 1 to x.
Example 1:
Input: edges = [[1,2]]
Output: 1
Explanation:
The path from Node 1 to Node 2 consists of one edge (1 → 2).
Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
Example 2:
Input: edges = [[1,2],[1,3],[3,4],[3,5]]
Output: 2
Explanation:
The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.
For example, the path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4).
Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.

Constraints:
2 <= n <= 10^5
edges.length == n - 1
edges[i] == [ui, vi]
1 <= ui, vi <= n
edges represents a valid tree.
 */

/*
Quick Power:
Instead of O(n) complexity of a^n. we go for exponentiation
e.g. a^n where n is even -> (a^2)^n/2
if n is odd -> a * a^n-1

The Combinatorics Logic:
Let's say the path has $L$ edges. Each edge can either be Odd (weight 1) or Even (weight 2).
For the total sum of the path to be odd, we must choose an odd number of edges to have the weight 1.
The remaining edges will automatically have the weight 2.The total number of ways to choose an odd number of items
out of $L$ items is a known mathematical identity:$$\binom{L}{1} + \binom{L}{3} + \binom{L}{5} + \dots = 2^{L-1}$$
Analogy: For any sequence of binary choices (like 1 or 2), exactly half of the total possible combinations will result in an odd sum, and the other half will result in an even sum.
Total combinations = $2^L$.
Half of them = $\frac{2^L}{2} = 2^{L-1}$.

Imagine you have a path with $L$ edges, and you need to assign them a weight of either 1 or 2.
You want the final total sum to be odd.Let’s assign weights to the edges one by one:For the 1st edge,
you have 2 choices (1 or 2).For the 2nd edge, you have 2 choices (1 or 2).For the 3rd edge, you have 2 choices
(1 or 2)....For the $(L-1)$-th edge, you have 2 choices (1 or 2).Up to this point, you have made independent
choices for $L-1$ edges. The number of ways to do this is:$$2 \times 2 \times 2 \times \dots \times 2 = 2^{L-1}$$\
Now, look at your running total sum of these $L-1$ edges. It is currently either Even or Odd.You are now standing at
the very last edge ($L$-th edge). To make the final total sum odd, your choice for this last edge is completely
forced upon you:If your sum so far is Even, the last edge must be 1 (Even + Odd = Odd). You have exactly 1 choice.
If your sum so far is Odd, the last edge must be 2 (Odd + Even = Odd). You have exactly 1 choice.No matter what
wild combinations you picked for the first $L-1$ edges, the last edge always has exactly 1 valid choice to save
the day and make the sum odd.
 */
