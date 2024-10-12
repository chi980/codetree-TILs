import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int[] sdr = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] sdc = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };
	final static int DONT_MOVE = 1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (map[0][0] == 1)
			System.out.println(-1);
		else {
			int result = BFS(map, R, C, k, 0, 0);
			System.out.println(result);
		}
	}

	private static int BFS(int[][] map, int R, int C, int K, int startr, int startc) {
		int[][][] visited = new int[K+1][R][C];
		Queue<int[]> q = new ArrayDeque<>();

		visited[0][startr][startc] = 1;
		q.offer(new int[] { startr, startc, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int curr = cur[0];
			int curc = cur[1];
			int curk = cur[2];
			if(curr == R-1&&curc==C-1) continue;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!checkInBoard(newr, newc, R, C))
					continue;
				if (map[newr][newc] == DONT_MOVE)
					continue;
				if(visited[curk][newr][newc]!=0) continue;
				
				visited[curk][newr][newc] = visited[curk][curr][curc]+1;
				q.offer(new int[] {newr, newc, curk});
			}
			
			if(curk == K) continue;
			
			for(int d=0;d<8;d++) {
				int newr = curr + sdr[d];
				int newc = curc + sdc[d];

				if (!checkInBoard(newr, newc, R, C))
					continue;
				if (map[newr][newc] == DONT_MOVE)
					continue;
				if(visited[curk+1][newr][newc]!=0) continue;
				
				visited[curk+1][newr][newc] = visited[curk][curr][curc]+1;
				q.offer(new int[] {newr, newc, curk+1});
			}
		}
		int result = Integer.MAX_VALUE;
		for(int i=0;i<=K;i++) {
			result = Math.min(result, visited[i][R-1][C-1]);
		}
		return result!=Integer.MAX_VALUE?result-1:-1;
	}

	private static boolean checkInBoard(int r, int c, int R, int C) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		else
			return true;
	}
	
}