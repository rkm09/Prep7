package daily.easy;

import java.util.Arrays;

public class SumZero1304 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumZero(5)));
    }

//    time: O(n), space: O(1)
    public static int[] sumZero(int n) {
        int[] res = new int[n];
        int idx = 0;
        for(int i = 1 ; i <= n / 2 ; i++) {
            res[idx++] = i;
            res[idx++] = -i;
        }
        if(n % 2 == 1) {
            res[idx] = 0;
        }
        return res;
    }
}

/*
Given an integer n, return any array containing n unique integers such that they add up to 0.
Example 1:
Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:
Input: n = 3
Output: [-1,0,1]
Example 3:
Input: n = 1
Output: [0]
Constraints:
1 <= n <= 1000
 */