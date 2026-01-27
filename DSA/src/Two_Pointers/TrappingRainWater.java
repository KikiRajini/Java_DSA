package Two_Pointers;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                //calculating new max before the volume because if leftMax is smaller than current value, vol will be negative and we need to add extra if
                //but now the vol will be 0 in that case. if leftmax is greater than current val..the leftmax will stay the same
                res += leftMax - height[l];
                //why we consider only the leftmax and not right max? also right max here may not be the true rightmax
                //becuase we care only about the minimum.
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
}
