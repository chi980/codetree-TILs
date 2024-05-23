import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Consumer;
public class Main {
	static final int INIT = -1;
	static final int EMPTY = 0;

	static final char LEFT = 'L';
	static final char RIGHT = 'R';
	static final char UP = 'U';
	static final char DOWN = 'D';

	static int R, C;
	static int[][] map;

	static int[] tmpR;
	static int[] tmpC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		R = C = 4;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		char command = br.readLine().charAt(0);
		tmpR = new int[R];
		tmpC = new int[C];
		switch (command) {
		case LEFT :
			move(map, (m) -> {
				for (int r = 0; r < R; r++) {
					Arrays.fill(tmpC, EMPTY);

					int idx = 0;
					int tmp = INIT;
					int cnt = 0;
					for (int c = 0; c < C; c++) {
						if (m[r][c] == EMPTY)
							continue;

						if (tmp == m[r][c]) {
							cnt++;

							if (cnt == 2) {
								tmpC[idx++] = m[r][c] * 2;
								tmp = INIT;
								cnt = 0;
							}

						} else {
							if (tmp != INIT)
								tmpC[idx++] = tmp;
							tmp = m[r][c];
							cnt = 1;
						}
					}

					if (cnt == 1)
						tmpC[idx++] = tmp;

					for (int c = 0; c < C; c++)
						m[r][c] = tmpC[c];
				}

			});
			break;
		case RIGHT:
			move(map, (m) -> {

				for (int r = 0; r < R; r++) {
					Arrays.fill(tmpC, EMPTY);
					int idx = C - 1;
					int tmp = INIT;
					int cnt = 0;
					for (int c = C - 1; c >= 0; c--) {
						if (m[r][c] == EMPTY)
							continue;

						if (tmp == m[r][c]) {
							cnt++;

							if (cnt == 2) {
								tmpC[idx--] = m[r][c] * 2;
								tmp = INIT;
								cnt = 0;
							}

						} else {
							if (tmp != INIT)
								tmpC[idx--] = tmp;
							tmp = m[r][c];
							cnt = 1;
						}
					}

					if (cnt == 1)
						tmpC[idx--] = tmp;

					for (int c = 0; c < C; c++)
						m[r][c] = tmpC[c];

				}
			});
		break;
		case UP:
			move(map, (m) -> {
				for (int c = 0; c < C; c++) {
					Arrays.fill(tmpR, EMPTY);

					int idx = 0;
					int tmp = INIT;
					int cnt = 0;
					for (int r = 0; r < R; r++) {
						if (m[r][c] == EMPTY)
							continue;

						if (tmp == m[r][c]) {
							cnt++;

							if (cnt == 2) {
								tmpR[idx++] = m[r][c] * 2;
								tmp = INIT;
								cnt = 0;
							}

						} else {
							if (tmp != INIT)
								tmpR[idx++] = tmp;
							tmp = m[r][c];
							cnt = 1;
						}
					}

					if (cnt == 1)
						tmpR[idx++] = tmp;

					for (int r = 0; r < R; r++)
						m[r][c] = tmpR[r];
				}

			});
		break;
		case DOWN:
			move(map, (m) -> {
				for (int c = 0; c < C; c++) {
					Arrays.fill(tmpR, EMPTY);

					int idx = R-1;
					int tmp = INIT;
					int cnt = 0;
					for (int r = R-1; r >= 0; r--) {
						if (m[r][c] == EMPTY)
							continue;

						if (tmp == m[r][c]) {
							cnt++;

							if (cnt == 2) {
								tmpR[idx--] = m[r][c] * 2;
								tmp = INIT;
								cnt = 0;
							}

						} else {
							if (tmp != INIT)
								tmpR[idx--] = tmp;
							tmp = m[r][c];
							cnt = 1;
						}
					}

					if (cnt == 1)
						tmpR[idx--] = tmp;

					for (int r = 0; r < R; r++)
						m[r][c] = tmpR[r];
				}

			});
		break;
		}

		printArr(map);
	}

	private static void move(int[][] map, Consumer<int[][]> consumer) {
		consumer.accept(map);
	}

	public static void printArr(int[][] map) throws IOException {
        Arrays.stream(map).forEach(row -> {
            Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
            System.out.println();
        });
		
	}
}