import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * nxn격자 1~100 특정 위치에서 시작, 아래 조건을 만족하는 숫자 위치를 찾아 상하좌우로 이동하자 k번 반복한 이후의 위치를 구하는
 * 것이 목표(k번 반복을 하지 못했으나 새로 이동할 위치가 없다면 종료)
 * 
 * 이동조건 1. 시작 위치 숫자를 x라고 할 때 시작 위치에서 출발하여 인접한 칸(상하좌우로 갈 수 있는) 숫자가 x보다 작은 곳으로 이동
 * 가능 만약 시작위치의 상하좌우가 시작숫자보다 큰 숫자로 둘러쌰여져 있다면 이동 불가 2. 1번 조건 중 최댓값, 여러 개인 경우 행 번호가
 * 가장 작은 곳, 같다면 열 번호가 가장 작은 곳
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		R = C = N;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;

		int curr = startr;
		int curc = startc;
		for (int k = 0; k < K; k++) {
			if (!canGo(curr, curc))
				continue;

			boolean[][] canGo = new boolean[R][C];
			if (BFS(curr, curc, canGo) == 0)
				break;

			// 최댓값을 찾아주는 함수
			int[] end = getEnd(canGo, curr, curc);
			curr = end[0];
			curc = end[1];
			
//			System.out.println(Arrays.toString(end)+"로 이동");
//			System.out.println("======================");
		}
		
		System.out.println((curr+1)+" "+(curc+1));
	}

	private static int[] getEnd(boolean[][] canGo, int curr, int curc) {
		int maxr = curr;
		int maxc = curc;
		int maxValue = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(!canGo[r][c]) continue;
				if(r == curr && c == curc) continue;
				if(maxValue >= map[r][c]) continue;
				
				maxr = r;
				maxc = c;
				maxValue = map[r][c];
			}
		}
		return new int[] {maxr, maxc};
	}

	private static int BFS(int startr, int startc, boolean[][] visited) {
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();

		visited[startr][startc] = true;
		q.offer(new int[] { startr, startc });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;
				if (map[newr][newc] >= map[startr][startc] || visited[newr][newc])
					continue;

				visited[newr][newc] = true;
				q.offer(new int[] { newr, newc });
				cnt++;
			}

		}

		return cnt;
	}

	private static boolean canGo(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int newr = r + dr[d];
			int newc = c + dc[d];

			if (!isInRange(newr, newc))
				continue;

			if (map[newr][newc] <= map[r][c])
				return true;
		}
		return false;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static void printArr(boolean[][] array) {
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				System.out.print(array[r][c] ? 1 : 0);
			}
			System.out.println("");
		}
	}
}