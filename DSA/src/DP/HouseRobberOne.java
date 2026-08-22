package DP;

public class HouseRobberOne {
    public int rob(int[] nums) {
        int twoHousesBack = 0;
        int oneHouseBack = 0;

        for(int num : nums){
            int current = Math.max(num+twoHousesBack, oneHouseBack);
            twoHousesBack = oneHouseBack;
            oneHouseBack = current;
        }

        return oneHouseBack;
    }
}
