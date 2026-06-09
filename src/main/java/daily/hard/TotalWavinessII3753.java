package daily.hard;

import java.util.Arrays;

public class TotalWavinessII3753 {
    private static long[][][] memoCount;
    private static long[][][] memoSum;
    public static void main(String[] args) {
        System.out.println(totalWaviness(120,130));
    }

//    Digit DP;
    public static long totalWaviness(long num1, long num2) {
        return eval(num2) - eval(num1 - 1);
    }

    private static long eval(long num) {
        if(num < 100) return 0;

//        get length & decompress to a fast primitive int array
        int len = (int) Math.log10(num) + 1;
        int[] digits = new int[len];
        long temp = num;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (int) (temp % 10);
            temp /= 10;
        }

//        compressed 3d memoization; [index][prev_digit][prev_digit2]
//        10 is the placeholder for an invalid/ unplaced digit
        memoCount = new long[len + 1][11][11];
        memoSum = new long[len + 1][11][11];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 11; j++) {
                Arrays.fill(memoCount[i][j], -1);
                Arrays.fill(memoSum[i][j], - 1);
            }
        }

//        run the dfs, return {count, sum}
        long[] result = dfs(0, 10, 10, 1, 0, digits);
        return result[1];
    }

    private static long[] dfs(int idx, int prev, int prev2, int isLimit, int isStarted, int[] digits) {
//        base case: full number successfully constructed
        if (idx == digits.length)
            return new long[] {1, 0};

//        only read from cache if choices are unrestricted and number has started
        if (isLimit == 0 && isStarted == 1)
            if(memoCount[idx][prev][prev2] != -1)
                return new long[] {memoCount[idx][prev][prev2], memoSum[idx][prev][prev2]};


        long totalCount = 0, totalSum = 0;
//        retrieve upper bound digit
        int limit = (isLimit == 1) ? digits[idx] : 9;

        for (int d = 0; d <= limit; d++) {
            int nextLimit = (isLimit == 1 && d == limit) ? 1 : 0; // d == limit enough?
            int nextStarted = (isStarted == 1 || d > 0) ? 1 : 0;

            int wavinessContribution = 0;

//            core waviness verification
            if(isStarted == 1 && prev2 != 10) {
                if(prev > prev2 && prev > d) wavinessContribution = 1; // peak
                if(prev < prev2 && prev < d) wavinessContribution = 1;  // valley
            }

//            shift digits history forward
            int nextPrev = (nextStarted == 1) ? d : 10;
            int nextPrev2 = (nextStarted == 1) ? prev : 10;

            long[] nextStateResult = dfs(idx + 1, nextPrev, nextPrev2, nextLimit, nextStarted, digits);

            long suffixCount = nextStateResult[0];
            long suffixSum = nextStateResult[1];

            totalCount += suffixCount;
            totalSum += suffixSum + (wavinessContribution * suffixCount);
        }

//        only write to cache for stable, repeating universal states
        if (isLimit == 0 && isStarted == 1) {
            memoCount[idx][prev][prev2] = totalCount;
            memoSum[idx][prev][prev2] = totalSum;
        }

        return new long[] {totalCount, totalSum};
    }

//    TLE;
    public static long totalWavinessX(long num1, long num2) {
        int waviness = 0;
        for (long i = num1; i <= num2; i++) {
//            note: we are doing toCharArray, since we need to access data points multiple times,
//            and .charAt will be slightly slower since cpu needs to do boundary checks. but note that
//            in there is only one or two charAt needed it is better to keep it as string then, since space
//            occupied would be less and so the GC won't bleed that much. do case by case always.
            char[] num = String.valueOf(i).toCharArray();
            for (int j = 1; j < num.length - 1; j++) {
                boolean valley = (num[j] < num[j - 1]) && (num[j] < num[j + 1]);
                boolean peak = (num[j] > num[j - 1]) && (num[j] > num[j + 1]);
                if (peak || valley) waviness++;
            }
        }

        return waviness;
    }
}

/*
You are given two integers num1 and num2 representing an inclusive range [num1, num2].
The waviness of a number is defined as the total count of its peaks and valleys:
A digit is a peak if it is strictly greater than both of its immediate neighbors.
A digit is a valley if it is strictly less than both of its immediate neighbors.
The first and last digits of a number cannot be peaks or valleys.
Any number with fewer than 3 digits has a waviness of 0.
Return the total sum of waviness for all numbers in the range [num1, num2].
Example 1:
Input: num1 = 120, num2 = 130
Output: 3
Explanation:
In the range [120, 130]:
120: middle digit 2 is a peak, waviness = 1.
121: middle digit 2 is a peak, waviness = 1.
130: middle digit 3 is a peak, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.
Example 2:
Input: num1 = 198, num2 = 202
Output: 3
Explanation:
In the range [198, 202]:
198: middle digit 9 is a peak, waviness = 1.
201: middle digit 0 is a valley, waviness = 1.
202: middle digit 0 is a valley, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.
Example 3:
Input: num1 = 4848, num2 = 4848
Output: 2
Explanation:
Number 4848: the second digit 8 is a peak, and the third digit 4 is a valley, giving a waviness of 2.
Constraints:
1 <= num1 <= num2 <= 10^15
 */
