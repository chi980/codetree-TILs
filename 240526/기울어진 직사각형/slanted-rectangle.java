import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { 1, -1, -1, 1 };

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

		int maxSum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int m1 = 1; m1 <= n; m1++) {
					for (int m2 = 1; m2 <= n; m2++) {
						int[] mList = new int[] {m1, m2, m1, m2};
						if (!check(r, c, mList))
							continue;
//						System.out.println("r: "+r+", c: "+c);
//						System.out.println(Arrays.toString(mList));
						int sum = getSum(r, c, mList);
						maxSum = Math.max(maxSum, sum);
//						System.out.println("===============================");
					}
				}
			}

		}
		
		System.out.println(maxSum);
	}

	private static boolean check(int r, int c, int[] mList) {
		int curr = r;
		int curc = c;
		for (int i = 0; i < 4; i++) {
			int newr = curr + mList[i] * dr[i];
			int newc = curc + mList[i] * dc[i];
			if (!checkInRange(newr, newc))
				return false;
			curr = newr;
			curc = newc;
		}
		return true;
	}

	private static boolean checkInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static int getSum(int startr, int startc, int[] mList) {
		int[][] visited = new int[R][C];
		int sum = 0;
		int mIdx = 0;

		int r = startr;
		int c = startc;
		int d = 0;

		do {

			visited[r][c] = 1;
			sum += map[r][c];
			
			mList[mIdx]--;
			
			if(mList[mIdx] == 0) {
				mIdx++;
				r += dr[d];
				c += dc[d];
				d += 1;
			}else {
				r += dr[d];
				c += dc[d];
			}
			
		} while (r != startr || c != startc);
		
		return sum;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}