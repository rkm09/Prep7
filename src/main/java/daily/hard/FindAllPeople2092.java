package daily.hard;

import java.util.*;

public class FindAllPeople2092 {
    public static void main(String[] args) {
        FindAllPeople2092 f = new FindAllPeople2092();
        int[][] meetings = {{1,2,5},{2,3,8},{1,5,8}};
        System.out.println(f.findAllPeople(6,meetings,1));
    }

//    time: O(M.logM + N), space: O(M + N)
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
//        sort meetings in increasing order of time
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
//        group meetings in increasing order of time
        Map<Integer, List<int[]>> sameTimeMeetings = new TreeMap<>();
        for(int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            sameTimeMeetings.computeIfAbsent(t, a -> new ArrayList<>())
                    .add(new int[]{x, y});
        }

//        create graph
        UnionFind dsu = new UnionFind(n);
        dsu.union(firstPerson, 0);

//        process in increasing order of time
        for(int t : sameTimeMeetings.keySet()) {
//            unite the two people taking part in a meeting at time t
            for(int[] meeting : sameTimeMeetings.get(t)) {
                int x = meeting[0], y = meeting[1];
                dsu.union(x, y);
            }

//            if one of them knows the secret at time t, both will know the secret
//            conversely, if none of them know the secret then reset their connection with each other
//            this would ensure we do maintain knowing based on instant
            for(int[] meeting : sameTimeMeetings.get(t)) {
                int x = meeting[0], y = meeting[1];
                if(!dsu.isConnected(x, 0)) {
                    dsu.reset(x);
                    dsu.reset(y);
                }
            }
        }

//        finally, all those connected to 0 will know the secret
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
            if(dsu.isConnected(0, i))
                result.add(i);
        return result;
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;
    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0 ; i < n ; i++)
            parent[i] = i;
    }

//    use path compression to find parent of node x
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
                rank[px] += 1;
            }
        }
    }

//    check if the two nodes are connected
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

//    reset to initial properties of node x
    public void reset(int i) {
        parent[i] = i;
        rank[i] = 0;
    }
}

/*
You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
Example 1:
Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
Example 2:
Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.
Example 3:
Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
Constraints:
2 <= n <= 105
1 <= meetings.length <= 105
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 105
1 <= firstPerson <= n - 1
 */