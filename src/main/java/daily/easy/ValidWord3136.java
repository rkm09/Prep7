package daily.easy;

public class ValidWord3136 {
    public static void main(String[] args) {
        String word = "234Adas";
        System.out.println(isValid(word));
    }

//    one time traversal; time: O(n), space: O(1)
    public static boolean isValid(String word) {
        if(word.length() < 3) return false;
        boolean hasVowel = false, hasConsonant = false;
        for(char c : word.toCharArray()) {
            if(Character.isLetter(c)) {
                char ch = Character.toLowerCase(c);
                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                    hasVowel = true;
                else
                    hasConsonant = true;
            } else if(!Character.isDigit(c))
                return false;
        }
        return hasConsonant && hasVowel;
    }
}


/*
A word is considered valid if:
It contains a minimum of 3 characters.
It contains only digits (0-9), and English letters (uppercase and lowercase).
It includes at least one vowel.
It includes at least one consonant.
You are given a string word.
Return true if word is valid, otherwise, return false.
Notes:
'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
A consonant is an English letter that is not a vowel.
Example 1:
Input: word = "234Adas"
Output: true
Explanation:
This word satisfies the conditions.
Example 2:
Input: word = "b3"
Output: false
Explanation:
The length of this word is fewer than 3, and does not have a vowel.
Example 3:
Input: word = "a3$e"
Output: false
Explanation:
This word contains a '$' character and does not have a consonant.

Constraints:
1 <= word.length <= 20
word consists of English uppercase and lowercase letters, digits, '@', '#', and '$'.
 */