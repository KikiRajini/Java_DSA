package DP;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        //we are counting pathways , not steps.
        int oneStepBehind = 2; //for second you can either climb one step or two steps - two ways to reach step 2
        int twoStepBehind = 1; //For the first step, you can climb only one step - so one way to reach step 1

        for(int i=3; i<=n; i++){ //starting from 1st step, so extend till i=n
            int currentStep = oneStepBehind + twoStepBehind;
            //You can reach by takign the paths used in oneStepBehind and take one step or use the paths to reach twoStepBehind and jump 2 steps, so pathways remain sum of these two.

            twoStepBehind = oneStepBehind;
            oneStepBehind = currentStep;
        }

        return oneStepBehind;
    }
}
