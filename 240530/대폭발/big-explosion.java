import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * nxn 격자 1~m초동안 폭탄이 생김 처음 폭탄은 r,c위치에 존재 1초가 흐를 때마다 폭탄들은 새 폭탄을 생성(위치는 2^(현재초))
 * 폭탄이 생길때 격자 밖이나, 이미 해당 위치에 폭탄이 있는 경우 생기지 않음
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static final int EMPTY = -1;
	static int bombCnt = 0;

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;

		R = C = n;
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[r], -1);
		}
		
		map[startr][startc] = 0;
		bombCnt++;

		for (int t = 1; t <= m; t++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] >= t  || map[r][c] == EMPTY)
						continue;

					bomb(r, c, (int) Math.pow(2, t - 1), t);
				}
			}
			

//			printArr(map);
//			System.out.println("===================");
		}
		
		System.out.println(bombCnt);
	}

	private static void bomb(int curr, int curc, int deg, int time) {
		for (int d = 0; d < 4; d++) {
			int newr = curr + deg * dr[d];
			int newc = curc + deg * dc[d];

			if (!isInRange(newr, newc) || map[newr][newc] != EMPTY)
				continue;
			
			map[newr][newc] = time;
			bombCnt++;
		}
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value==-1?"- ": value+" ").forEach(System.out::print);
			System.out.println();
		});
	}

}