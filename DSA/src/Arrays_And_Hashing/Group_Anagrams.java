package Arrays_And_Hashing;

import java.util.*;

/*Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
Example 1:
Input: strs = ["act","pots","tops","cat","stop","hat"]
Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
Example 2:
Input: strs = ["x"]
Output: [["x"]]
Example 3:
Input: strs = [""]
Output: [[""]]
*/

public class Group_Anagrams {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String,List<String>> result = new HashMap<>();
            for(String str: strs){
                int[] trace = new int[26];
                for(char c : str.toCharArray()){
                    trace[c-'a']++;
                }
                String key = Arrays.toString(trace);
                result.putIfAbsent(key, new ArrayList<>());
                result.get(key).add(str);
            }
            return new ArrayList<>(result.values());
        }


}
