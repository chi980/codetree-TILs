import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] map = new int[N][M];
		int value = 1;
		int r = 0;
		int c = 0;
		int d = 2;

		while (value <= N * M) {
			map[r][c] = value++;
			if (isInRange(r + dr[d], c + dc[d], N, M) && map[r + dr[d]][c + dc[d]] == 0) {
				r += dr[d];
				c += dc[d];
			} else {
				d = (d + 3) % 4;
				r += dr[d];
				c += dc[d];
			}

		}
		printArr(map);
	}

	private static boolean isInRange(int r, int c, int R, int C) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}

	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}