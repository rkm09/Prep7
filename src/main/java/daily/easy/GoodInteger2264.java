package daily.easy;

public class GoodInteger2264 {
    public static void main(String[] args) {
        String num = "6777133339";
        System.out.println(largestGoodInteger(num));
    }

//    def; two pointer; time: O(n), space: O(1)
    public static String largestGoodInteger(String num) {
        int start = 0, end = 2, maxNum = -1;
        int n = num.length();
        while(end < n) {
            int l = num.charAt(start) - '0';
            int r = num.charAt(end) - '0';
            if(l == r) {
                if(num.charAt(start + 1) - '0' == l) {
                    String currStr = l + "" + l + l;
                    maxNum = Math.max(maxNum, Integer.parseInt(currStr));
                }
            }
            start++; end++;
        }
        if(maxNum != -1) {
            if(maxNum == 0)
                return "000";
            return String.valueOf(maxNum);
        }
        return "";
    }
}

/*
You are given a string num representing a large integer. An integer is good if it meets the following conditions:
It is a substring of num with length 3.
It consists of only one unique digit.
Return the maximum good integer as a string or an empty string "" if no such integer exists.
Note:
A substring is a contiguous sequence of characters within a string.
There may be leading zeroes in num or a good integer.
Example 1:
Input: num = "6777133339"
Output: "777"
Explanation: There are two distinct good integers: "777" and "333".
"777" is the largest, so we return "777".
Example 2:
Input: num = "2300019"
Output: "000"
Explanation: "000" is the only good integer.
Example 3:
Input: num = "42352338"
Output: ""
Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
Constraints:
3 <= num.length <= 1000
num only consists of digits.
 */