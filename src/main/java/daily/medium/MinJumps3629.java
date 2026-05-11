package daily.medium;

import java.util.*;

public class MinJumps3629 {
    public static void main(String[] args) {
        MinJumps3629 m = new MinJumps3629();
        System.out.println(m.minJumps(new int[]{1,2,4,6}));
    }

//    bfs; time: O(V+E), space: O(n)
    public int minJumps(int[] nums) {
        int n = nums.length;
        if(n == 1) return 0;

        int maxVal = 0;
        for(int num: nums) maxVal = Math.max(maxVal, num);

//        Step 1: Sieve to find the smallest prime factors
        int[] spf = new int[maxVal + 1];
        for(int i = 2; i <= maxVal; i++) {
            if(spf[i] == 0) {
                for(int j = i; j <= maxVal; j += i)
                    if(spf[j] == 0) spf[j] = i;
            }
        }

//        Step 2: Map primes to indices that are multiples of that prime
//        this acts as our "teleportation" graph
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int temp = nums[i];
            while(temp > 1) {
                int p = spf[temp];
                primeToIndices.computeIfAbsent(p, a -> new ArrayList<>())
                        .add(i);
                while(temp % p == 0) temp /= p;
            }
        }

//        Step 3: BFS
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        boolean[] visitedIndex = new boolean[n];
        boolean[] visitedPrime = new boolean[maxVal + 1];
        visitedIndex[0] = true;

        int steps = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int currIdx = queue.poll();
                if(currIdx == n -1) return steps;

//                Option 1: Adjacent Steps
                if(currIdx + 1 < n && !visitedIndex[currIdx + 1]) {
                    visitedIndex[currIdx + 1] = true;
                    queue.offer(currIdx + 1);
                }
                if(currIdx - 1 >= 0 && !visitedIndex[currIdx - 1]) {
                    visitedIndex[currIdx - 1] = true;
                    queue.offer(currIdx - 1);
                }

//                Option 2: Prime Teleportation
//                Only if nums[currIdx] is prime
                if(isPrime(nums[currIdx], spf)) {
                    int p = nums[currIdx];
                    if(!visitedPrime[p]) {
                        visitedPrime[p] = true;
                        for(int nextIdx : primeToIndices.get(p)) {
                            if(!visitedIndex[nextIdx]) {
                                visitedIndex[nextIdx] = true;
                                queue.offer(nextIdx);
                            }
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private boolean isPrime(int n, int[] spf) {
        if(n < 2) return false;
        return spf[n] == n;
    }

}

/*
You are given an integer array nums of length n.
You start at index 0, and your goal is to reach index n - 1.
From any index i, you may perform one of the following operations:
Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
Return the minimum number of jumps required to reach index n - 1.
Example 1:
Input: nums = [1,2,4,6]
Output: 2
Explanation:
One optimal sequence of jumps is:
Start at index i = 0. Take an adjacent step to index 1.
At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
Thus, the answer is 2.
Example 2:
Input: nums = [2,3,4,7,9]
Output: 2
Explanation:
One optimal sequence of jumps is:
Start at index i = 0. Take an adjacent step to index i = 1.
At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is divisible by 3.
Thus, the answer is 2.
Example 3:
Input: nums = [4,6,5,8]
Output: 3
Explanation:
Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.
Constraints:
1 <= n == nums.length <= 10^5
1 <= nums[i] <= 10^6
 */


/*
To prove a number is prime, you only need to prove that no "small" factor exists. If you've checked every number from 2
up to $\sqrt{n}$ and found no divisors, it is physically impossible for a "large" factor to exist, because it would have
nothing to pair with except a "small" factor: which you've already ruled out.
When you remove all multiples of 2 and 3, the only numbers left "standing" in the entire number line are those at positions
$6k-1$ and $6k+1$.Why $6k \pm 1$ isn't always prime. You mentioned 9. Let's look at $k=4$:$6(4) - 1 = 23$ (Prime)$6(4) + 1 = 25$ (Not Prime, it's $5 \times 5$)
$6k$: Divisible by 6 (and 2 and 3).
$6k + 1$: Potential Prime.
$6k + 2$: Divisible by 2.
$6k + 3$: Divisible by 3.
$6k + 4$: Divisible by 2.
$6k + 5$: Potential Prime. (Also written as $6k - 1$).

*/

/*
private boolean isPrime(int n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n % 2 == 0 || n % 3 == 0) return false;
        for(int i = 5; i * i <= n; i += 6) {
            if(n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }
 */

/*
nums = {2,10,10,9,9,11,2,77,3,77}  you need to check index + 1, index - 1 in bfs [ans - 3, not 4]
nums = {5,2,20,1,15} you need the primeToIndices, temp while loop calculation, since you should be able to
the prime factor index from any prime. but the spf only ensures that the smallest prime is captured but not all.
prime -> index :: 5 -> 0; 2 -> 1,2; 3 -> 4. [ans -> 1, not 4]
 */