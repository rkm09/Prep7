package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class CanReach1306 {
    public static void main(String[] args) {
        CanReach1306 c = new CanReach1306();
        System.out.println(c.canReach(new int[]{3,0,2,1,2}, 2));
    }

//    dfs; time: O(n), space: O(n)
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }

    private boolean dfs(int[] arr, int index, boolean[] visited) {
        if(index < 0 || index >= arr.length) return false;
        if(arr[index] == 0) return true;
        if(!visited[index]) {
            visited[index] = true;
            return dfs(arr, index + arr[index], visited) || dfs(arr, index - arr[index], visited);
        }
        return false;
    }

//    backtracking dfs; time: O(n), space: O(1) [but slower due to backtracking, unless mutating is allowed]
    public boolean canReach1(int[] arr, int start) {
        return dfs(arr, start);
    }

    private boolean dfs(int[] arr, int index) {
        if(index < 0 || index >= arr.length) return false;
        if(arr[index] == 0) return true;
        if(arr[index] < 0) return false; // this is how we track visited
        int originalValue = arr[index];
        arr[index] = -1 * originalValue;

        boolean found = dfs(arr, index + arr[index]) || dfs(arr, index - arr[index]);
//        backtrack and revert once done; note: -0 no need to bother, since 0 is already the winning case
        arr[index] = originalValue;

        return found;
    }

    public boolean canReach2(int[] arr, int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[arr.length];
        stack.push(start);

        while(!stack.isEmpty()) {
            int index = stack.pop();
//            base case: found
            if(arr[index] == 0) return true;

            if(!visited[index]) {
                visited[index] = true;
//            calculate next moves
                int left = index - arr[index];
                int right = index + arr[index];
//            push valid moves onto the stack
                if (left >= 0 && !visited[left])
                    stack.push(left);
                if (right < arr.length && !visited[right])
                    stack.push(right);
            }
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