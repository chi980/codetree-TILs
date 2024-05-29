import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n x n 격자판 특정 열 선택 시 해당 열에 숫자가 적혀있는 위치 중 가장 위에 있는 칸을 중심으로 십자 모양으로 폭탄 터짐 터진 후에는
 * 중력에 의해 숫자가 아래로 떨어짐 십자 모양의 크기는 선택된 숫자에 비례하여 커짐 ex) 터진 숫자 1 -> 자기 자신만 터짐, 2 ->
 * 자신 포함하여 인접한 4개의 격자 터짐 터져야 하는 범위가 격자판 범위를 벗어나는 경우는 무시
 * 
 * @author 82108
 *
 */
public class Main {
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

		R = C = n;

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()) - 1;

			boolean canBomb = bomb(c);
			if(canBomb) gravity();
			
		}

		printArr(map);

	}

	private static boolean bomb(int curc) {
		int curr = -1;

		for (int r = 0; r < R; r++) {
			if (map[r][curc] != EMPTY) {
				curr = r;
				break;
			}
		}

		if (curr == -1)
			return false;

		int K = map[curr][curc];
		map[curr][curc] = EMPTY;
		
		for (int k = 1; k < K; k++) {
			for (int d = 0; d < 4; d++) {
				int newr = curr + k * dr[d];
				int newc = curc + k * dc[d];

				if (!isInMap(newr, newc))
					continue;
				
				map[newr][newc] = EMPTY;
			}
		}

		return true;
	}

	private static boolean isInMap(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static void gravity() {
		int[][] newMap = new int[R][C];
		
		for (int c = 0; c < C; c++) {
			int newr = R-1;
			for (int r = R - 1; r >= 0; r--) {
				if(map[r][c] == 0) continue;
				newMap[newr--][c] = map[r][c];
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