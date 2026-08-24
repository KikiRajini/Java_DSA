package DP;

public class HouseRobberTwo {
    public int rob(int[] nums) {
        int n = nums.length;
        if(nums.length==0){return 0;}
        if(nums.length==1){return nums[0];}

        return Math.max(robValue(nums,0,n-2), robValue(nums,1,n-1));
    }

    private int robValue(int[] nums, int start, int end){
        int twoHousesBack =0;
        int oneHouseBack =0;

        for(int i =start; i<=end; i++){
            int current = Math.max(nums[i]+twoHousesBack, oneHouseBack);
            twoHousesBack = oneHouseBack;
            oneHouseBack = current;
        }

        return oneHouseBack;
    }
}
