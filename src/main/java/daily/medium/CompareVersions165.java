package daily.medium;


public class CompareVersions165 {
    public static void main(String[] args) {
        System.out.println(compareVersion("1.0","1"));
    }

//    split + parse; two pass; time: O(N + M), space: O(N + M)
    public static int compareVersion(String version1, String version2) {
        String[] num1 = version1.split("\\.");
        String[] num2 = version2.split("\\.");
        int n1 = num1.length, n2 = num2.length;
        int v1, v2;
        for(int i = 0 ; i < Math.max(n1, n2) ; i++) {
            v1 = i < n1 ? Integer.parseInt(num1[i]) : 0;
            v2 = i < n2 ? Integer.parseInt(num2[i]) : 0;
            if(v1 != v2)
                return v1 > v2 ? 1 : -1;
        }
        return 0;
    }

//    def;
    public static int compareVersionN(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int len1 = arr1.length, len2 = arr2.length;
        int minLen = Math.min(len1, len2);
        for(int i = 0 ; i < minLen ; i++) {
            int val1 = Integer.parseInt(arr1[i]);
            int val2 = Integer.parseInt(arr2[i]);
            if(val1 > val2) return 1;
            else if(val1 < val2) return -1;
        }
        if(len1 == len2) return 0;
        if(len1 < len2) {
            for(int i = minLen ; i < len2 ; i++) {
                if(Integer.parseInt(arr2[i]) > 0) return -1;
            }
        } else {
            for(int i = minLen ; i < len1 ; i++) {
                if(Integer.parseInt(arr1[i]) > 0) return 1;
            }
        }
        return 0;
    }
}

/*
Given two version strings, version1 and version2, compare them. A version string consists of revisions separated by dots '.'. The value of the revision is its integer conversion ignoring leading zeros.
To compare version strings, compare their revision values in left-to-right order. If one of the version strings has fewer revisions, treat the missing revision values as 0.
Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
Example 1:
Input: version1 = "1.2", version2 = "1.10"
Output: -1
Explanation:
version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.
Example 2:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation:
Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
Example 3:
Input: version1 = "1.0", version2 = "1.0.0.0"
Output: 0
Explanation:
version1 has less revisions, which means every missing revision are treated as "0".

Constraints:
1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */
