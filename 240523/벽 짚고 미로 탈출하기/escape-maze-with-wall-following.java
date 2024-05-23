import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		int curtime = 0;
		time[curr][curc] = curtime++;
		while (true) {

			int frontr = curr + dr[d];
			int frontc = curc + dc[d];

			if (!check(frontr, frontc)) {
				break;
			}

			if (map[frontr][frontc] == WALL) {
				d += 3;
				d %= 4;

				time[curr][curc] = curtime;
			}else {
				curr = frontr;
				curc = frontc;
				time[curr][curc] = curtime++;
				
				int rightr = curr + dr[(d+1)%4];
				int rightc = curc + dc[(d+1)%4];
				
				if(map[rightr][rightc] != WALL) {
					curr = rightr;
					curc = rightc;
					time[curr][curc] = curtime++;
					
					d += 1;
					d %= 4;
				}
			}

//			Arrays.stream(time).forEach(row -> {
//				Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
//				System.out.println("");
//			});
//			System.out.println("");
		}
		
		System.out.println(curtime);
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}