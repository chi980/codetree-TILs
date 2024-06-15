import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int max;
	static int[][] map;
	static int n;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		visited = new boolean[n];
		pickNumber(0, Integer.MAX_VALUE);
		System.out.println(max);
		
	}
	private static void pickNumber(int r, int min) {
		if(r == n) {
			max = Math.max(max, min);
			return;
		}
		
		for (int c = 0; c < n; c++) {
			if(visited[c]) continue;
			visited[c] = true;
			pickNumber(r+1, Math.min(min, map[r][c]));
			visited[c] = false;
		}
	}
}