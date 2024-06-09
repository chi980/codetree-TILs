import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	static int[][] dr = { { -2, -1, 1, 2 }, { -1, 0, 1, 0 }, { -1, -1, 1, 1 } };
	static int[][] dc = { { 0, 0, 0, 0 }, { 0, 1, 0, -1 }, { -1, 1, 1, -1 } };

	static final int BOMB_SITE = 1;
	static List<int[]> bombSites;
	static int[] bombs;

	static int R, C;
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		R = C = n;
		bombSites = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == BOMB_SITE)
					bombSites.add(new int[] { r, c });
			}
		}

		bombs = new int[bombSites.size()];
		maxCnt = 0;
		Permutation(0);
		System.out.println(maxCnt);
	}

	private static void Permutation(int idx) {
		if (idx == bombSites.size()) {
			int regionCnt = getRegionCnt();
			maxCnt = Math.max(maxCnt, regionCnt);
			return;
		}

		for (int type = 0; type < 3; type++) {
			bombs[idx] = type;
			Permutation(idx + 1);
		}
	}

	private static int getRegionCnt() {
		boolean[][] visited = new boolean[R][C];

		for (int idx = 0; idx < bombSites.size(); idx++) {
			int[] bombSite = bombSites.get(idx);
			int bombType = bombs[idx];

			int curr = bombSite[0];
			int curc = bombSite[1];
			visited[curr][curc] = true;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[bombType][d];
				int newc = curc + dc[bombType][d];

				if (!isInRange(newr, newc))
					continue;

				visited[newr][newc] = true;
			}
		}

		int cnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visited[r][c])
					cnt++;
			}
		}
		return cnt;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}