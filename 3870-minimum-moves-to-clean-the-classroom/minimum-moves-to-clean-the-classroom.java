
class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sx = 0, sy = 0, count = 0;
        int[][] id = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                id[i][j] = -1;

                if (classroom[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                } else if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = count++;
                }
            }
        }

        if (count == 0) return 0;

        int full = (1 << count) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << count];

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sx, sy, energy, 0, 0});
        visited[sx][sy][energy][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int steps = cur[4];

            if (mask == full) return steps;

            if (e == 0) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;

                if (classroom[nx].charAt(ny) == 'X')
                    continue;

                int ne = e - 1;
                int nmask = mask;

                if (classroom[nx].charAt(ny) == 'R')
                    ne = energy;

                if (classroom[nx].charAt(ny) == 'L')
                    nmask |= (1 << id[nx][ny]);

                if (!visited[nx][ny][ne][nmask]) {
                    visited[nx][ny][ne][nmask] = true;

                    q.offer(new int[]{
                        nx, ny, ne, nmask, steps + 1
                    });
                }
            }
        }

        return -1;
    }
}

