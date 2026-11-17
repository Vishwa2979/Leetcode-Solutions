class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute proper divisors for every possible substring length (2 to n)
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        
        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(s, i, j, divisors[j - i + 1]);
            }
        }
        
        // dp[i][p] = min changes to partition prefix s[0..i-1] into p semi-palindromes
        int INF = 1_000_000;
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome must have length >= 2, so p parts require at least 2*p chars
            for (int i = 2 * p; i <= n; i++) {
                for (int prev = 2 * (p - 1); prev <= i - 2; prev++) {
                    if (dp[prev][p - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[prev][p - 1] + cost[prev][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getMinChanges(String s, int start, int end, List<Integer> divs) {
        int len = end - start + 1;
        int minChanges = len;
        
        for (int d : divs) {
            int changes = 0;
            int groupLen = len / d;
            
            // Check each of the d interleaved groups
            for (int offset = 0; offset < d; offset++) {
                int left = 0;
                int right = groupLen - 1;
                while (left < right) {
                    if (s.charAt(start + offset + left * d) != s.charAt(start + offset + right * d)) {
                        changes++;
                    }
                    left++;
                    right--;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }
        
        return minChanges;
    }
}