import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int[]dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		int zeroCnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(new int[] {i, j});
					
				}else if(map[i][j] == 0) zeroCnt++;
			}
		}
		
		int result = BFS(q, map, n, m, zeroCnt);
		System.out.println(result);
		
	}

	private static int BFS(Queue<int[]> q, int[][] map, int R, int C, int zeroCnt) {
		int cnt = 0;
		int result = 0;
		int[][] visited = new int[R][C];
		
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			
			int r = rc[0];
			int c=  rc[1];
			
			for(int d=0;d<4;d++) {
				int newr = r+dr[d];
				int newc = c+dc[d];
				
				if(newr < 0 || newc < 0 || newr>=R||newc>=C) continue;
				if(map[newr][newc] != 0) continue;
				if(visited[newr][newc]!=0) continue;
				
				visited[newr][newc] = visited[r][c] + 1;
				q.offer(new int[] {newr, newc});
				cnt++;
				result = Math.max(result, visited[newr][newc]);
			}
			
		}
		
//		printMap(visited);
		
		if(cnt == zeroCnt) return result;
		else return -1;
	}
	
	public static void printMap(int[][] arr) {
		Arrays.stream(arr).forEach(row -> System.out.println(Arrays.toString(row)));
		System.out.println("");
	}
}