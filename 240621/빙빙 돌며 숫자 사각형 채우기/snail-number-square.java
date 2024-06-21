import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int[][] visited = new int[R][C];
		int r = 0;
		int c = 0;
		int d = 1;
		int value = 1;

		for (int i = 0; i < R * C; i++) {
			visited[r][c] = value++;

			int newr = r + dr[d];
			int newc = c + dc[d];

			if (isInRange(newr, newc) && visited[newr][newc] == 0) {
				r = newr;
				c = newc;
			} else {
				d = (d + 1) % 4;
				r = r + dr[d];
				c = c + dc[d];
			}
		}
		
		printArr(visited);
	}

	private static boolean isInRange(int r, int c) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}
	
	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}