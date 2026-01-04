package daily.medium;

public class SumOfFourDivisors1390 {
    public static void main(String[] args) {
        int[] nums = {21,4,7};
        System.out.println(sumFourDivisors(nums));
    }

    public static int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        for(int num : nums) {
            int foundDivisor = 0;
            for(int divisor = 2 ; divisor * divisor <= num ; divisor++) {
                if(num % divisor == 0) {
                    if(foundDivisor == 0)
                        foundDivisor = divisor;
                    else {
                        foundDivisor = 0; //if more than one found break
                        break;
                    }
                }
            }
            if(foundDivisor != 0 && foundDivisor != num / foundDivisor)
                totalSum += 1 + num + foundDivisor + num / foundDivisor;
        }
        return totalSum;
    }
}

/*
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.
Example 1:
Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
Example 2:
Input: nums = [21,21]
Output: 64
Example 3:
Input: nums = [1,2,3,4,5]
Output: 0
Constraints:
1 <= nums.length <= 104
1 <= nums[i] <= 105
 */

/*
The key insight is understanding the divisor structure of numbers.
Any number n>1 has at least two divisors: 1 and n itself. For a number to have exactly 4 divisors, there must be exactly
one additional pair of divisors.
For a number n with divisors d where d∣n (d divides n):
We always have divisors: 1 and n
If we find a divisor d where 2≤d≤n, then n/d is also a divisor
The critical observation is:
If d=n/d(i.e., d= sqrt.n), then n is a perfect square and has an odd number of divisors
If we find exactly one divisor d in range [2, sqrt.n] where d = n/d, then n has exactly 4 divisors: {1,d,d/n,n}
 */