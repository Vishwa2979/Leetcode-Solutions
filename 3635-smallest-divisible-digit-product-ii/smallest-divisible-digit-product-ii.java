class Solution {
    public String smallestNumber(String num, long t) {
        long tempT = t;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (tempT % p == 0) {
                tempT /= p;
            }
        }
        if (tempT != 1) {
            return "-1";
        }

        int n = num.length();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = num.charAt(i) - '0';
        }

        long[] p = new long[n + 1];
        p[0] = t;
        int firstZero = -1;

        for (int i = 0; i < n; i++) {
            if (s[i] == 0) {
                firstZero = i;
                break;
            }
            p[i + 1] = p[i] / gcd(p[i], s[i]);
        }

        if (firstZero == -1 && p[n] == 1) {
            return num;
        }

        int pos = (firstZero != -1) ? firstZero : n - 1;

        for (; pos >= 0; pos--) {
            int start = s[pos] + 1;
            if (firstZero != -1 && pos < firstZero) {
                start = s[pos] + 1;
            } else if (firstZero != -1 && pos > firstZero) {
                start = 1;
            }

            for (int d = start; d <= 9; d++) {
                long nextT = p[pos] / gcd(p[pos], d);
                int remLen = n - 1 - pos;
                if (canForm(nextT, remLen)) {
                    s[pos] = d;
                    fillSmallest(s, pos + 1, n - 1, nextT);
                    StringBuilder sb = new StringBuilder();
                    for (int x : s) {
                        sb.append(x);
                    }
                    return sb.toString();
                }
            }
        }

        int len = n + 1;
        while (!canForm(t, len)) {
            len++;
        }
        int[] ans = new int[len];
        fillSmallest(ans, 0, len - 1, t);
        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x);
        }
        return sb.toString();
    }

    private boolean canForm(long t, int len) {
        for (int d = 9; d >= 2; d--) {
            while (t % d == 0 && len > 0) {
                t /= d;
                len--;
            }
        }
        return t == 1;
    }

    private void fillSmallest(int[] s, int start, int end, long t) {
        int idx = end;
        for (int d = 9; d >= 2; d--) {
            while (t % d == 0) {
                s[idx--] = d;
                t /= d;
            }
        }
        while (idx >= start) {
            s[idx--] = 1;
        }
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}