import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static final int SNAKE = 0;
	static final int NOT_VISITED = 0;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;
	static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0][0] = 1;
		DFS(0, 0);
		
		if(visited[R-1][C-1] == NOT_VISITED) System.out.println("0");
		else System.out.println("1");
	}

	private static void DFS(int curr, int curc) {
		for (int d = 2; d >=1; d--) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (!canGo(newr, newc))
				continue;

			visited[newr][newc] = visited[curr][curc] + 1;
			DFS(newr, newc);
		}
	}

	private static boolean canGo(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		if (map[r][c] == SNAKE ||visited[r][c] != NOT_VISITED)
			return false;
		return true;
	}

	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}

}