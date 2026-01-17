package Arrays_And_Hashing;

/*Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
Example 1:
Input: s = "racecar", t = "carrace"
Output: true
Example 2:
Input: s = "jar", t = "jam"
Output: false*/

public class Is_Anagram {

        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;

            int[] trace = new int[128]; // ASCII range (works for caps and small)


            for (int i = 0; i < s.length(); i++) {
                trace[s.charAt(i)]++;
                trace[t.charAt(i)]--;
            }

            for (int n : trace) {
                if (n != 0) return false;
            }
            return true;
        }

}

/*If only small/large/case-insensitive case, use array of 26
ASCII values:
'A'	65
'Z'	90
'a'	97
'z'	122
 */
