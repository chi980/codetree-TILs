import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[] dInfo = new int['Z' - 'A' + 1];
		dInfo['U' - 'A'] = 0;
		dInfo['R' - 'A'] = 1;
		dInfo['D' - 'A'] = 2;
		dInfo['L' - 'A'] = 3;

		int R, C;
		R = C = n;

		st = new StringTokenizer(br.readLine());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;
		int startd = dInfo[st.nextToken().charAt(0) - 'A'];

		int r = startr;
		int c = startc;
		int d = startd;
		for (int i = 0; i < t; i++) {
			int newr = r + dr[d];
			int newc = c + dc[d];

			if (isInRange(newr, newc, R, C)) {
				r = newr;
				c = newc;
			} else {
				d = (d + 2) % 4;
			}
		}
		
		System.out.println((r+1)+" "+(c+1));
	}

	private static boolean isInRange(int r, int c, int R, int C) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}
}