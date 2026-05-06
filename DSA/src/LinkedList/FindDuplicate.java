package LinkedList;

public class FindDuplicate {

        public int findDuplicate(int[] nums) {
            //Using Floyd's cycle detection to find if there is a cycle
            //Since these aren't nodes, we are going to use the value in the index as the pointer to next node

            int slow = nums[0];
            int fast = nums[nums[0]];

            while(fast!=slow){
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            //Second part of floyd's theorom
            //the distance from start of linkedlist to start of cycle = distance between start of cycle to point of collission (slow)

            int start= 0;
            while(slow!=start){
                start = nums[start];
                slow = nums[slow];
            }

            return start;

        }


}
