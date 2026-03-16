package BinarySearch;

public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1. Safety & Efficiency: Always search the shorter array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right = m;
        int halfLen = (m + n + 1) / 2; // The +1 ensures odd median is on the left

        while (left <= right) {
            // i is our partition in nums1, j is the forced partition in nums2
            int i = left + (right - left) / 2;
            int j = halfLen - i;

            // 2. Handle Boundaries with Infinity (Prevents crashing at edges)
            int nums1Left = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1Right = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int nums2Left = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2Right = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 3. The Diagonal Check
            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                // Correct partition found!

                if ((m + n) % 2 != 0) {
                    // Total is ODD: Median is the max of the left side
                    return Math.max(nums1Left, nums2Left);
                }

                // Total is EVEN: Average of the two middle elements
                return (Math.max(nums1Left, nums2Left) +
                        Math.min(nums1Right, nums2Right)) / 2.0;
            }
            // 4. Move the fences
            else if (nums1Left > nums2Right) {
                // Left side of nums1 is too big, move the cut left
                right = i - 1;
            } else {
                // Left side of nums1 is too small, move the cut right
                left = i + 1;
            }
        }
        return 0.0;
    }
}
