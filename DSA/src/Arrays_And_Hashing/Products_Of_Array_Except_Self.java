package Arrays_And_Hashing;

/*Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
Each product is guaranteed to fit in a 32-bit integer.
Follow-up: Could you solve it in O(n) time without using the division operation?
Example 1:
Input: nums = [1,2,4,6]
Output: [48,24,12,8]
Example 2:
Input: nums = [-1,0,1,2,3]
Output: [0,-6,0,0,0]*/

public class Products_Of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        //o/p[i]= product of the values to the left * to the right;
        //prefix*suffix
        //prefix
        output[0]=1;
        for(int i =1; i<nums.length; i++){
            output[i]= output[i-1]*nums[i-1];
        }
        //suffix
        int right=1;
        for(int i = nums.length -1; i>=0; i--){
            output[i] = output[i]*right;
            right = right * nums[i];
        }
        return output;
    }
}
