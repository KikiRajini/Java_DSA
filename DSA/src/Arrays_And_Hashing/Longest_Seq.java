package Arrays_And_Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.
A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.
You must write an algorithm that runs in O(n) time.
Example 1:
Input: nums = [2,20,4,10,3,4,5]
Output: 4
Explanation: The longest consecutive sequence is [2, 3, 4, 5].
Example 2:
Input: nums = [0,3,2,5,4,6,1,1]
Output: 7*/

public class Longest_Seq {

    public int longestConsecutive(int[] nums) {
        Set<Integer> uniqueNums = new HashSet<>();
        for (int n :nums){
            uniqueNums.add(n);
        }
        int longest =0;
        int length =0;
        for (int n :nums){
            if(!uniqueNums.contains(n-1)){
                length=1;
                while(uniqueNums.contains(n+length)){
                    length++;
                }
                longest = Math.max(longest, length);
            }

        }
        return longest;
    }


}

