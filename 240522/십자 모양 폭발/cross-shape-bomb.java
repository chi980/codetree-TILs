import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static final int EMPTY = 0;
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		R = C = n;

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		r -= 1;
		c -= 1;
		
		bomb(r, c, map[r][c]-1);
		gravity(map, R, C);
		printArr(map);
	}

	private static void gravity(int[][] arr, int R, int C) {
		for (int c = 0; c < C; c++) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int r = R - 1; r >= 0; r--) {
				if(arr[r][c] == EMPTY) continue;
				q.offer(arr[r][c]);
				arr[r][c] = EMPTY;
			}
			
			for (int r = R - 1; r >= 0; r--) {
				if(q.isEmpty()) break;
				arr[r][c] = q.poll();
			}
		}
	}

	private static void bomb(int r, int c, int K) {
		map[r][c] = EMPTY;
		
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < 4; d++) {
				int newr = r + k*dr[d];
				int newc = c + k*dc[d];
				
				if(!check(newr, newc)) continue;
				
				map[newr][newc] = EMPTY;
			}
		}
	}

	private static boolean check(int r, int c) {
		if(r < 0 || c < 0 || r >= R || c >= C) return false;
		return true;
	}
	
	public static void printArr(int[][] arr) throws IOException{
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}