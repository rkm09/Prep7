package daily.medium;

public class CanReach1306 {
    public static void main(String[] args) {
        CanReach1306 c = new CanReach1306();
        System.out.println(c.canReach(new int[]{3,0,2,1,2}, 2));
    }

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }

    private boolean dfs(int[] arr, int index, boolean[] visited) {
        if(index < 0 || index >= arr.length) return false;
        if(arr[index] == 0) return true;
        if(!visited[index]) {
            visited[index] = true;
            boolean left = dfs(arr, index - arr[index], visited);
            boolean right = dfs(arr, index + arr[index], visited);
            return left || right;
        }
        return false;
    }
}

/*
Given an array of non-negative integers arr, you are initially positioned at start index of the array.
When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
Notice that you can not jump outside the array at any time.
Example 1:
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3
Example 2:
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3
Example 3:
Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
Constraints:
1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
 */