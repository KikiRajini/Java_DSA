package DP;

public class NumDecoding {
    public int numDecodings(String s) {
        // Fix 1: Guard against null, empty string, or leading '0'
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int twoBack = 1; // Base case: dp[0] (empty string prefix has 1 valid state)
        int oneBack = 1; // Base case: dp[1] (first character is non-zero, so 1 valid way)

        // Fix 2: Loop condition must be i <= s.length() to evaluate the full string length
        for (int i = 2; i <= s.length(); i++) {
            int current = 0;

            // Fix 3: Single digit must be between 1 and 9 (cannot be '0')
            int singleDigit = s.charAt(i - 1) - '0';
            if (singleDigit >= 1) {
                current += oneBack;
            }

            // Two-digit decode: must be between 10 and 26
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                current += twoBack;
            }

            // Shift states forward for the next index
            twoBack = oneBack;
            oneBack = current;
        }

        return oneBack;
    }
}
