import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 0, 1, 2로 이루이전 NXN 격자판 핀볼 게임 시작 0은 빈공간, 1은 /, 2는 \ 1이나, 2일 경우 구슬이 해당 위치로 진입했을
 * 때 바에 부딪혀 진행방향이 바뀜 구슬이 격자 밖으로 나가면 게임은 끝남 구슬이 한 칸 움직이는데 1초의 시간 소요 격자 안으로 들어가는
 * 시간과 격자 밖으로 나오는 시간까지 포함하여 계산
 * 
 * 구슬은 단 하나이며, 격자밖 모든 공간에서 진입가능(꼭지점x)
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static final int EMPTY = 0;

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

		Queue<int[]> startPoses = new ArrayDeque<>();
		for (int c = 0; c < C; c++) {
			startPoses.offer(new int[] { -1, c, 2 });
			startPoses.offer(new int[] { R, c, 0 });
		}

		for (int r = 0; r < R; r++) {
			startPoses.offer(new int[] { r, -1, 1 });
			startPoses.offer(new int[] { r, C, 3 });
		}

		int maxTime = 0;
		while (!startPoses.isEmpty()) {
			int[] cur = startPoses.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int time = 0;
			
			int[][] visited = new int[R][C];

			do {
				int newr = r + dr[d];
				int newc = c + dc[d];

				r = newr;
				c = newc;

				if (isInBoard(r, c)) {
					visited[r][c] = time+1;
					if (map[r][c] == 1) {
						if (d == 0)
							d = 1;
						else if (d == 1)
							d = 0;
						else if (d == 2)
							d = 3;
						else if (d == 3)
							d = 2;
						else
							d = -1;
					} else if (map[r][c] == 2) {
						if (d == 0)
							d = 1;
						if (d >= 0 && d < 4)
							d = 3 - d;
						else
							d = -1;
					}
				}

				time++;
			} while (isInBoard(r, c));
			maxTime = Math.max(maxTime, time);
		}
		System.out.println(maxTime);
	}

	private static boolean isInBoard(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}