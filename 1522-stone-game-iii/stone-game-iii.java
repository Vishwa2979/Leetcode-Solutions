class Solution {
    Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {
        dp = new Integer[stoneValue.length];

        int diff = solve(stoneValue, 0);

        if(diff > 0) return "Alice";
        if(diff < 0) return "Bob";
        return "Tie";
    }

    public int solve(int[] stoneValue, int i) {
        if(i >= stoneValue.length) {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        int sum = 0;
        int ans = Integer.MIN_VALUE;

        for(int k = 0; k < 3 && i + k < stoneValue.length; k++) {
            sum += stoneValue[i + k];
            ans = Math.max(ans, sum - solve(stoneValue, i + k + 1));
        }

        return dp[i] = ans;
    }
}