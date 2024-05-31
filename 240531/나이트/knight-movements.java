import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};

	static final int NOT_VISITED = -1;
	static int R, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		R = C = n;
		
		int startr = sc.nextInt()-1;
		int startc = sc.nextInt()-1;
		int endr = sc.nextInt()-1;
		int endc = sc.nextInt()-1;
		
		int result = BFS(startr, startc, endr, endc);
		System.out.println(result);
	}

	private static int BFS(int startr, int startc, int endr, int endc) {
		int[][] visited = new int[R][C];
		for (int r = 0; r < R; r++) {
			Arrays.fill(visited[r], NOT_VISITED);
		}
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[startr][startc] = 0;
		q.offer(new int[] {startr, startc});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			for(int d=0;d<8;d++) {
				int newr = curr+dr[d];
				int newc = curc+dc[d];
				
				if(!canGo(newr, newc, visited)) continue;
				
				visited[newr][newc] = visited[curr][curc] +1;
				
				if(newr == endr && newc == endc) return visited[newr][newc];
				
				q.offer(new int[] {newr, newc});
			}
		}
		
		
		return -1;
	}

	private static boolean canGo(int r, int c, int[][] visited) {
		if(r < 0 || c < 0||r>=R||c>=C) return false;
		if(visited[r][c] != NOT_VISITED) return false;
		return true;
	}
}