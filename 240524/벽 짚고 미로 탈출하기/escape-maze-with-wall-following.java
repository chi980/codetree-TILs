import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final char WALL = '#';

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static char[][] map;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		R = Integer.parseInt(br.readLine());
		C = R;
		map = new char[R][C];
		time = new int[R][C];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int startr = Integer.parseInt(st.nextToken());
		int startc = Integer.parseInt(st.nextToken());
		startr--;
		startc--;

		for (int r = 0; r < R; r++) {
			char[] tmp = br.readLine().toCharArray();
			map[r] = tmp;
		}
		int d = 1;
		int curr = startr;
		int curc = startc;
		int curtime = 1;
		time[curr][curc] = curtime++;
		while (true) {

			int frontr = curr + dr[d];
			int frontc = curc + dc[d];

			if (check(frontr, frontc) && map[frontr][frontc] == WALL) {
				d += 3;
				d %= 4;
			} else {
				if (!check(frontr, frontc)) {
					System.out.println(curtime - 1);
					break;
				} else {

					curr = frontr;
					curc = frontc;
					time[curr][curc] = curtime++;
					if (!isRightWall(curr, curc, d)) {
						d += 1;
						d %= 4;
						
						if(!isRightWall(curr+dr[d], curc+dc[d], d)) {
							System.out.println("-1");
							break;
						}
						curr += dr[d];
						curc += dc[d];
						time[curr][curc] = curtime++;

					}

				}
			}
			
//			printArr(time);
		}
	}


	private static boolean isRightWall(int r, int c, int d) {
		int rightr = r + dr[(d + 1) % 4];
		int rightc = c + dc[(d + 1) % 4];
		if (check(rightr, rightc) && map[rightr][rightc] == WALL)
			return true;
		return false;
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
		System.out.println("");
	}
}