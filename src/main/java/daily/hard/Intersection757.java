package daily.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersection757 {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{3,7},{8,9}};
        System.out.println(intersectionSizeTwo(intervals));
    }

//    greedy; time: O(n^2), space: O(n)
    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> {
            if(a[1] == b[1])
                return b[0] - a[0];
            return a[1] - b[1];
        });

        List<Integer> li = new ArrayList<>();
        int res = 0;
        for(int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            int cnt = 0;
            for(int i = li.size() - 1 ; i >= 0 ; i--) {
                if(li.get(i) >= start && li.get(i) <= end)
                    cnt++;
                if(cnt == 2) break;
            }
            if(cnt == 0) {
                li.add(end-1);
                li.add(end);
                res += 2;
            } else if(cnt == 1) {
                li.add(end);
                res += 1;
            }
        }

        return res;
    }
}

/*
You are given a 2D integer array intervals where intervals[i] = [starti, endi] represents all the integers from starti to endi inclusively.
A containing set is an array nums where each interval from intervals has at least two integers in nums.
For example, if intervals = [[1,3], [3,7], [8,9]], then [1,2,4,7,8,9] and [2,3,4,8,9] are containing sets.
Return the minimum possible size of a containing set.
Example 1:
Input: intervals = [[1,3],[3,7],[8,9]]
Output: 5
Explanation: let nums = [2, 3, 4, 8, 9].
It can be shown that there cannot be any containing array of size 4.
Example 2:
Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
Output: 3
Explanation: let nums = [2, 3, 4].
It can be shown that there cannot be any containing array of size 2.
Example 3:
Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
Output: 5
Explanation: let nums = [1, 2, 3, 4, 5].
It can be shown that there cannot be any containing array of size 4.
Constraints:
1 <= intervals.length <= 3000
intervals[i].length == 2
0 <= starti < endi <= 108
 */

/*
Greedy:
Instead of picking numbers randomly, we can use a greedy approach by always selecting the largest possible numbers from each interval. This way, we maximize the chance that these numbers will also cover future intervals.
The trick is to sort the intervals by their end point, and then for each interval, check how many numbers from our current set already fall into it. If we're missing numbers, we add the largest possible numbers from that interval - specifically end-1 and end.
Why these particular numbers? Because they're the most likely to be useful for future intervals that end at or beyond our current position. It's like leaving the door open for the next guest - you want to position yourself in the most convenient spot for what's coming next.
sorting: We sort the intervals primarily by their end point (a[1] and b[1]) in ascending order
If two intervals have the same end point, we sort them by start point in descending order (b[0] - a[0])
Sorting by end point allows us to process intervals from left to right
When ends are equal, putting intervals with larger starts first ensures we handle tighter intervals before wider ones
This ordering maximizes our chances of reusing numbers across multiple intervals
We can break early once we find two numbers that cover the interval
This avoids unnecessary checks on smaller numbers that are less likely to help
 */