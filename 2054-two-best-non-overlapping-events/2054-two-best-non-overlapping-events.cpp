class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());

        int n = events.size();

        vector<int> suffix(n, 0);

        suffix[n - 1] = events[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = max(suffix[i + 1], events[i][2]);
        }

        int maxi = 0;
        for (int i = 0; i < n; i++) {
            auto it = lower_bound(events.begin(), events.end(),
                                  vector<int>{events[i][1]+1, INT_MIN, INT_MIN});

            if (it != events.end()) {
                int index = it-events.begin();
               
                maxi = max(maxi, events[i][2] + suffix[index]);
            }else{
                  maxi = max(maxi, events[i][2]);
            }

           
        }
         return maxi;
    }
};