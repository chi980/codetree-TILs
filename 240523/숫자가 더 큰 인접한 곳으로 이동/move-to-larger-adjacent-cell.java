import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = R;
		int startr = Integer.parseInt(st.nextToken());
		int startc = Integer.parseInt(st.nextToken());
		startr--;
		startc--;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int value = map[startr][startc];
		int curr = startr;
		int curc = startc;

		while (true) {
			int r = curr;
			int c = curc;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!check(newr, newc))
					continue;
				
				if(value >= map[newr][newc]) continue;
				
				System.out.print(value+" ");
				value = map[newr][newc];
				curr = newr;
				curc = newc;
			}
			
			if(r == curr && c == curc) {
				System.out.println(value);
				break;
			}
		}

	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}