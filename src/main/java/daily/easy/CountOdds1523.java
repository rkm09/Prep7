package daily.easy;

public class CountOdds1523 {
    public static void main(String[] args) {
        System.out.println(countOdds(3,7));
    }

//    maths; odds up till high - odds before low
    public static int countOdds(int low, int high) {
        return (high + 1) / 2 - (low) / 2;
    }

    public static int countOdds1(int low, int high) {
        if(low % 2 != 0 && high % 2 != 0)
            return (high - low) / 2 + 1;
        else
            return (high - low) / 2;
    }

//    def; TLE
    public static int countOddsX(int low, int high) {
        int count = 0;
        for(int i = low ; i <= high ; i++) {
            if(i % 2 != 0) count++;
        }
        return count;
    }
}

/*
Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
Example 1:
Input: low = 3, high = 7
Output: 3
Explanation: The odd numbers between 3 and 7 are [3,5,7].
Example 2:
Input: low = 8, high = 10
Output: 1
Explanation: The odd numbers between 8 and 10 are [9].
Constraints:
0 <= low <= high <= 10^9
 */