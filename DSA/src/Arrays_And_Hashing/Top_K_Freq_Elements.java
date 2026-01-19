package Arrays_And_Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements within the array.
The test cases are generated such that the answer is always unique.
You may return the output in any order.
Example 1:
Input: nums = [1,2,2,3,3,3], k = 2
Output: [2,3]
Example 2:
Input: nums = [7,7], k = 1
Output: [7]
Constraints:
1 <= nums.length <= 10^4.
-1000 <= nums[i] <= 1000
1 <= k <= number of distinct elements in nums.
*/

public class Top_K_Freq_Elements {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer,Integer> frequency = new HashMap<>();
            for(int num : nums){
                frequency.putIfAbsent(num,0);
                frequency.put(num,frequency.get(num)+1);
            }
            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)->frequency.get(a)-frequency.get(b));
            //Not using treemap as nums with same frequency will be overwitten
            //maintains order : small to big values
            for(int key: frequency.keySet()){
                heap.add(key);
                if(heap.size()>k){
                    heap.poll(); //ensure heap size is only k and not nums.size()
                }
            }

            int[] res = new int[k];
            for(int i=k-1; i>=0; i--){
                res[i] = heap.poll();
            }

            return res;

        }


}
