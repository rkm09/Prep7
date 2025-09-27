package daily.easy;

public class LargestTriangleArea812 {
    public static void main(String[] args) {
        LargestTriangleArea812 l = new LargestTriangleArea812();
        int[][] points = {{0,0},{0,1},{1,0},{0,2},{2,0}};
        System.out.println(l.largestTriangleArea(points));
    }

//    brute force; time: O(n^3), space: O(1)
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double ans = 0.0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j ++) {
                for(int k = j + 1 ; k < n ; k++) {
                   ans = Math.max(ans, area(points[i],points[j],points[k]));
                }
            }
        }
        return ans;
    }

//    using shoelace method
    private double area(int[] p, int[] q, int[] r) {
        return 0.5 * Math.abs(p[0] * q[1] - p[1] * q[0] + q[0] * r[1] - q[1] * r[0] + r[0] * p[1] - r[1] * p[0]);
    }

}

/*
Given an array of points on the X-Y plane points where points[i] = [xi, yi], return the area of the largest triangle that can be formed by any three different points. Answers within 10-5 of the actual answer will be accepted.
Example 1:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2.00000
Explanation: The five points are shown in the above figure. The red triangle is the largest.
Example 2:
Input: points = [[1,0],[0,0],[0,1]]
Output: 0.50000

Constraints:
3 <= points.length <= 50
-50 <= xi, yi <= 50
All the given points are unique.
 */