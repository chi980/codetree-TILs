import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		R = C = n;
		int m = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		Map<Integer, int[]> positions = new HashMap<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				positions.put(map[r][c], new int[] { r, c });
			}
		}

		for (int i = 0; i < m; i++) {
			for (int value = 1; value <= n * n; value++) {
				int[] curpos = positions.get(value);
				int aftervalue = getNewPosition(curpos);
				
				swap(positions, value, aftervalue);
			}
		}
		printArr(map);

	}

	private static void swap(Map<Integer, int[]> positions, int befovalue, int aftervalue) {
		int[] befopos = positions.get(befovalue);
		int[] afterpos = positions.get(aftervalue);
		
		positions.put(befovalue, afterpos);
		positions.put(aftervalue, befopos);
		
		map[befopos[0]][befopos[1]] = aftervalue;
		map[afterpos[0]][afterpos[1]] = befovalue;
	}

	private static int getNewPosition(int[] pos) {
		int curr = pos[0];
		int curc = pos[1];
		
		int maxvalue = 0;
		for (int d = 0; d < 8; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (!check(newr, newc))
				continue;
			if(maxvalue > map[newr][newc]) continue;
			maxvalue = map[newr][newc];
		}
		
		return maxvalue;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}