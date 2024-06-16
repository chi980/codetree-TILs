import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * NXM격자 1은 빙하, 0은 물 빙하는 1초에 한번씩 물에 닿아있는 부분들이 녹음 다만, 빙하로 둘러싸여있는 물은 빙하를 녹이지 못함
 * 
 * 빙하가 전부 녹는데 걸리는 시간과 마지막으로 녹은 빙하의 크기?
 * 
 * bfs돌리고 동시에 빙하크기 측정
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static final int WATER = 0;

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int rest = 0;
		int lastTime = 0;
		for (int time = 1;; time++) {
			int cnt = BFS(0, 0);
			if (cnt == 0)
				break;
			
			rest = cnt;
			lastTime = time;
		}
		
		System.out.println(lastTime +" "+rest);
		
		
	}

	private static int BFS(int startr, int startc) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];

		q.offer(startr * C + startc);
		visited[startr][startc] = true;

		int iceCnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			int curr = cur / C;
			int curc = cur % C;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;
				if (visited[newr][newc])
					continue;
				visited[newr][newc] = true;
				if (map[newr][newc] == WATER) {
					q.offer(newr * C + newc);
				} else {
					map[newr][newc] = WATER;
					iceCnt++;
				}

			}
		}

		return iceCnt;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}