package daily.easy;

import lombok.val;

import java.util.HashMap;
import java.util.Map;

public class MaxFreq3005 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1,4};
        System.out.println(maxFrequencyElements(nums));
    }

//    one pass; map; time: O(n), space: O(n)
    public static int maxFrequencyElements(int[] nums) {
        int totalElements = 0, maxFrequency = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            int currentFrequency = freqMap.get(num);
//            if we discover higher element, update the maxFrequency and reset totalElements
            if(currentFrequency > maxFrequency) {
                maxFrequency = currentFrequency;
                totalElements = currentFrequency;
            } else if(currentFrequency == maxFrequency) {
//                if it is equal to the highest frequency, add the element count to the running total
                totalElements += maxFrequency;
            }
        }
        return totalElements;
    }

//    def; two pass; map; time: O(n), space: O(n)
    public static int maxFrequencyElements1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0, count = 0;
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            max = Math.max(freq.get(num), max);
        }
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if(entry.getValue() == max)
                count += max;
        }
        return count;
    }
}

/*
You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
The frequency of an element is the number of occurrences of that element in the array.
Example 1:
Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.
Example 2:
Input: nums = [1,2,3,4,5]
Output: 5
Explanation: All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
 */