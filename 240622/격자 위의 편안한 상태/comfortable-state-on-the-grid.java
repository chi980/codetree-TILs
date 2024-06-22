import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static int R, C;
	static boolean[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		R = C = N;
		map = new boolean[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			
			map[r][c] = true;
			
			boolean flag = checkComfortable(r, c);
			System.out.println(flag?"1":"0");
		}
	}

	private static boolean checkComfortable(int r, int c) {
		
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int newr = r+dr[d];
			int newc = c+dc[d];
			
			if(isInRange(newr,newc) && map[newr][newc]) cnt++;
			
		}
		
		return cnt==3;
	}

	private static boolean isInRange(int r, int c) {
		return !(r<0||c<0||r>=R||c>=C);
	}
}