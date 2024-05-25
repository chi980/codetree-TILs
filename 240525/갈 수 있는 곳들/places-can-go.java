import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static final int WALL = 1;
	
	static int R, C;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		R = C = n;
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		int sum = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			r--;c--;
			if(visited[r][c]) continue;
			sum += BFS(visited, r, c);
		}
		System.out.println(sum);
		
	}

	private static int BFS(boolean[][] visited, int startr, int startc) {
		int sum = 0;
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[startr][startc] = true;
		sum++;
		q.offer(new int[] {startr, startc});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			for(int d=0;d<4;d++) {
				int newr = curr+dr[d];
				int newc = curc+dc[d];
				
				if(!canGo(visited, newr, newc)) continue;
				
				visited[newr][newc] = true;
				sum++;
				q.offer(new int[] {newr, newc});
			}
		}
		
		return sum;
	}

	private static boolean canGo(boolean[][] visited, int r, int c) {
		if(r < 0 || c < 0 || r>=R||c>=C) return false;
		if(map[r][c] == WALL) return false;
		if(visited[r][c]) return false;
		return true;
	}
}