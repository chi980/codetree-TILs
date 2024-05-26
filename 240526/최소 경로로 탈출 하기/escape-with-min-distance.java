import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static final int SNAKE = 0;
	static final int NOT_VISITED = -1;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

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

		int length = BFS(0, 0);
		System.out.println(length);
	}

	private static int BFS(int startr, int startc) {
		int[][] visited = new int[R][C];
		for (int r = 0; r < R; r++)
			Arrays.fill(visited[r], NOT_VISITED);
		Queue<int[]> q = new ArrayDeque<>();

		visited[startr][startc] = 0;
		q.offer(new int[] { startr, startc });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!canGo(newr, newc, visited))
					continue;
				visited[newr][newc] = visited[curr][curc] + 1;
				if (newr == R - 1 && newc == C - 1)
					break;
				q.offer(new int[] { newr, newc });
			}
		}

		return visited[R - 1][C - 1];
	}

	private static boolean canGo(int r, int c, int[][] visited) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		if (map[r][c] == SNAKE)
			return false;
		if (visited[r][c] != NOT_VISITED)
			return false;
		return true;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}