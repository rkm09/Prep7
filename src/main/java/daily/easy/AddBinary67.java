package daily.easy;

import java.math.BigInteger;

public class AddBinary67 {
    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }

//    using BigInteger
    public static String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger carry, sum;
        BigInteger zero = new BigInteger("0", 2);
        while(y.compareTo(zero) != 0) {
            sum = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = sum;
            y = carry;
        }
//        note: you need to specify radix in toString()
        return x.toString(2);
    }
}

/*
Given two binary strings a and b, return their sum as a binary string.
Example 1:
Input: a = "11", b = "1"
Output: "100"
Example 2:
Input: a = "1010", b = "1011"
Output: "10101"
Constraints:
1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
 */