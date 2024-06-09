import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int R, C;
	static int[][] numbers;
	static int[][] directions;
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		R = C = n;
		numbers = new int[R][C];
		directions = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				numbers[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				directions[r][c] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;

		maxCnt = 0;
		move(0, startr, startc);
		System.out.println(maxCnt);
	}

	private static void move(int cnt, int curr, int curc) {
		maxCnt = Math.max(maxCnt, cnt);
		int d = directions[curr][curc];
		int r = curr + dr[d];
		int c = curc + dc[d];
		while (isInRange(r, c)) {

			if (numbers[curr][curc] < numbers[r][c])
				move(cnt + 1, r, c);
			
			r += dr[d];
			c += dc[d];
		}
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}