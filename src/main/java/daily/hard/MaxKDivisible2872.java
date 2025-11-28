package daily.hard;

import java.util.*;

public class MaxKDivisible2872 {
    public static void main(String[] args) {
        int[][] edges = {{0,2},{1,2},{1,3},{2,4}};
        int[] values = {1,8,1,4,4};
        System.out.println(maxKDivisibleComponents(5,edges,values,6));
    }

//     bfs; time: O(n), space: O(n)
    public static int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
//        base case: if there are less than two nodes return 1
        if(n < 2) return 1;
        int componentCount = 0;
//        step 1: build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            graph.computeIfAbsent(node1, s -> new HashSet<>()).add(node2);
            graph.computeIfAbsent(node2, s -> new HashSet<>()).add(node1);
        }
//        convert values to long to prevent overflow
        long[] longValues = new long[values.length];
        for(int i = 0 ; i < values.length ; i++)
            longValues[i] = values[i];
//        step 2: initialize the bfs queue with leaf nodes
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++) {
            if(graph.get(i).size() == 1)
                queue.offer(i);
        }
//        step 3: process nodes in bfs order
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
//            find the neighbour node
            int neighbourNode = -1;
            if(!graph.get(currentNode).isEmpty())
                neighbourNode = graph.get(currentNode).iterator().next();
//            remove the edge between the current node and the neighbour node
            if(neighbourNode >= 0) {
                graph.get(currentNode).remove(neighbourNode);
                graph.get(neighbourNode).remove(currentNode);
            }
//            check the divisibility of the current node's value
            if(longValues[currentNode] % k == 0)
                componentCount++;
            else if(neighbourNode >= 0) {
//                add the current node's value to the neighbour node
                longValues[neighbourNode] += longValues[currentNode];
            }
//            if the neighbour becomes a leaf add it to the queue
            if(neighbourNode >= 0 && graph.get(neighbourNode).size() == 1)
                queue.offer(neighbourNode);
        }
        return componentCount;
    }
}

/*
There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.
A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.
Return the maximum number of components in any valid split.
Example 1:
Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
Output: 2
Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.
Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
Output: 3
Explanation: We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting split is valid because:
- The value of the component containing node 0 is values[0] = 3.
- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
It can be shown that no other valid split has more than 3 connected components.
Constraints:
1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
values.length == n
0 <= values[i] <= 109
1 <= k <= 109
Sum of values is divisible by k.
The input is generated such that edges represents a valid tree.
 */