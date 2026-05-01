package daily.medium;

public class MaxRotate396 {
    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[]{4,3,2,6}));
    }

//    dp; time: O(n), space: O(1)
    public static int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long f0 = 0, sum = 0;
//        calculate the total sum and initial f0
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }

        long maxVal = f0, currentF = f0;
//        Iterate from the last element to the first element
//        each step represents a transition from F(k - 1) to F(k)
        for(int i = n - 1; i > 0; i--) {
//            recurrent relation: F(k) = F(k - 1) + S - n * nums[last_element_of_previous_rotation]
            currentF = currentF + sum - (long) n * nums[i];
            maxVal = Math.max(maxVal, currentF);
        }

        return (int) maxVal;
    }
}

/*
You are given an integer array nums of length n.
Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:
F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
Return the maximum value of F(0), F(1), ..., F(n-1).
The test cases are generated so that the answer fits in a 32-bit integer.
Example 1:
Input: nums = [4,3,2,6]
Output: 26
Explanation:
F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
Example 2:
Input: nums = [100]
Output: 0
Constraints:
n == nums.length
1 <= n <= 10^5
-100 <= nums[i] <= 100
 */

/*
Why the Invariant is the Key:
In many sliding window or rotation problems, we are often tempted to look only at what is changing.
However, looking at what stays the same (the invariant) allows you to:
Normalize the transition: You realize every element in the array is shifted by the same "weight" (1), which is why the total sum $S$ appears in the delta.
Isolate the anomaly: Since every element increased its multiplier by 1, the only "error" in the system is the element that wrapped around from $(n-1)$ to $0$.
The invariant $S$ helps you see that you "over-counted" that specific element by $n$.
Recurrence = Invariant + delta
F(k) = F(k - 1) + S - nums[n - k] * n
'S' is the invariant here, delta - state transition
essentially sum to AP: n * (a + l) / 2 = n * (0 + n - 1) / 2 => O(n^2) => intermediate results may overflow
int: 2 * 10^9, long: 2 * 10^18
Why the "Difference" method is superior here
In this specific rotation problem, a standard DP state like dp[i] = max rotation for prefix i doesn't work
because the rotation affects every element's multiplier simultaneously.
By looking at the difference between terms, you essentially discovered a Differential DP (or iterative update).
Instead of asking "How do I build this from a smaller array?", you asked "How does the total energy of the system change when I shift it?".
 */