class Solution {
    Long dp[][][][];
    public long maximumProfit(int[] prices, int k) {
        dp = new Long[prices.length][k+1][2][2];
        return solve(prices, 0, k, false, false);
    }
    public long solve(int prices[], int idx, int k, boolean buy, boolean sell){

        if(idx == prices.length || k == 0){
            if (buy || sell) return Long.MIN_VALUE/4;
            return 0;
        }
        int b = (buy) ? 1 : 0;
        int s = (sell) ? 1 : 0; 
        if(dp[idx][k][b][s] != null) return dp[idx][k][b][s];
        long ans = Long.MIN_VALUE;
        if(!buy && !sell){
            ans = Math.max(ans, -prices[idx] + solve(prices, idx+1, k, true, false));
            ans = Math.max(ans, prices[idx] + solve(prices, idx+1, k, false, true));
            ans = Math.max(ans, solve(prices, idx+1, k, false, false));
        } 
        if(buy && !sell){
            ans = Math.max(ans, prices[idx] + solve(prices, idx+1, k-1, false, false));
            ans = Math.max(ans, solve(prices, idx+1, k, true, false));
        }
        if(!buy && sell){
            ans = Math.max(ans, -prices[idx] + solve(prices, idx+1, k-1, false, false));
            ans = Math.max(ans, solve(prices, idx+1, k, false, true));
        }
        return dp[idx][k][b][s] = ans;
    }
}