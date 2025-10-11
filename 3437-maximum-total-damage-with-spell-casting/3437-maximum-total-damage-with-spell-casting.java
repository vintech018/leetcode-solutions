class Solution {
    public long maximumTotalDamage(int[] power) {
        // Step 1: Frequency map
        Map<Long, Long> freq = new HashMap<>();
        for (int x : power)
            freq.put((long)x, freq.getOrDefault((long)x, 0L) + 1);

        // Step 2: Sort unique values
        List<Long> nums = new ArrayList<>(freq.keySet());
        Collections.sort(nums);
        int n = nums.size();
        long[] dp = new long[n];

        long result = Long.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            // Skip current value
            long skip = (i + 1 < n) ? dp[i + 1] : 0;

            // Take current value
            int j = lowerBound(nums, i + 1, nums.get(i) + 3);
            long take = nums.get(i) * freq.get(nums.get(i)) + ((j < n) ? dp[j] : 0);

            dp[i] = Math.max(skip, take);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    // Helper for binary search (lower_bound equivalent)
    private int lowerBound(List<Long> nums, int start, long target) {
        int low = start, high = nums.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}