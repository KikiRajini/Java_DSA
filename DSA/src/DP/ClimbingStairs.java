package DP;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int oneStepBehind = 2;
        int twoStepBehind = 1;

        for(int i=3; i<=n; i++){
            int currentStep = oneStepBehind + twoStepBehind;
            twoStepBehind = oneStepBehind;
            oneStepBehind = currentStep;
        }

        return oneStepBehind;
    }
}
