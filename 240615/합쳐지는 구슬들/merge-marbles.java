import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/**
 * m개의 구슬이 nxn격자 위에 존재 격자는 벽으로 둘러싸여 있음
 * 
 * 구슬 1초에 한칸씩 상하좌우 중 한 방향으로 이동 구슬은 번호, 무게가 존재 벽에 부딪히면 움직이는 방향이 반대로 뒤집힘, 이때 1초 소요
 * 엇갈리는 경우는 충돌로 간주X
 * 
 * 만약 같은 칸에 존재하는 구슬이 두 개 이상이라면 충돌 충돌이 일어나면 구슬이 합쳐짐, 이 구슬의 무게는 해당 위치의 모든 구슬의 합으로
 * 결정 방향과 번호는 합쳐진 구슬들 중 가장 큰 번호가 매겨져있는 구슬에 따름
 * 
 * @author 82108
 *
 */
public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static Marble[][] map;
	static List<Marble> marbles;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new Marble[R][C];
		marbles = new ArrayList<Marble>();

		int[] dInfo = new int[27];
		Arrays.fill(dInfo, -1);
		dInfo['U' - 'A'] = 0;
		dInfo['R' - 'A'] = 1;
		dInfo['D' - 'A'] = 2;
		dInfo['L' - 'A'] = 3;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = dInfo[st.nextToken().charAt(0) - 'A'];
			int w = Integer.parseInt(st.nextToken());

			Marble marble = new Marble(i + 1, r, c, w, d);
			map[r][c] = marble;
			marbles.add(marble);
		}

		for (int i = 0; i < t; i++) {
			moveMarbles();
		}
		
		printResult();

	}

	private static void printResult() {
		int maxW = 0;
		int cnt = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == null) continue;
				if(Integer.compare(maxW, map[r][c].w) < 0) {
					maxW = map[r][c].w;
				}
				cnt++;
			}
		}
		
		System.out.println(cnt+ " "+maxW);
	}

	private static void printMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == null) {
					System.out.print("-\t");
				}else {
					System.out.print(map[r][c]+"\t");
				}
			}
			System.out.println("");
		}
		System.out.println("=========================");
	}

	private static void moveMarbles() {
		Marble[][] newMap = new Marble[R][C];
		List<Marble> newMarbles = new ArrayList<>();

		for (Marble marble : marbles) {

			moveMarble(marble, newMap, newMarbles);
		}

		map = newMap;
		marbles = newMarbles;

	}

	private static void moveMarble(Marble marble, Marble[][] newMap, List<Marble> newMarbles) {
		int newd = marble.d;
		int newr = marble.r + dr[newd];
		int newc = marble.c + dc[newd];

		if (!isInRange(newr, newc)) {
			newd += 2;
			newd %= 4;

			newr = marble.r;
			newc = marble.c;
		}

		marble.move(newr, newc, newd);
		if (newMap[marble.r][marble.c] == null) {
			newMap[marble.r][marble.c] = marble; 
			newMarbles.add(marble);
		}else {
			Marble befo = newMap[newr][newc];
			if(befo.compareTo(marble) < 0) {
				befo.idx = marble.idx;
				befo.d = marble.d;
			}
			befo.w += marble.w;
		}
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	static class Marble implements Comparable<Marble> {
		int idx, r, c, w, d;

		public Marble(int idx, int r, int c, int w, int d) {
			super();
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.w = w;
			this.d = d;
		}

		public void move(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Marble o) {
			return Integer.compare(this.idx, o.idx);
		}

		@Override
		public String toString() {
			return "[w=" + w + ", d=" + d + "]";
		}
	}
}