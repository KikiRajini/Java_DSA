package backTracking;

import java.util.ArrayList;
import java.util.List;

public class Partition {


        /**
         * Main launcher method to initiate palindrome partitioning.
         * @param s The input string to be partitioned.
         * @return A list of all valid combinations of palindromic sub-strings.
         */
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();

            // Edge case safety guard
            if (s == null || s.length() == 0) return result;

            // Launch the DFS tracking rocket:
            // Start at index 0, with a fresh temporary backpack (current list)
            partition(s, 0, new ArrayList<>(), result);

            return result;
        }

        /**
         * Recursive DFS helper method that uses backtracking to find valid partitions.
         */
        private void partition(String s, int index, List<String> current, List<List<String>> result) {

            // RULE 1: THE WIN CONDITION (The Finish Line)
            // If the index reaches the total length of the string, it means our knife
            // sliced completely through the whole bread board. Because the loop only
            // allows valid palindrome slices, hitting this point means the entire
            // configuration currently sitting inside the 'current' backpack is perfectly valid.
            if (index == s.length()) {
                // Take a deep-copy snapshot of our backpack so future backtracks don't erase it
                result.add(new ArrayList<>(current));
                return; // Shut down this room and step backward
            }

            // RULE 2 & 3: EXPLORATORY SLICING ENGINE
            // 'index' acts as our left hand holding the bread down.
            // 'i' acts as our knife pointer, testing larger and larger slices to the right.
            for (int i = index; i < s.length(); i++) {

                // HORIZONTAL PRUNING: Only make a slice if the chunk from 'index' to 'i' reads perfectly backwards
                if (isPalindrome(s, index, i)) {

                    // 1. CHOOSE: Slice the substring out.
                    // Java substrings exclude the end index, so we pass 'i + 1' to grab the actual character at 'i'.
                    current.add(s.substring(index, i + 1));

                    // 2. EXPLORE: Step vertically down into the next room.
                    // We pass 'i + 1' as the new starting line so the next room only processes the remaining bread.
                    // We pass our active 'current' backpack down so the next room can add onto our progress history.
                    partition(s, i + 1, current, result);

                    // 3. BACKTRACK: Clean up our mess!
                    // Pop out the last slice we added so that when this loop ticks forward horizontally,
                    // the knife can try testing a wider cut from a clean slate.
                    current.remove(current.size() - 1);
                }
            }
        }

        /**
         * Two-pointer utility helper to verify if a substring is a valid palindrome.
         */
        private boolean isPalindrome(String s, int left, int right) {
            // Step inwards from both ends simultaneously
            while (right > left) {
                // The moment characters don't match up, blow the whistle and reject it
                if (s.charAt(right) != s.charAt(left)) {
                    return false;
                }
                right--; // Move right wall leftward
                left++;  // Move left wall rightward
            }
            // If pointers successfully cross or meet, the substring is mathematically a palindrome
            // Note: For single characters (left == right), the loop is skipped instantly, returning true.
            return true;
        }

}
