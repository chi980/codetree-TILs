import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static List<Marble> marbles;
	static PriorityQueue<Marble>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		R = C = n;
		marbles = new ArrayList<>();
		map = new PriorityQueue[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] = new PriorityQueue<>();
			}
		}

		int[] dInfo = new int[28];
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
			int v = Integer.parseInt(st.nextToken());
			Marble marble = new Marble(i+1, r, c, d, v);
			marbles.add(marble);
			map[r][c].offer(marble);
		}

//		t = 2;
//		printMap(map);
		for (int i = 0; i < t; i++) {
			moveMarbles();
//			printMap(map);
			deleteMarbles(k);
//			printMap(map);
//			System.out.println("**********************************");
		}
		
		int cnt = getRemainMarblesCnt();
		System.out.println(cnt);

	}

	private static int getRemainMarblesCnt() {
		int cnt = 0;
		for(Marble marble : marbles) {
			if(marble.isDead) continue;
			cnt++;
		}
		return cnt;
	}

	private static void printMap(PriorityQueue<Marble>[][] map) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]+"\t");
			}
			System.out.println("");
		}
		System.out.println("===============");
	}

	private static void moveMarbles() {
		PriorityQueue<Marble>[][] newMap = new PriorityQueue[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				newMap[r][c] = new PriorityQueue<Marble>();
			}
		}
		
		for (Marble marble : marbles) {
			if (marble.isDead)
				continue;
			moveMarble(marble, newMap);
		}
		
		map = newMap;
	}

	private static void moveMarble(Marble marble, PriorityQueue<Marble>[][] newMap) {
		int tmp = marble.v;
		int curr = marble.r;
		int curc = marble.c;
		int d = marble.d;

		while (tmp > 0) {
			int newr = curr + tmp * dr[d];
			int newc = curc + tmp * dc[d];

			if (isInRange(newr, newc)) {
				curr = newr;
				curc = newc;
				break;
			} else {
				if(d == 0) {
					tmp -= curr;
					curr = 0;
				}else if(d==1) {
					tmp -= (C-1-curc);
					curc = C-1;
				}else if(d==2) {
					tmp -= (R-1-curr);
					curr = R-1;
				}else if(d == 3) {
					tmp -= curc;
					curc = 0;
				}
				
				d += 2;
				d %= 4;
			}
		}
		marble.move(curr, curc, d);
		newMap[marble.r][marble.c].offer(marble);
		
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		else
			return true;
	}

	private static void deleteMarbles(int k) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c].size() <= k) continue;
				
				while(map[r][c].size() > k) {
					Marble tmp = map[r][c].poll();
					tmp.isDead = true;
				}
			}
		}
	}

	static class Marble implements Comparable<Marble>{
		int idx;
		int r, c, d, v;
		boolean isDead;

		public Marble(int idx, int r, int c, int d, int v) {
			super();
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.d = d;
			this.v = v;
			this.isDead = false;
		}

		public void move(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		@Override
		public int compareTo(Marble m) {
			if(this.v == m.v) return Integer.compare(this.idx, m.idx);
			return Integer.compare(this.v, m.v);
		}

		@Override
		public String toString() {
			return "M("+idx+")[d:" + d + ", v:" + v + "]";
		}
	}
}