package DP;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);

            // Case 2: Even length palindrome (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                // Calculate the start index from the current center i
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Distance between (left + 1) and (right - 1) inclusive:
        // (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}
