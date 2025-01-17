class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indices = new HashMap<>(); 
        int tempTarget =0;
        for (int i =0; i< nums.length;i++){
            tempTarget = target - nums[i];
            if(indices.containsKey(tempTarget)){
                return new int[]{indices.get(tempTarget),i};
            }else{
                indices.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }
}
