import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] dNums = br.readLine().toCharArray();

		int x = 0;
		int y = 0;
		int d = 0;
		for (int i = 0; i < dNums.length; i++) {
			if (dNums[i] == 'L') {
				d += 3;
				d %= 4;
			} else if (dNums[i] == 'R') {
				d += 1;
				d %= 4;
			} else if (dNums[i] == 'F') {
				x = x + dx[d];
				y = y + dy[d];
			}
		}
		System.out.println(x + " " + y);

	}
}