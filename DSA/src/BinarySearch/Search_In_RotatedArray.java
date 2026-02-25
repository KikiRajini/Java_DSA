package BinarySearch;

/*You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Given the rotated sorted array nums and an integer target, return the index of target within nums, or -1 if it is not present.
You may assume all elements in the sorted rotated array nums are unique,
A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
Example 1:
Input: nums = [3,4,5,6,1,2], target = 1
Output: 4
Example 2:
Input: nums = [3,5,6,0,1,2], target = 4
Output: -1*/

public class Search_In_RotatedArray {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] == target) return mid;

                // 1. Identify which side is sorted
                if (nums[left] <= nums[mid]) {
                    // Left side is sorted (e.g., [4, 5, 6, 7, 0])
                    if (target >= nums[left] && target < nums[mid]) {
                        right = mid - 1; // Target is in the sorted range
                    } else {
                        left = mid + 1;  // Target is in the rotated side
                    }
                } else {
                    // Right side is sorted (e.g., [6, 7, 0, 1, 2])
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;  // Target is in the sorted range
                    } else {
                        right = mid - 1; // Target is in the rotated side
                    }
                }
            }
            return -1;
        }
}
