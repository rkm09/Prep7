package daily.medium;

import java.util.ArrayList;
import java.util.List;

public class TwoEditWords2452 {
    public static void main(String[] args) {
        TwoEditWords2452 t = new TwoEditWords2452();
        System.out.println(t.twoEditWords(new String[]{"yes"}, new String[]{"not"}));
    }

//    make it nested class, so we prevent any memory leaks. the inner class does not need a reference to the outer
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

//    trie; time: O(D.L), space: O(D.L) [D - number of dictionary words, L - length of the words]
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        TrieNode root = new TrieNode();
//        step 1: build the Trie from the dictionary
        for(String word : dictionary)
            insert(root, word);

        List<String> result = new ArrayList<>();
        for(String query : queries) {
            if(canMatch(query, 0, root, 0))
                result.add(query);
        }

        return result;
    }

//    dfs
    private boolean canMatch(String word, int index, TrieNode curr, int edits) {
//        base case: if we've reached the end withing 2 edits it's a match
        if(index == word.length())
            return edits <= 2;

        int targetIdx = word.charAt(index) - 'a';

//        optimization: try the exact character match first (0 cost)
        if(curr.children[targetIdx] != null)
            if(canMatch(word, index + 1, curr.children[targetIdx], edits))
                return true;

//        if we still have edits available, try all other possible characters(1 cost each)
        if(edits < 2) {
            for(int i = 0; i < 26; i++) {
//                skip the target index as we have already checked it with edits + 0
                if(i != targetIdx && curr.children[i] != null)
                    if(canMatch(word, index + 1, curr.children[i], edits + 1))
                        return true;
            }
        }

        return false;
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()) {
            int idx = c - 'a';
            if(curr.children[idx] == null)
                curr.children[idx] = new TrieNode();

            curr = curr.children[idx];
        }
        curr.isWord = true;
    }

//    brute force(since the constraint size is small); time: O(m.n.q), space: O(1)
    public List<String> twoEditWords1(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        for(String query : queries) {
            for(String s : dictionary) {
                int dist = 0;
                for(int i = 0; i < query.length(); i++) {
                    if(query.charAt(i) != s.charAt(i))
                        dist++;
                }
                if(dist <= 2) {
                    ans.add(query); break;
                }
            }
        }

        return ans;
    }
}

/*
You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.
In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.
Example 1:
Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
Output: ["word","note","wood"]
Explanation:
- Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
- Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
- It would take more than 2 edits for "ants" to equal a dictionary word.
- "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
Thus, we return ["word","note","wood"].
Example 2:
Input: queries = ["yes"], dictionary = ["not"]
Output: []
Explanation:
Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.
Constraints:
1 <= queries.length, dictionary.length <= 100
n == queries[i].length == dictionary[j].length
1 <= n <= 100
All queries[i] and dictionary[j] are composed of lowercase English letters.
 */