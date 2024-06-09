import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
 	static int R, C;
	static int[][] map;
	
	static boolean[] visited;
	static int maxSum;
	
	public static void main(String[] args) throws IOException{
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
		
		visited = new boolean[C];
		maxSum = 0;
		Permutation(0, 0);
		System.out.println(maxSum);
	}

	private static void Permutation(int r, int sum) {
		if(r == R) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for (int c = 0; c < C; c++) {
			if(visited[c]) continue;
			visited[c] = true;
			Permutation(r+1, sum+map[r][c]);
			visited[c] = false;
		}
	}
}