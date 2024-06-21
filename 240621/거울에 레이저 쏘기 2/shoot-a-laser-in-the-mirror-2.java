import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		R = C = n;

		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		Map<Integer, int[]> posMap = new HashMap<>();
		int value = 1;
		for (int c = 0; c < C; c++) {
			posMap.put(value++, new int[] { -1, c, 2 });
		}
		for (int r = 0; r < R; r++) {
			posMap.put(value++, new int[] { r, C, 3 });
		}
		for (int c = C - 1; c >= 0; c--) {
			posMap.put(value++, new int[] { R, c, 0 });
		}
		for (int r = R - 1; r >= 0; r--) {
			posMap.put(value++, new int[] { r, -1, 1 });
		}

		int start = Integer.parseInt(br.readLine());
		int cnt = getAnswer(posMap.get(start));
		System.out.println(cnt);
	}

	private static int getAnswer(int[] start) {
		int d = start[2];
		int r = start[0]+dr[d];
		int c = start[1]+dc[d];
		int cnt= 0;

		while(isInRange(r, c)) {
//			System.out.println(r+" "+c);
			if(map[r][c] == '/') {
				if(d%2==0) {
					d = (d+1)%4;
				}else {
					d = (d+3)%4;
				}
			}else {
				if(d%2!=0) {
					d = (d+1)%4;
				}else {
					d = (d+3)%4;
				}
			}
			r+=dr[d];
			c+=dc[d];
			cnt++;
		}

		return cnt;
	}

	private static boolean isInRange(int r, int c) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}
}