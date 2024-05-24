import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		R = C = n;

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] marbles = new int[R][C];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			marbles[r][c]++;
		}

		
		for (int t = 0; t < T; t++) {
			int[][] newMarbles = new int[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (marbles[r][c] == 0)
						continue;
					int maxr = r;
					int maxc = c;
					int maxValue = map[r][c];
					for (int d = 0; d < 4; d++) {
						int newr = r + dr[d];
						int newc = c + dc[d];
						if (!check(newr, newc))
							continue;
						if(maxValue >= map[newr][newc]) continue;
						maxValue = map[newr][newc];
						maxr = newr;
						maxc = newc;
					}
					
					newMarbles[maxr][maxc]++;
				}
			}
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (newMarbles[r][c] >= 2) newMarbles[r][c] = 0;
				}
			}
			marbles = newMarbles;
		}

		
        int sum = Arrays.stream(marbles)
                .flatMapToInt(Arrays::stream)
                .sum();
        
        System.out.println(sum);
	}

	private static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row ->{
			Arrays.stream(row).mapToObj(value -> value+" ").forEach(System.out::print);
			System.out.println("");
		});
		System.out.println("");
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}