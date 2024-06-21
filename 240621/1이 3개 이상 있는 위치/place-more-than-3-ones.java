import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

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

		int cnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				boolean flag = check(r, c);
				if(flag) cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static boolean check(int curr, int curc) {

		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (isInRange(newr, newc) && map[newr][newc] == 1)
				cnt++;
		}
		return cnt >= 3;
	}

	private static boolean isInRange(int r, int c) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}

}