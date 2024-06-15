import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static final int EMPTY = 0;
	static final int NORMAL = 1;
	static final int ABNORMAL = 2;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new int[R][C];
		time = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == EMPTY)
					time[r][c] = -1;
				else if (map[r][c] == NORMAL) {
					time[r][c] = Integer.MAX_VALUE;
				} else
					time[r][c] = 0;
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == ABNORMAL){
					BFS(r, c);
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(time[r][c] == Integer.MAX_VALUE) time[r][c] = -2;
			}
		}
		
		printArr(time);
	}

	private static void BFS(int startr, int startc) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[R][C];
		
		q.offer(new int[] { startr, startc });
		visited[startr][startc] = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;
				if (map[newr][newc] == EMPTY || map[newr][newc] == ABNORMAL)
					continue;
				if(visited[newr][newc] != 0) continue;
				
				visited[newr][newc] = visited[curr][curc]+1;
				time[newr][newc] = Math.min(time[newr][newc], visited[newr][newc]-1);
				q.offer(new int[] {newr, newc});
				
			}
		}

	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
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