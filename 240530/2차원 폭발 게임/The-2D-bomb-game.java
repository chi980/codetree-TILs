import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 한 열에 대해 M개 이상 같은 숫자가 연속으로 존재한다면 터짐 
 * 2. 중력 작용 
 * 3. 시계방향으로 90도 회전 
 * 4. 중력 작용
 * 
 * @author 82108
 *
 */
public class Main {
	static final int INIT = 101;
	static final int EMPTY = 0;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < k; i++) {
			bomb(m);
			rotate();
			bomb(m);
			rotate();
		}
		
		int bombCnt = (int) Arrays.stream(map)
                .flatMapToInt(Arrays::stream)
                .filter(value -> value > EMPTY)
                .count();

		System.out.println(bombCnt);
	}

	private static void bomb(int M) {
		int[][] newMap = new int[R][C];
		for (int c = 0; c < C; c++) {
			int last = INIT;
			int cnt = 0;

			int newR = R - 1;

			for (int r = R - 1; r >= 0; r--) {
				if(map[r][c] == EMPTY) continue;
				if (last == map[r][c]) {
					cnt++;
				} else {
					if (cnt < M) {
						for (int i = 0; i < cnt; i++) {
							newMap[newR--][c] = last;
						}
					}
					last = map[r][c];
					cnt = 1;
				}
			}

			if (cnt < M) {
				for (int i = 0; i < cnt; i++) {
					newMap[newR--][c] = last;
				}
			}
		}

		map = newMap;
	}

	private static void rotate() {
		int[][] newMap = new int[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				newMap[c][R - 1 - r] = map[r][c];
			}
		}

		map = newMap;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}