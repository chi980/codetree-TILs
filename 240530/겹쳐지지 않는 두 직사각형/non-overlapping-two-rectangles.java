import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int R, C;
	static int[][] map;

	static boolean[][] visited;
	static int maxSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[R][C];
		maxSum = Integer.MIN_VALUE;
		selectRectangle(0, 0);
		
		System.out.println(maxSum);
	}

	private static void selectRectangle(int depth, int startPos) {
		if (depth == 2) {
			// 두 직사각형 합을 구하기
			int sum = getRectaglesSum();
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int pos = startPos; pos<R*C;pos++) {
				int r = pos/C;
				int c = pos%C;
				if (visited[r][c])
					continue;
				for (int m1 = 1; r + m1 <= R; m1++) {
					for (int m2 = 1; c + m2 <= C; m2++) {
						mark(r, c, m1, m2, true);
						selectRectangle(depth + 1, pos+1);
						mark(r, c, m1, m2, false);
					}
				}
		}
	}

	private static int getRectaglesSum() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(!visited[r][c]) continue;
				sum += map[r][c];
			}
		}
		return sum;
	}

	private static void mark(int startr, int startc, int m1, int m2, boolean value) {
		for (int r = startr; r < startr + m1; r++) {
			for (int c = startc; c < startc + m2; c++) {
				visited[r][c] = value;
			}
		}
	}

}