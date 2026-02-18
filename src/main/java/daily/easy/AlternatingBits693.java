package daily.easy;

public class AlternatingBits693 {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
    }

//    divide by 2; time:O(1), space: O(1)
    public static boolean hasAlternatingBits(int n) {
        int curr = n & 1;  // n % 2 (last bit)
        n >>= 1;  // n /= 2 (rest)
        while(n > 0) {
            if((n & 1) == curr) return false;
            curr = n & 1;
            n >>= 1;
        }
        return true;
    }

//    convert to string; time: O(1), space: O(1)
    public static boolean hasAlternatingBits1(int n) {
        String bits = Integer.toBinaryString(n);
        for(int i = 1 ; i < bits.length() ; i++) {
            if(bits.charAt(i - 1) == bits.charAt(i))
                return false;
        }
        return true;
    }
}

/*
Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
Example 1:
Input: n = 5
Output: true
Explanation: The binary representation of 5 is: 101
Example 2:
Input: n = 7
Output: false
Explanation: The binary representation of 7 is: 111.
Example 3:
Input: n = 11
Output: false
Explanation: The binary representation of 11 is: 1011.
Constraints:
1 <= n <= 231 - 1
 */
