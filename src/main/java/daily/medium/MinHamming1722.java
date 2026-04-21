package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class MinHamming1722 {
    public static void main(String[] args) {
        int[] source = {1,2,3,4};
        int[] target = {2,1,4,5};
        int[][] allowed = {{0,1},{2,3}};
        System.out.println(minimumHammingDistance(source, target, allowed));
    }

//    dsu + hashmap; time: O(n.alpha(n)), space: O(n)
    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind dsu = new UnionFind(n);
//        1. unify indices based on allowed swaps to find connected components
        for(int[] swap : allowedSwaps)
            dsu.union(swap[0], swap[1]);
//        2. group source values by their component root
//        map: rootIndex -> {value, frequency}
        Map<Integer, Map<Integer, Integer>> components = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int root = dsu.find(i);
            components.computeIfAbsent(root, k -> new HashMap<>())
                    .merge(source[i], 1, Integer::sum);
        }
//        3. calculate matches by checking target values against their component's pool
        int totalMatches = 0;
        for(int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> pool = components.get(root);
            int targetVal = target[i];
//            if the required target value exists in swappable pool, it's a match
            if(pool.containsKey(targetVal) && pool.get(targetVal) > 0) {
                totalMatches++;
                pool.merge(targetVal, -1, Integer::sum);
            }
        }

//        hamming distance is the number of positions that couldn't be matched
        return n - totalMatches;
    }

}

class UnionFind {
    private final int[] parent;
    private final int[] rank;
    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;
    }

//    use path compression to find the parent of node i
    public int find(int i) {
        if(parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

//    union by rank heuristic
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px != py) {
            if(rank[px] > rank[py])
                parent[py] = px;
            else if(rank[px] < rank[py])
                parent[px] = py;
            else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }
}

/*
You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source.
Note that you can swap elements at a specific pair of indices multiple times and in any order.
The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
Example 1:
Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
Output: 1
Explanation: source can be transformed the following way:
- Swap indices 0 and 1: source = [2,1,3,4]
- Swap indices 2 and 3: source = [2,1,4,3]
The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
Example 2:
Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
Output: 2
Explanation: There are no allowed swaps.
The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
Example 3:
Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
Output: 0
Constraints:
n == source.length == target.length
1 <= n <= 105
1 <= source[i], target[i] <= 105
0 <= allowedSwaps.length <= 105
allowedSwaps[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
 */

/*
Why this structure wins:
Lazy Initialization: computeIfAbsent ensures we only create a HashMap when a new component is encountered.
Atomic Updates: merge handles the "if exists increment/decrement" logic cleanly without the get/put ceremony.
Complexity: * Time: $O(n.alpha(n)), where $\alpha$ is the inverse Ackermann function from the DSU operations.
Space: O(n) to store the DSU and the frequency maps.
 */