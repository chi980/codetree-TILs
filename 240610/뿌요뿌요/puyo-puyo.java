import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	static boolean[][] visited;
	static int blockCnt;
	static int maxBlockSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		R = C = n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[R][C];
		blockCnt = 0;
		maxBlockSize = 0;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visited[r][c])
					continue;
				visited[r][c] = true;
				int size = bomb(r, c);
				if(size >= 4) {
					blockCnt++;
				}
				maxBlockSize = Math.max(maxBlockSize, size);
			}
		}
		System.out.printf("%d %d", blockCnt, maxBlockSize);
	}

	private static int bomb(int curr, int curc) {
		int size = 1;

		for (int d = 0; d < 4; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (!isInRange(newr, newc) || map[newr][newc] != map[curr][curc])
				continue;
			if (visited[newr][newc])
				continue;

			visited[newr][newc] = true;
			size += bomb(newr, newc);
		}

		return size;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}