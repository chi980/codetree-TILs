import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 숫자 0, 1, 2, 3으로 이루지느 nxn격자
 * 사람이 h명 겹치지 않게 서있다.
 * 비를 피할 수 있는 공간의 위치가 존재(m개)
 * 각 사람마다 비를 피할 수 있는 가장 가까운 공간까지의 거리를 구하는 프로그램 작성해보자
 * 1은 벽, 2는 사람, 3은 비를 피할 수 있는 칸
 * 사람은 상하좌우 인접한 곳만 이동 가능, 한 칸 이동 시 1초 소요
 * 벽이 아닌 곳은 이동 가능
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static final int WALL = 1;
	static final int PERSON = 2;
	static final int DESTINATION = 3;

	static int R, C;
	static int[][] map;

	static List<int[]> persons;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new int[R][C];
		persons = new ArrayList<int[]>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == PERSON)
					persons.add(new int[] { r, c });
			}
		}

		time = new int[R][C];
		for (int[] person : persons) {
			int r = person[0];
			int c = person[1];
			BFS(r, c);
		}
		
		printArr(time);

	}

	private static void BFS(int startr, int startc) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[R][C];
//		for (int r = 0; r < R; r++) {
//			Arrays.fill(visited[r], -1);
//		}

		q.offer(new int[] { startr, startc });
		visited[startr][startc] = 1;

		int minTime = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			if (map[curr][curc] == DESTINATION) {
				minTime = Math.min(minTime, visited[curr][curc]);
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!canGo(newr, newc, visited))
					continue;

				visited[newr][newc] = visited[curr][curc] + 1;
				q.offer(new int[] { newr, newc });
			}
		}
		
		
		if (minTime == Integer.MAX_VALUE)
			minTime = 0;
		time[startr][startc] = minTime-1;

	}

	private static boolean canGo(int r, int c, int[][] visited) {
		if (!isInRange(r, c))
			return false;
		if (map[r][c] == WALL)
			return false;
		if (visited[r][c] != 0)
			return false;
		return true;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
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