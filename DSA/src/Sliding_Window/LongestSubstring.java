package Sliding_Window;

import java.util.HashSet;
import java.util.Set;
/*Given a string s, find the length of the longest substring without duplicate characters.

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: s = "zxyzxyz"

Output: 3
Explanation: The string "xyz" is the longest without duplicate characters.

Example 2:

Input: s = "xxxx"

Output: 1
Constraints:

0 <= s.length <= 1000
s may consist of printable ASCII characters.
*/


public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        char[] charArr = s.toCharArray();
        Set<Character> charSet = new HashSet<>();
        int l = 0;
        int max = 0;

        for (int r = 0; r < charArr.length; r++) {
            // If we find a duplicate, shrink the window from the left
            while (charSet.contains(charArr[r])) {
                charSet.remove(charArr[l]);
                l++;
            }

            // Add the current character and update max
            charSet.add(charArr[r]);
            max = Math.max(max, r - l + 1);
        }

        return max;
    }
}

/*public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int max = 0;
    // Standard ASCII size is 128
    int[] count = new int[128];
    int l = 0;

    for (int r = 0; r < n; r++) {
        char rightChar = s.charAt(r);
        count[rightChar]++; // Add character to window

        // If count > 1, we have a duplicate! Shrink from left.
        while (count[rightChar] > 1) {
            char leftChar = s.charAt(l);
            count[leftChar]--;
            l++;
        }

        max = Math.max(max, r - l + 1);
    }
    return max;
}

Why this is "Senior Level" Optimization
Memory Efficiency: int[128] takes exactly 512 bytes (128 * 4 bytes). A HashSet with just 10 characters can take up kilobytes due to object overhead and hash table buckets.

No Auto-boxing: You aren't converting char to Character objects back and forth.

Cache Locality: An array is stored in a contiguous block of memory. The CPU can read it much faster than a HashSet, where data is scattered across the "heap."*/