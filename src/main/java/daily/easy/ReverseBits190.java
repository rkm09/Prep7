package daily.easy;

public class ReverseBits190 {
    public static void main(String[] args) {
        System.out.println(reverseBits(43261596));
    }

    public static int reverseBits(int n) {
//        treat n as an unsigned value
        n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16); // 2 byte swap
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8); // 1 byte swap
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4); // 4 bits swap
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2); // 2 bits swap
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1); // 1 bit swap
        return n;
    }
}

/*
Reverse bits of a given 32 bits signed integer.
Example 1:
Input: n = 43261596
Output: 964176192
Explanation:
Integer	Binary
43261596	00000010100101000001111010011100
964176192	00111001011110000010100101000000
Example 2:
Input: n = 2147483644
Output: 1073741822
Explanation:
Integer	Binary
2147483644	01111111111111111111111111111100
1073741822	00111111111111111111111111111110
Constraints:
0 <= n <= 231 - 2
n is even.
Follow up: If this function is called many times, how would you optimize it?
 */


/*
This code is a brilliant example of a Divide and Conquer algorithm applied to bit manipulation.
Instead of reversing the 32 bits one by one (which would take 32 steps), it swaps progressively smaller "chunks" of bits.
This reduces the complexity from $O(n)$ to $O(\log n)$. For a 32-bit integer, $\log_2(32) = 5$, which is why there are exactly 5 lines of logic.1.

The Logic: "The Big Swap"Think of the 32 bits like a deck of cards.
Line 1: Swaps the left half (16 bits) with the right half (16 bits).
Line 2: Within those halves, it swaps the left 8 bits with the right 8 bits.
Line 3: Within those bytes, it swaps the left 4 bits with the right 4 bits....and so on, until every single bit has been flipped to its mirror position.
2. Breakdown of a Single Line
Let's look at the first operation:n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);The Mask (& 0xffff0000):
This "zeros out" the bottom 16 bits and keeps only the top 16 bits.
The Shift (>>> 16): This moves those top 16 bits all the way to the right.
The Second Mask (& 0x0000ffff): This keeps only the bottom 16 bits.
The Second Shift (<< 16): This moves those bottom 16 bits all the way to the left.
The Combine (|): The OR operator stitches these two halves back together in their new, swapped positions.

3. Understanding the "Magic" Hex MasksThe masks might look like random gibberish, but they are actually very specific repeating patterns in binary:
Hex MaskBinary PatternWhat it targets
0xffff00001111...0000...The top 16-bit block
0xff00ff0011111111 00000000...Every other 8-bit block (bytes)
0xf0f0f0f01111 0000 1111 0000...Every other 4-bit block (nibbles)
0xcccccccc1100 1100 1100 1100...Every other 2-bit pair
0xaaaaaaaa1010 1010 1010 1010...Every other single bit

4. Why use >>> instead of >>?
In Java, >> is a signed shift (it preserves the positive/negative sign bit), while >>> is an unsigned shift (it fills the empty spots with zeros).
When reversing bits, you don't care about the numerical "sign";
you just want the raw bits to move, so >>> is essential to avoid accidentally filling the left side with 1s.

Why is this better than a loop?
A standard for loop would require 32 iterations and multiple comparisons.
This method uses exactly 15 operations (5 lines $\times$ 3 operations per line: two masks, two shifts, and one OR).
It is significantly faster and is the preferred way to handle bit-level transformations in high-performance systems or graphics drivers.
 */