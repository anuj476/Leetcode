class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sx = 0, sy = 0, litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') { sx = i; sy = j; }
                else if (c == 'L') litterId[i][j] = litterCount++;
            }
        }
        int fullMask = (1 << litterCount) - 1;
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(bestEnergy[i][j], -1);
        Queue<int[]> q = new LinkedList<>();
        bestEnergy[sx][sy][0] = energy;
        q.offer(new int[]{sx, sy, 0, energy});
        int steps = 0, dirs[] = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1], mask = curr[2], e = curr[3];
                if (mask == fullMask) return steps;
                if (e == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dirs[d], nc = c + dirs[d + 1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') continue;
                    char cell = classroom[nr].charAt(nc);
                    int nextMask = mask | (cell == 'L' ? (1 << litterId[nr][nc]) : 0);
                    int nextEnergy = (cell == 'R') ? energy : e - 1;
                    if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextEnergy;
                        q.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}