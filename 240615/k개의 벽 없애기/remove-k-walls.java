import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static final int WALL = 1;

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		R = C = n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int endr = Integer.parseInt(st.nextToken()) - 1;
		int endc = Integer.parseInt(st.nextToken()) - 1;

		int result = BFS(startr, startc, endr, endc, k);
		System.out.println(result);
	}

	private static int BFS(int startr, int startc, int endr, int endc, int K) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][][] visited = new int[K + 1][R][C];

		q.offer(new int[] { 0, startr, startc });
		visited[0][startr][startc] = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curk = cur[0];
			int curr = cur[1];
			int curc = cur[2];

			if (curr == endr && curc == endc)
				return visited[curk][curr][curc] - 1;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;
				if (map[newr][newc] == WALL) {
					if (curk == K)
						continue;
					if (visited[curk + 1][newr][newc] != 0)
						continue;
					visited[curk + 1][newr][newc] = visited[curk][curr][curc] + 1;
					q.offer(new int[] { curk + 1, newr, newc });
				} else {
					if (visited[curk][newr][newc] != 0)
						continue;
					visited[curk][newr][newc] = visited[curk][curr][curc] + 1;
					q.offer(new int[] { curk, newr, newc });
				}

			}
		}

		return -1;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}