class Solution {
public:
    int C[31][31] = {{0}}; // iCj
    int md = 1e9 + 7;
    int dp[31][31][50][31];
    void precompute(){
        if(C[0][0]==1) return; // only once
        for(int i=1;i<=30;i++){
            C[i][0] = 1; C[i][i] = 1;
            for(int j=1;j<=i/2;j++){
                int iCj = C[i-1][j-1] + C[i-1][j];
                C[i][j] = iCj; C[i][i-j] = iCj;
            }
        }
    }
    int func(int m_rem, int k_rem, int i, int mask, vector<int> nums){
        // base cases
        int cnt = __builtin_popcount(mask); // no. of set bits
        if(m_rem<0 || k_rem<0 || m_rem + cnt < k_rem) return 0;
        if(m_rem==0){
            if(k_rem==cnt) return 1;
            else return 0;
        }
        if(i>=nums.size()) return 0;
        if(dp[m_rem][k_rem][i][mask]!=-1) return dp[m_rem][k_rem][i][mask];
        int x = nums[i];
        int exp = 1;
        int ans = 0;
        for(int f=0;f<=m_rem;f++){ // choose to take x, f times
            long long mul = ((long long)C[m_rem][f]*exp)%md;

            int newmask = mask + f; // what are the bits which get carried by this addition
            int next = newmask >> 1;
            int isSet = newmask&1; 
            ans = (ans + (long long)mul*func(m_rem-f, k_rem - isSet , i+1, next , nums))%md;
            exp = ((long long)exp*x)%md;
        }
        dp[m_rem][k_rem][i][mask] = ans;
        return ans;
    }
    int magicalSum(int m, int k, vector<int>& nums) {
        int n = nums.size();
        precompute();
        memset(dp,-1,sizeof(dp));
        return func(m,k,0,0,nums); // m_rem, k_rem, index, mask
    }
};