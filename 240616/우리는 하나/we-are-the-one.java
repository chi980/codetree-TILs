import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * nxn격자 나라 각 칸마다 하나의 도시 각 도시는 높이 k개의 도시를 골라 골라진 k개의 도시로부터 갈 수 있는 서로 다른 도시의 수를
 * 최대화하고자 함 이때 상하좌우로 인접한 도시간의 이동만 가능, 두 도시간 높이차가 u이상d이하인 경우만 가능
 * 
 * k개의 도시를 적절히 골라 갈 수 있는 서로 다른 도시의 수를 최대로 해보자
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int K, U, D;// 도시 수, 하한, 상한
	static int R, C;
	static int[][] map;
	static int[] picked;
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = R;
		K = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		maxCnt = 0;
		picked = new int[K];
		pickCities(0, 0);
		System.out.println(maxCnt);
	}

	private static void pickCities(int depth, int start) {
		if (depth == K) {
			int cnt = BFS();
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		for (int i = start; i < R * C; i++) {
			picked[depth] = i;
			pickCities(depth + 1, i + 1);
		}
	}

	private static int BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];

		int cnt = 0;

		for (int start : picked) {
			int startr = start / C;
			int startc = start % C;

			q.offer(start);
			visited[startr][startc] = true;
			cnt++;
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			int curr = cur / C;
			int curc = cur % C;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!canGo(newr, newc, visited))
					continue;
				int diff = Math.abs(map[newr][newc] - map[curr][curc]);
				if (diff < U || diff > D)
					continue;

				visited[newr][newc] = true;
				q.offer(newr * C + newc);
				cnt++;
			}
		}

		return cnt;
	}

	private static boolean canGo(int r, int c, boolean[][] visited) {
		if (!isInRange(r, c))
			return false;
		if (visited[r][c])
			return false;

		return true;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}