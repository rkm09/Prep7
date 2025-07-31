package daily.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubArrBitwiseOR895 {
    public static void main(String[] args) {
        int[] arr = {1,1,2};
        //System.out.println(generateSubLists1(arr));
        System.out.println(subarrayBitwiseORs(arr));
    }


    public static int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> current = new HashSet<>();
        for(int num : arr) {
            Set<Integer> nextCurrent = new HashSet<>();
            nextCurrent.add(num);
            for(int prevOR : current)
                nextCurrent.add(prevOR | num);
            current = nextCurrent;
            result.addAll(current);
        }
        return result.size();
    }


//    time: O(n^3)
    private static List<List<Integer>> generateSubLists(int[] arr) {
        int n = arr.length;
//        the final list that will store all the sub arrays
        List<List<Integer>> subArrays = new ArrayList<>();
//        the outer loop picks the starting element of the sub array
        for(int i = 0 ; i < n ; i++) {
//            the middle loop picks the ending element of the sub array
            for(int j = i ; j < n ; j++) {
//                this list will store the current sub arrays
                List<Integer> currSub = new ArrayList<>();
//                the inner loop iterates from the start index to the end index
//                adding elements to the current sub list
                for(int k = i ; k <= j ; k++)
                    currSub.add(arr[k]);
//                add fully formed current sub list to the final list
                subArrays.add(currSub);
            }
        }
        return subArrays;
    }

//    optimized; time: O(n^2) [still overall O(n^3) due to copying of list]
    private static List<List<Integer>> generateSubLists1(int[] arr) {
        int n = arr.length;
//        the final list that will store all the sub arrays
        List<List<Integer>> subArrays = new ArrayList<>();
//        the outer loop picks the starting element of the sub array
        for(int i = 0 ; i < n ; i++) {
            List<Integer> currSub = new ArrayList<>();
            for(int j = i ; j < n ; j++) {
                currSub.add(arr[j]);
                subArrays.add(new ArrayList<>(currSub));
            }
        }
        return subArrays;
    }
}

/*
Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty subarrays of arr.
The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.
A subarray is a contiguous non-empty sequence of elements within an array.
Example 1:
Input: arr = [0]
Output: 1
Explanation: There is only one possible result: 0.
Example 2:
Input: arr = [1,1,2]
Output: 3
Explanation: The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
Example 3:
Input: arr = [1,2,4]
Output: 6
Explanation: The possible results are 1, 2, 3, 4, 6, and 7.

Constraints:
1 <= arr.length <= 5 * 104
0 <= arr[i] <= 109
 */