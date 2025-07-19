package daily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSub1233 {
    public static void main(String[] args) {
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        System.out.println(removeSubfolders(folder));
    }

//    sorting; time: O(N.L.LogN), space: O(L.N)
    public static List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        result.add(folder[0]);
        for(int i = 1 ; i < folder.length ; i++) {
            String lastFolder = result.getLast();
            lastFolder += "/";
            if(!folder[i].startsWith(lastFolder))
                result.add(folder[i]);
        }
        return result;
    }
}

/*
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
Example 1:
Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:
Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
Example 3:
Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]

Constraints:
1 <= folder.length <= 4 * 104
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.
 */

/*
Complexity Analysis:
Let N be the number of folders and L be the maximum length of a folder path.
Time complexity: O(N⋅LlogN)
Sorting takes O(N⋅logN) comparisons, but each comparison can involve up to L characters (the maximum length of a folder path). Therefore, this step has a time complexity of O(N⋅LlogN).
The loop runs N−1 times. For each folder, it does the following:
Retrieves the last folder from result and appends a '/' to it, which takes O(L) time.
Uses compare to check if the current folder starts with the last added folder. This comparison will take O(L) time in the worst case.
Thus, the overall time complexity for this part is: O(N⋅L)
Therefore, combining the sorting and iteration steps, the total time complexity is: O(N⋅LlogN)+O(N⋅L)
Since O(N⋅LlogN) dominates O(N⋅L), we can simplify the time complexity to O(N⋅LlogN).
Space complexity: O(N⋅L)
The result array stores each folder that is not a sub-folder. In the worst case, every folder is added to result, which requires O(N⋅L) space.
The space taken by the sorting algorithm depends on the language of implementation:
In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logN).
In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logN).
In Python, the sort() method sorts a list using the Timsort algorithm which is a combination of Merge Sort and Insertion Sort and has a space complexity of O(N).
Thus, the total space complexity is O(N⋅L)
 */