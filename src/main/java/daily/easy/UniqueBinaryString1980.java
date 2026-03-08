package daily.easy;

public class UniqueBinaryString1980 {
    public static void main(String[] args) {
        String[] nums = {"01","10"};
        System.out.println(findDifferentBinaryString(nums));
    }

//    cantor's diagonal method; time: O(n), space: O(1)
    public static String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        int n = nums.length;
        for(int i = 0 ; i < n ; i++) {
//            look at the ith character of the ith string
            char currentChar = nums[i].charAt(i);
            sb.append(currentChar == '0' ? '1' : '0');
        }
        return sb.toString();
    }
}

/*
Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
Example 1:
Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:
Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:
Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
Constraints:
n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
 */

/*
The Strategy: Cantor's DiagonalInstead of checking all $2^n$ possibilities (which would be overkill),
we build our answer character by character:For the first character of our result, we look at the first character of
the first string and flip it.For the second character of our result, we look at the second character of the second
string and flip it.Repeat this for all $n$ strings.By the time we finish, our new string is guaranteed to be unique
because it differs from the $i$-th string at the $i$-th position.
 */