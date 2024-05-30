import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	static final int WALL = 0;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		R = C = n;
		map = new int[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int townCnt = 0;
		List<Integer> towns = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!canGo(r, c)) continue;

				visited[r][c] = true;
				int sum = 1;
				sum += DFS(r, c);
				townCnt++;
				towns.add(sum);
			}
		}
		System.out.println(townCnt);
		Collections.sort(towns);
		for(int town : towns) {
			System.out.println(town);
		}
	}

	private static int DFS(int curr, int curc) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];
			if (!canGo(newr, newc))
				continue;

			visited[newr][newc] = true;
			cnt++;
			cnt += DFS(newr, newc);
		}
		return cnt;
	}

	private static boolean canGo(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		if (map[r][c] == WALL)
			return false;
		if (visited[r][c])
			return false;

		return true;
	}
}