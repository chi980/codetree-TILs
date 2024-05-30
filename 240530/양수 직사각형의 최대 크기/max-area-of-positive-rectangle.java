import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int R, C;
	static int[][] map;

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

		int maxDimension = Integer.MIN_VALUE;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int m1 = 1; m1 + r <= R; m1++) {
					for (int m2 = 1; m2 + c <= C; m2++) {
						if(!isPositive(r, c, m1, m2)) continue;
						int dimension = m1 * m2;
						maxDimension = Math.max(maxDimension, dimension);
					}
				}
			}
		}		
		if (maxDimension == Integer.MIN_VALUE)
			System.out.println("-1");
		else
			System.out.println(maxDimension);
	}

	private static boolean isPositive(int startr, int startc, int m1, int m2) {
		for (int r = startr; r < startr + m1; r++) {
			for (int c = startc; c < startc + m2; c++) {
				if (map[r][c] <= 0)
					return false;
			}
		}
		return true;
	}

}