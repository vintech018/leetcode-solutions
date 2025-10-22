class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        unordered_map<int, int> mp;
        unordered_map<int, int> freq;
        set<int> points;
        for (int i : nums) {
            freq[i]++;
            mp[i - k]++;
            mp[i + k + 1]--;
            points.insert(i);
            points.insert(i - k);
            points.insert(i + k + 1);
        }
        int ans = 1;
        int sum = 0;
        for (int i : points) {

            int curr = i;

            sum += mp[curr];

            int extra = min(sum - freq[curr], numOperations);
            ans = max(ans, freq[curr] + extra);
        }
        return ans;
    }
};
