package Arrays_And_Hashing;


/*Question: Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

Example 1:
Input: nums = [1, 2, 3, 3]
Output: true

Example 2:
Input: nums = [1, 2, 3, 4]
Output: false */

import java.util.HashSet;
import java.util.Set;

public class Contains_Duplicate {

        public boolean hasDuplicate(int[] nums) {
            Set<Integer> tracker = new HashSet<>();
            for(int i :  nums){
                if(tracker.contains(i)){
                    return true;
                }else{
                    tracker.add(i);
                }
            }
            return false;
        }
}
