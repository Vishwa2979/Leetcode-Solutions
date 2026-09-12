import java.util.*;

class Solution {
    static class Interval {
        int start, end, weight, idx;

        Interval(int start, int end, int weight, int idx) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] a = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);
            a[i] = new Interval(x.get(0), x.get(1), x.get(2), i);
        }

        // Sort by end time.
        Arrays.sort(a, (x, y) -> {
            if (x.end != y.end)
                return Integer.compare(x.end, y.end);
            return Integer.compare(x.start, y.start);
        });

        int[] ends = new int[n];
        for (int i = 0; i < n; i++)
            ends[i] = a[i].end;

        // prev[i] = last interval j whose end < a[i].start
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1;
            int ans = -1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (ends[mid] < a[i].start) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = ans;
        }

        // dp[i][k] = best answer using first i intervals,
        // choosing at most k intervals.
        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++)
            dp[0][k] = new State(0, new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            dp[i][0] = new State(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {
                // Don't take interval i - 1
                State skip = dp[i - 1][k];

                // Take interval i - 1
                Interval cur = a[i - 1];

                State base = dp[prev[i - 1] + 1][k - 1];

                List<Integer> newIds = new ArrayList<>(base.ids);
                newIds.add(cur.idx);
                Collections.sort(newIds);

                State take = new State(
                    base.score + cur.weight,
                    newIds
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> ans = dp[n][4].ids;

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            result[i] = ans.get(i);

        return result;
    }

    private State better(State a, State b) {
        if (a.score != b.score)
            return a.score > b.score ? a : b;

        // Lexicographically smaller list wins.
        int m = Math.min(a.ids.size(), b.ids.size());

        for (int i = 0; i < m; i++) {
            if (!a.ids.get(i).equals(b.ids.get(i)))
                return a.ids.get(i) < b.ids.get(i) ? a : b;
        }

        return a.ids.size() <= b.ids.size() ? a : b;
    }
}