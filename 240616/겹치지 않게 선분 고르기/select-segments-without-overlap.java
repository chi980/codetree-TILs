import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int n;
	static int[][] lines;
	static boolean[] picked;
	static int maxPicked;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		lines = new int[n][2];
		picked = new boolean[n];

		for (int i = 0; i < n; i++) {
			lines[i][0] = sc.nextInt();
			lines[i][1] = sc.nextInt();
		}

		maxPicked = 0;
		pickLines(0, 0, 0);
		System.out.println(maxPicked);
	}

	private static void pickLines(int depth, int start, int cnt) {
		maxPicked = Math.max(maxPicked, cnt);
//		System.out.println(Arrays.toString(picked));
		if (depth == n) {
			return;
		}

		for (int i = start; i < n; i++) {
			if (isCrossed(i))
				continue;
			
			picked[i] = true;

			pickLines(depth + 1, i + 1, cnt + 1);

			picked[i] = false;
		}
		
		pickLines(depth+1, start+1, cnt);
	}

	private static boolean isCrossed(int curidx) {
		int[] curline = lines[curidx];

		for (int i = 0; i < curidx; i++) {
			if(!picked[i]) continue;
			int[] otherline = lines[i];

			if (isCrossed(curline, otherline))
				return true;
		}

		return false;
	}

	private static boolean isCrossed(int[] line1, int[] line2) {
		if (line1[1] < line2[0] || line1[0] > line2[1])
			return false;
		return true;
	}
}