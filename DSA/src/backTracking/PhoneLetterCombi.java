package backTracking;

import java.util.ArrayList;
import java.util.List;

public class PhoneLetterCombi {

        // Global lookup array mapping each digit string index to its letters
        private static final String[] MAPPING = {
                "",     // 0
                "",     // 1
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv",  // 8
                "wxyz"  // 9
        };

        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();

            // Edge case: If no buttons are pressed, return empty list immediately
            if (digits == null || digits.length() == 0) {
                return result;
            }

            // Launch the DFS backtracking rocket starting at index 0 of digits
            dfs(0, digits, new StringBuilder(), result);

            return result;
        }

        private void dfs(int index, String digits, StringBuilder current, List<String> result) {
            // RULE 1: THE WIN CONDITION (The Finish Line)
            // If our button tracker index matches the total number of digits pressed,
            // we have successfully picked exactly one letter for each button.
            if (index == digits.length()) {
                result.add(current.toString());
                return; // Snapshot saved! Backtrack out of this room.
            }

            // 1. Identify which button we are looking at right now using the vertical index
            char digitChar = digits.charAt(index);
            int digitNum = digitChar - '0'; // Convert character '2' to integer 2
            String letters = MAPPING[digitNum]; // Load the fresh pool of letters (e.g., "abc")

            // RULE 2: THE FRESH POOL SCANNER
            // We ALWAYS start our loop counter at i = 0 here because this pool of letters
            // is completely independent of past buttons. Starting at 0 allows us to naturally
            // select identical letters (like "aa" for input "22") across separate steps.
            for (int i = 0; i < letters.length(); i++) {

                // CHOOSE: Append the current character from this button's independent pool
                current.append(letters.charAt(i));

                // EXPLORE: Move strictly forward to the next button by passing index + 1.
                // This guarantees we never look backward at previous buttons.
                dfs(index + 1, digits, current, result);

                // BACKTRACK: Remove the last added character to try the next letter on this button
                current.deleteCharAt(current.length() - 1);
            }
        }

}
