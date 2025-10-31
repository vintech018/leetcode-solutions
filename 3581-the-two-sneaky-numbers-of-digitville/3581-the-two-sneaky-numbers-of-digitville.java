class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int XOR = 0; // a ^ b

        // XOR of all numbers in nums
        for (int num : nums) {
            XOR ^= num;
        }

        // XOR of all numbers from 0 to n-1 (the original list)
        for (int num = 0; num <= n - 1; num++) {
            XOR ^= num;
        }

        // Find rightmost set bit (similar to __builtin_ctz in C++)
        int trailZeroCount = Integer.numberOfTrailingZeros(XOR);
        int mask = 1 << trailZeroCount;

        int G1 = 0;
        int G2 = 0;

        // Separate into two groups based on mask bit
        for (int num : nums) {
            if ((num & mask) != 0) {
                G1 ^= num;
            } else {
                G2 ^= num;
            }
        }

        for (int num = 0; num <= n - 1; num++) {
            if ((num & mask) != 0) {
                G1 ^= num;
            } else {
                G2 ^= num;
            }
        }

        return new int[]{G1, G2};
    }
}