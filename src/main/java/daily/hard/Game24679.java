package daily.hard;

import java.util.ArrayList;
import java.util.List;

public class Game24679 {
    public static void main(String[] args) {
        Game24679 g = new Game24679();
        int[] cards = {4,1,8,7};
        System.out.println(g.judgePoint24(cards));
    }


//    backtracking; time: O(N^3⋅3^N−1⋅N!⋅(N−1)!), space: O(N^2)
    public boolean judgePoint24(int[] cards) {
        List<Double> cardList = new ArrayList<>();
        for(int card : cards)
            cardList.add((double) card);
        return checkIfResultReached(cardList);
    }

//     check if we can reach result 24 by using the current list
    public boolean checkIfResultReached(List<Double> cardList) {
        int n = cardList.size();
//        base case: we only have one number left, check if it is approximately 24
        if(n == 1)
            return Math.abs(cardList.getFirst() - 24) <= 0.1;
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
//                create a new list with the remaining numbers and the new list
                List<Double> newList = new ArrayList<>();
                for(int k = 0 ; k < n ; k++) {
                    if(k != i && k != j)
                        newList.add(cardList.get(k));
                }
//                for any two numbers in our list, we perform every operation one by one
                for(double res : generatePossibleResults(cardList.get(i), cardList.get(j))) {
//                    push the new result in the list
                    newList.add(res);
//                    check if by adding the new result we can obtain 24
                    if(checkIfResultReached(newList))
                        return true;
//                    backtrack : remove the result from the list
                    newList.remove(res);
                }
            }
        }
        return false;
    }

    private List<Double> generatePossibleResults(double a, double b) {
        List<Double> resList = new ArrayList<>();
        resList.add(a + b);
        resList.add(a - b);
        resList.add(b - a);
        resList.add(a * b);
        if(a != 0)
            resList.add(b / a);
        if(b != 0)
            resList.add(a / b);
        return resList;
    }
}

/*
You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
You are restricted with the following rules:
The division operator '/' represents real division, not integer division.
For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
You cannot concatenate numbers together
For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
Return true if you can get such expression that evaluates to 24, and false otherwise.
Example 1:
Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: cards = [1,2,1,2]
Output: false
Constraints:
cards.length == 4
1 <= cards[i] <= 9
 */

/*
Complexity Analysis
If N is the number of cards in the input array.
Time complexity: O(N^3⋅3^N−1⋅N!⋅(N−1)!).
In a time-sensitive interview setting, it may be difficult to provide an exact analysis for this problem. A tighter upper bound likely exists, but the current analysis provides a reasonable upper bound for the time complexity.
In each recursive call, if we have k elements in our array, we choose k⋅(k−1)/2 pairs of numbers and for each pair, we perform 6 operations and for each operation, we make a recursive call.
With each recursive call, the array size decreases by 1. Thus, the total number of recursive calls is:
N(N−1)(3)⋅(N−1)(N−2)(3)⋅...⋅(2)(1)(3) =N!⋅(N−1)!⋅3^N−1
As the number of nodes more than doubles at every level, the total number of nodes can be approximated by the number of nodes in the last level, N!⋅(N−1)!⋅3^N−1.
And the maximum time required for any node will be O(outer_two_for_loops)⋅O(array_copy + inner_for_loop)=O(N(N−1)/2)⋅O(N+6)=O(N^3)
So, we can say the time complexity is O(N^3⋅3^N−1⋅N!⋅(N−1)!).

Space complexity: O(N^2).
At one time, we make at most N recursive calls, and the recursive stack will take O(N) space.
With each recursive call, we create a new array, and the array size decreases by 1 with each call.
Thus, space used by new arrays will be O((N−1)+(N−2)+(N−3)+....+2+1)=O(N^2).
 */