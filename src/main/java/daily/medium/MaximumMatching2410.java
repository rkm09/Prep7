package daily.medium;

import java.util.Arrays;

public class MaximumMatching2410 {
    public static void main(String[] args) {
        int[] players = {4,7,9};
        int[] trainers = {8,2,5,8};
        System.out.println(matchPlayersAndTrainers(players, trainers));
    }

//    sorting + two pointer + greedy; time: O(mlogm + nlogn), space: O(logm + logn)
    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int m = players.length, n = trainers.length;
        int count = 0, i = 0, j = 0;
        while(i < m && j < n) {
            if(players[i] <= trainers[j]) {
                count++;
                i++; j++;
            } else {
                j++;
            }
        }
        return count;
    }
}

/*
You are given a 0-indexed integer array players, where players[i] represents the ability of the ith player. You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth trainer.
The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's training capacity. Additionally, the ith player can be matched with at most one trainer, and the jth trainer can be matched with at most one player.
Return the maximum number of matchings between players and trainers that satisfy these conditions.
Example 1:
Input: players = [4,7,9], trainers = [8,2,5,8]
Output: 2
Explanation:
One of the ways we can form two matchings is as follows:
- players[0] can be matched with trainers[0] since 4 <= 8.
- players[1] can be matched with trainers[3] since 7 <= 8.
It can be proven that 2 is the maximum number of matchings that can be formed.
Example 2:
Input: players = [1,1,1], trainers = [10]
Output: 1
Explanation:
The trainer can be matched with any of the 3 players.
Each player can only be matched with one trainer, so the maximum answer is 1.

Constraints:
1 <= players.length, trainers.length <= 105
1 <= players[i], trainers[j] <= 109
 */


/*
To match the maximum number of athletes, we can follow a greedy strategy: sort both athletes and trainers in increasing order of ability, and for each athlete, assign the trainer with the smallest possible ability who can still match that athlete. This ensures that stronger trainers are preserved for athletes who need them.
 */