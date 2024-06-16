import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * nxn격자 -> 0,1로 이루어짐 주어진 돌 중 m개의 돌만 적절히 치워 k개의 시작점에서 상하좌우 인접한 곳으로만 이동하여 도달 가능한
 * 칸의 수를 최대로 하는 프로그램 작성해보자
 * 
 * 0이면 계속 BFS 1이면 BackTracking
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static final int STONE = 1;

	static int R, C;
	static int[][] map;

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int startr = Integer.parseInt(st.nextToken()) - 1;
			int startc = Integer.parseInt(st.nextToken()) - 1;

			boolean[][] visited = new boolean[R][C];
			BFS(startr, startc, visited, m, 0);
		}

		System.out.println(result);
	}

	private static void BFS(int startr, int startc, boolean[][] visited, int depth, int cnt) {
		int emptyCnt = 1;
		List<int[]> stoneList = new ArrayList<>();
		
		Queue<int[]> q = new ArrayDeque<>();

		visited[startr][startc] = true;
		q.offer(new int[] { startr, startc});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;
				
				
				if(map[newr][newc] == STONE) {
					stoneList.add(new int[] {newr, newc});
				}else {
					if(visited[newr][newc]) continue;
					visited[newr][newc] = true;
					q.offer(new int[] {newr, newc});
					emptyCnt++;
				}
			}
		}

		
		if(depth == 0) result = Math.max(result, cnt+emptyCnt);
		else {
			for (int i = 0; i < stoneList.size(); i++) {
				int[] stone = stoneList.get(i);
				int stoner = stone[0];
				int stonec = stone[1];
				
				BFS(stoner, stonec, copyArr(visited), depth-1,cnt+emptyCnt);
			}
		}
		
		

	}

	private static boolean[][] copyArr(boolean[][] visited) {
		int R = visited.length;
		boolean[][] copy = new boolean[R][];
		
		for (int r = 0; r < R; r++) {
			copy[r] = Arrays.copyOf(visited[r], visited[r].length);
		}
		
		return copy;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}