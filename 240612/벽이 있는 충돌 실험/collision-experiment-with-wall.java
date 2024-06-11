import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] status;
	static boolean[] isDead;
	static int[][] marbles;
	static boolean[][][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			R = C = N;
			status = new int[R][C];
			isDead = new boolean[M+1];
			marbles = new int[M + 1][3];// 0은 r, 1은 c, 2는 d
			visited = new boolean[M + 1][4][R][C];

			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				char dInfo = st.nextToken().charAt(0);

				int d = -1;

				if (dInfo == 'U')
					d = 0;
				else if (dInfo == 'R')
					d = 1;
				else if (dInfo == 'D')
					d = 2;
				else if (dInfo == 'L')
					d = 3;

				status[r][c]++;
				marbles[i] = new int[] { r, c, d };
				visited[i][d][r][c] = true;
			}

			while (moveMarbles()) {
				killMarbles();
			}
			
			int restMarble = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					restMarble += status[r][c];
				}
			}
			System.out.println(restMarble);
		}

	}

	private static void killMarbles() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(status[r][c] < 2) continue;
				
				for(int idx=1;idx<marbles.length;idx++) {
					if(isDead[idx]) continue;
					int[] marble = marbles[idx];
					int marbler= marble[0];
					int marblec = marble[1];
					
					if(r == marbler && c == marblec) {
						isDead[idx] = true;
						status[marbler][marblec]--;
					}
					
				}
			}
		}
	}

	private static boolean moveMarbles() {
		boolean flag = false;
		for (int idx = 1; idx < marbles.length; idx++) {
			if(isDead[idx]) continue;
			int[] marble = marbles[idx];
			int marbler = marble[0];
			int marblec = marble[1];
			int marbled = marble[2];

			int newr = marbler + dr[marbled];
			int newc = marblec + dc[marbled];

			if (!isInRange(newr, newc)) {
				marbled += 2;
				marbled %= 4;

				newr = marbler;
				newc = marblec;
			}
			
			if(!visited[idx][marbled][newr][newc]) {
				flag = true;
			}
			
			visited[idx][marbled][newr][newc] = true;
			
			status[marbler][marblec]--;
			status[newr][newc]++;
			
			marble[0] = newr;
			marble[1] = newc;
			marble[2] = marbled;

		}

		return flag;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		else
			return true;
	}

	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}