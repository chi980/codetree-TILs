import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[][] map;
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		R=C=n;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[R][C];
		
		dp[0][0] = map[0][0];
		for (int c = 1; c < C; c++) {
			dp[0][c] = dp[0][c-1] + map[0][c]; 
		}
		
		for(int r=1;r<R;r++) {
			dp[r][0] = dp[r-1][0] + map[r][0];
		}
		
		for (int r = 1; r < R; r++) {
			for (int c = 1; c < C; c++) {
				dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]) + map[r][c];
			}
		}
		
		System.out.println(dp[R-1][C-1]);
	}
}