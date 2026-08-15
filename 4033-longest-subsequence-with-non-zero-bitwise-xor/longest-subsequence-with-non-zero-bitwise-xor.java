class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean allZeros = true;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZeros = false;
            }
        }

        // Case 1: If every element is 0, no non-zero XOR subsequence exists.
        if (allZeros) {
            return 0;
        }

        // Case 2: If the XOR of the entire array is non-zero, the whole array works.
        if (totalXor != 0) {
            return nums.length;
        }

        // Case 3: If totalXor is 0 but there is at least one non-zero element,
        // removing any single non-zero element makes the XOR non-zero.
        return nums.length - 1;
    }
}