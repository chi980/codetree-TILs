import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static final int GOLD = 1;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxGold = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int k = 1; k <= 2*n-1; k++) {
					int cost = getCost(k-1);
					int goldCnt = BFS(r, c, k);
					int earn = goldCnt*m - cost;
					
					if(0 <= earn) {
						maxGold = Math.max(maxGold, goldCnt);
					}
//					System.out.println(cost);
//					System.out.println(earn);
//					System.out.println(goldCnt);
//					System.out.println("-------------------------");
				}
			}
		}
		
		System.out.println(maxGold);
	}

	private static int BFS(int startr, int startc, int k) {
		int[][] visited = new int[R][C];
		Queue<int[]> q = new ArrayDeque<>();

		visited[startr][startc] = 1;
		q.offer(new int[] { startr, startc });

		int cnt = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			if (map[curr][curc] == GOLD)
				cnt++;
			if (visited[curr][curc] == k)
				continue;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!canGo(visited, newr, newc))
					continue;

				visited[newr][newc] = visited[curr][curc] + 1;
				q.offer(new int[] { newr, newc });
			}
		}
		
//		printArr(visited);

		return cnt;
	}

	private static boolean canGo(int[][] visited, int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		if (visited[r][c] != 0)
			return false;
		return true;
	}

	private static int getCost(int k) {
		return k * k + (k + 1) * (k + 1);
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}