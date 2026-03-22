package Sliding_Window;

/*You are given two strings s1 and s2.

Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true.

Both strings only contain lowercase letters.

Example 1:

Input: s1 = "abc", s2 = "lecabee"

Output: true
Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee".

Example 2:

Input: s1 = "abc", s2 = "lecaabee"

Output: false*/

public class PermutationInString {
    //we'll use fixed sliding window to check by comparing the frequency arrays of both strings in the window
        public boolean checkInclusion(String s1, String s2) {
            //If the target string is bigger than the given string -> we return false;
            if (s1.length() > s2.length()) {
                return false;
            }

            //Build frequency array for the target string and build the frequency array of the first window of the given string
            int[] s1Count = new int[26];
            int[] s2Count = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s1Count[s1.charAt(i) - 'a']++;
                s2Count[s2.charAt(i) - 'a']++;
            }

            //see if both the arrays are equal by computing matches ..if freq of all alphabets match, then matches will be 26 and we will return true
            int matches = 0;
            for (int i = 0; i < 26; i++) {
                if (s1Count[i] == s2Count[i]) {
                    matches++;
                }
            }
            if (matches == 26) {
                return true;
            }

            int l = 0; //left -> starts with first window's left
            for (int r = s1.length(); r < s2.length(); r++) { //right starts with the new right element as first window ends with s1/length()-1


                //find index of new right and increment its count in s2count
                int index = s2.charAt(r) - 'a';
                s2Count[index]++;
                //core reason why we use matches instead of .equals in array -> for every comparison we increment/decrement matches O(1) time complexity
                //by using equals , we would have O(26) time complexity for every char in given string
                if (s1Count[index] == s2Count[index]) {
                    //if the count matches btw both count arrays we increment
                    matches++;
                } else if (s1Count[index] + 1 == s2Count[index]) {
                    //cannot use "just else" here because
                    //1. we need to reduce the matches only if there was already a match and the count of alphabet in r is increased by 1 because of our recent addition
                    //2. It can only increase by 1 in each loop
                    //3. No need to decrement if it was not a match initially
                    matches--;
                }

                //to remove the right character and its count and increment l to l+1
                index = s2.charAt(l) - 'a';
                s2Count[index]--;
                if (s1Count[index] == s2Count[index]) {
                    matches++;
                } else if (s1Count[index] - 1 == s2Count[index]) {
                    //Here we reduce only if there was match but by removing l , we reduced the count by 1 -> matches --
                    matches--;
                }

                if (matches == 26) {
                    return true;
                }

                l++;
            }
            return false;

        }

}
