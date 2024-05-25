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

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int m1 = Integer.parseInt(st.nextToken());
		int m2 = Integer.parseInt(st.nextToken());
		int m3 = Integer.parseInt(st.nextToken());
		int m4 = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		if (dir == 0)
			rotate(r, c, new int[] {m1, m2, m3, m4}, dir, 1);
		else
			rotate(r, c, new int[] {m4, m3, m2, m1}, dir, 3);
		printArr(map);
	}

	private static void rotate(int startr, int startc, int[] mList, int dir, int value) {
		int r = startr;
		int c = startc;
		int mIdx = 0;
		int tmp = map[r][c];
		
		do {
//			System.out.printf("r: %d, c: %d\n", r, c);
			r += dr[dir];
			c += dc[dir];
			tmp = swap(r, c, tmp);
			mList[mIdx]--;
			
			if(mList[mIdx] == 0) {
				dir += value;
				dir %= 4;
				mIdx++;
			}
		}while(r != startr || c != startc);
	}

	private static int swap(int r, int c, int value) {
//		System.out.println("현재 value: "+map[r][c]);
		int befo = map[r][c];
		
		map[r][c] = value;
		
//		System.out.println("바뀐후 value: "+map[r][c]);
//		System.out.println("-----------------------");
		return befo;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}