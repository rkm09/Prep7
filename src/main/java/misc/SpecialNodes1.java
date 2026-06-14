package misc;

import java.util.*;

public class SpecialNodes1 {
    public static void main(String[] args) {
        int nodes = 7;
        int[] nodesFrom = {1,2,3,3,1,1};
        int[] nodesTo = {2,3,4,5,6,7};  // 0 0 0 1 1 1 1
        System.out.println(Arrays.toString(isSpecial(nodes, nodesFrom, nodesTo)));
    }

    public static int[] isSpecial(int nodes, int[] nodesFrom, int[] nodesTo) {
//       1. build node 1 based adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= nodes; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < nodesFrom.length; i++) {
            int u = nodesFrom[i];
            int v = nodesTo[i];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

//       2. 1st pass: find the farthest distance from an arbitrary starting node (1)
        int[] distFromStart = bfs(1, nodes, adjList);
        int nodeA = findFarthestNode(distFromStart, nodes);

//       3. 2nd pass: find distances from node A
        int[] distA = bfs(nodeA, nodes, adjList);
        int nodeB = findFarthestNode(distA, nodes);

//        diameter: maximum distance between endpoint A and endpoint B
        int diameter = distA[nodeB];

//       4. 3rd pass: find distances from node B
        int[] distB = bfs(nodeB, nodes, adjList);

//       5. build result array
        int[] result = new int[nodes];
        for (int i = 1; i <= nodes; i++) {
            result[i - 1] = (distA[i] == diameter || distB[i] == diameter) ? 1 : 0;
        }

        return result;
    }

//    bfs that returns an array of distances from startNode
    private static int[] bfs(int startNode, int nodes, List<List<Integer>> adjList) {
        int[] dist = new int[nodes + 1];
        Arrays.fill(dist, -1);
        Deque<Integer> queue  = new ArrayDeque<>();
        dist[startNode] = 0;
        queue.offer(startNode);

        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            for (int neighbor : adjList.get(currNode)) {
                if(dist[neighbor] == -1) {
                    dist[neighbor] = dist[currNode] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return dist;
    }

//    helper to extract the node at the maximum distance
    private static int findFarthestNode(int[] dist, int nodes) {
        int maxDist = -1, farthestNode = -1;
        for (int i = 1; i <= nodes; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                farthestNode = i;
            }
        }

        return farthestNode;
    }


}

/*
Find all special nodes in a tree with tree_nodes nodes. A node is special if it is an endpoint of any diameter of the tree.
Return a binary array where the ith value is 1 if the ith node is special, otherwise 0.
Note: The diameter of a tree is the number of edges in the longest path of the tree
Example:
tree_nodes = 3
tree_from = [0,0]
tree_to = [1,2]
This tree has one diameter, which is the path between nodes 1 and 3. The length of this diameter is 2 edges. The endpoints of this diameter are nodes 1 and 3, making them special nodes. Node 2 is not special.
Therefore, the result is [1,0,1], indicating nodes 1 and 3 are special, while node 2 is not.
Function Description:
Complete the function isSpecial in the editor with the following parameter(s):
int tree_nodes: the number of nodes in the tree
Int tree_from[tree_nodes - 1]: tree_from[I] is connected to tree_to[I]
Int tree_to[tree_nodes - 1]

Return:
int[tree_nodes]: the 4th value of the array is 1 if the nodes special, or 0 otherwise

Constraints:
1 <= tree_nodes <= 10^5
1 <= tree_from[I], tree_to[I] <= tree_nodes

STDIN.          FUNCTION
———-     ———————
7 6.       -> tree_nodes = 7 and tree_nodes - 1= 6
1 2.       -> tree_from = [1,2,3,3,1,1]
2 3             tree_To = [2,3,4,5,6,7]
3 4
3 5
1 6
1 7
Sample Output:
0 0 0 1 1 1 1

STDIN           FUNCTION
2   1    -> tree_nodes = 2 and tree_nodes - 1 = 1
2   1     -> tree_from = [2], tree_to = [1]

Sample Output:
1 1
 */
