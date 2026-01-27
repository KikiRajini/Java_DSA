package Two_Pointers;

public class TrappingRainWater {
        public int trap(int[] height) {
            int l =0; int r = height.length-1;
            int leftMax = height[l];
            int rightMax = height[r];

            int vol =0;
            while(l<r){
                if(leftMax<rightMax){
                    l++;
                    if(height[l]<leftMax){
                        vol = vol + (leftMax-height[l]);
                    }
                    leftMax = Math.max(leftMax,height[l]);
                }else{
                    r--;
                    if(height[r]<rightMax){
                        vol = vol + (rightMax-height[r]);
                    }
                    rightMax = Math.max(rightMax,height[r]);
                }
            }
            return vol;
        }
}
