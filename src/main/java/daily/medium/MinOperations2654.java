package daily.medium;

public class MinOperations2654 {
    public static void main(String[] args) {
        int[] nums = {2,6,3,4};
        System.out.println(minOperations(nums));
    }

//    greedy; time: O(n^2logM), space: O(1)  [M - max value in nums, n - length]  [enumerating - n^2, gcd logM]
    public static int minOperations(int[] nums) {
        int n = nums.length;
        int num1 = 0, g = 0;
        for(int num : nums) {
            if(num == 1)
                num1++;
            g = gcd(g, num);
        }
        if(num1 > 0)
            return n - num1;
        if(g > 1)
            return -1;

        int minLen = n;
        for(int i = 0 ; i < n ; i++) {
            int currentGcd = 0;
            for(int j = i ; j < n ; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if(currentGcd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen + n - 2;
    }

    private static int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

/*
You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:
Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
The gcd of two integers is the greatest common divisor of the two integers.
Example 1:
Input: nums = [2,6,3,4]
Output: 4
Explanation: We can do the following operations:
- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
Example 2:
Input: nums = [2,10,6,14]
Output: -1
Explanation: It can be shown that it is impossible to make all the elements equal to 1.
Constraints:
2 <= nums.length <= 50
1 <= nums[i] <= 106
 */

/*
First, if the gcd of all numbers in nums is greater than 1, then it is impossible to make all elements in the array equal to 1.
Second, if there is already a 1 in nums, we can perform operations between that 1 and its adjacent non-1 numbers, requiring at most n−num
1 operations, where num1 represents the number of 1s in nums.
If neither of the above cases applies, we find the smallest interval where the gcd of all numbers in the interval equals 1. Suppose the length of this interval is minLen. Then, the number of operations required to obtain a 1 from these numbers is minLen−1. After obtaining a 1, the number of operations required to convert the remaining elements into 1s using this 1 is n−1. Therefore, the total number of operations needed is minLen+n−2.
We can enumerate all intervals in order of increasing interval length and calculate their gcd sequentially.
 */