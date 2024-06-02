import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		int maxDepth = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				maxDepth = Math.max(maxDepth, map[r][c]);
			}
		}

		boolean[][] visited = new boolean[R][C];

		int maxCnt = 0;
		int maxK = 0;
		for (int k = 1; k <= maxDepth; k++) {
			int curCnt = 0;
			resetArr(visited, false);
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] <= k)
						continue;
					if (visited[r][c])
						continue;
					visited[r][c] = true;
					DFS(visited, r, c, k);
					curCnt++;
				}
			}
			
			if (maxCnt < curCnt) {
				maxCnt = curCnt;
				maxK = k;
			} else if (maxCnt == curCnt) {
				maxK = Math.min(maxK, k);
			}

		}

		System.out.printf("%d %d", maxK, maxCnt);
	}

	private static void resetArr(boolean[][] visited, boolean value) {
		for (int r = 0; r < visited.length; r++) {
			Arrays.fill(visited[r], value);
		}
	}

	private static void DFS(boolean[][] visited, int curr, int curc, int k) {
		for (int d = 0; d < 4; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (newr < 0 || newc < 0 || newr >= R || newc >= C)
				continue;
			if (map[newr][newc] <= k)
				continue;
			if (visited[newr][newc])
				continue;
			visited[newr][newc] = true;
			DFS(visited, newr, newc, k);
		}
	}
	public static void printArr(boolean[][] arr) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(arr[r][c]?"o ":"x ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}