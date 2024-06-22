import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		R=C=N;
		map = new int[R][C];
		
		char[] commands = br.readLine().toCharArray();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = N/2;
		int c = N/2;
		int d = 0;
		
		int sum = map[r][c];
		
		for (char command : commands) {
			if(command == 'R') {
				d = (d+1)%4;
			}else if(command == 'L') {
				d = (d+3)%4;
			}else {
				if(!isInRange(r+dr[d], c+dc[d])) continue;
				r = r+dr[d];
				c = c+dc[d];
				
				sum += map[r][c];
			}
			
		}
		System.out.println(sum);
	}

	private static boolean isInRange(int r, int c) {
		return !(r<0||c<0||r>=R||c>=C);
	}
}