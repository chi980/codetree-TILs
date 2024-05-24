import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * RXC크기의 이차원 영역의 좌측상단 -> 우측 하단
 * 상하좌우 인접한 칸만 이동 가능
 * 뱀이 없는 칸만 이동 가능
 * 
 * 탈출 경로가 있으면 1, 없으면 0 출력
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static int R, C;
	static int[][] map;
	
	static final int SNAKE = 0;
	
	public static void main(String[] args) throws IOException{
		
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
		
		
		boolean canExit = BFS(0,0);
		System.out.println(canExit?"1":"0");
	}
	
	private static boolean BFS(int startr, int startc) {
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[startr][startc] = true;
		q.offer(new int[] {startr, startc});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			for(int d=0;d<4;d++) {
				int newr = curr+dr[d];
				int newc = curc + dc[d];
				
				if(!canGo(visited, newr, newc)) continue;
				
				visited[newr][newc] = true;
				q.offer(new int[] {newr, newc});
			}
		}
		
		return visited[R-1][C-1];
	}

	private static boolean canGo(boolean[][] visited, int r, int c) {
		if(r < 0 || c < 0 || r >= R || c >= C) return false;
		if(visited[r][c]) return false;
		if(map[r][c] == SNAKE) return false;
		return true;
	}

	public static void printArr(int[][] map) {
		Arrays.stream(map).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}