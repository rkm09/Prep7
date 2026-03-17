package daily.hard;

import java.util.*;

public class FancySequence1622 {
    public static void main(String[] args) {
        Fancy f = new Fancy();
        f.append(2);
        f.addAll(3);
        f.append(7); f.multAll(2); f.getIndex(0);
        System.out.println(f.getIndex(0));
    }
}



/* TLE (not applicable)*/
class Fancy {
    private static final int MOD = (int) 1e9 + 7;
    List<Integer> li;
    List<int[]> ops;
    int last = -1;
    public Fancy() {
        li = new ArrayList<>();
        ops = new ArrayList<>();
    }

    public void append(int val) {
        li.add(val);
        last = li.size() - 1;
    }

    public void addAll(int inc) {
        if(last == -1) return;
        ops.add(new int[] {0,inc,last});
    }

    public void multAll(int m) {
        if(last == -1) return;
        ops.add(new int[] {1,m,last});
    }

    public int getIndex(int idx) {
        if(idx > last) return -1;
        long value = (long) li.get(idx);
        for(int[] op : ops) {
            if(op[2] < idx) continue;
            if(op[0] == 0)
                value = (value + op[1]) % MOD;
            else
                value = (value * op[1]) % MOD;
        }
        return (int) value;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */


/*
Write an API that generates fancy sequences using the append, addAll, and multAll operations.
Implement the Fancy class:
Fancy() Initializes the object with an empty sequence.
void append(val) Appends an integer val to the end of the sequence.
void addAll(inc) Increments all existing values in the sequence by an integer inc.
void multAll(m) Multiplies all existing values in the sequence by an integer m.
int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
Example 1:
Input
["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
Output
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]
Explanation
Fancy fancy = new Fancy();
fancy.append(2);   // fancy sequence: [2]
fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
fancy.append(7);   // fancy sequence: [5, 7]
fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
fancy.getIndex(0); // return 10
fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
fancy.append(10);  // fancy sequence: [13, 17, 10]
fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
fancy.getIndex(0); // return 26
fancy.getIndex(1); // return 34
fancy.getIndex(2); // return 20
Constraints:
1 <= val, inc, m <= 100
0 <= idx <= 105
At most 105 calls total will be made to append, addAll, multAll, and getIndex.
 */
