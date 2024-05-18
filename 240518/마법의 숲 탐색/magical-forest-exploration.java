import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가장 위 1행, 아래 R행이라고 하지만 0행 R-1행으로 가정하자 ㅋ 처음 골렘의 위치가 중요한데 행을 -2로 세팅해야 한다 -> 모두
 * 주의해야 할 점은 golem의 r은 -2~R-2, c는 1~C-2 내에서만 존재할 수 있다. 움직인 후에도 -2~0행에 있다면 골렘의
 * 일부가 밖으로 나간것 -> 숲 리셋
 * 
 * @author 82108
 *
 */
public class Main {
	static final int EMPTY = 0;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static int R, C, K;
	static Golem[] golemList;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		K = Integer.parseInt(st.nextToken());

		golemList = new Golem[K + 1];
		for (int idx = 1; idx <= K; idx++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			golemList[idx] = new Golem(idx, c, d);
		}

		int sumSplitr = 0;
//		K = 1; // 지울 것
		for (int idx = 1; idx <= K; idx++) {
			Golem golem = golemList[idx];

			while (true) {
				// 남쪽으로 한 칸 내려감
				boolean canMoveSouth = moveSouth(golem);
				// 서쪽 방향으로 회전하면서 내려감
				if(!canMoveSouth) {
					boolean canMoveWest = moveWest(golem);
					// 동쪽 방향으로 회전하면서 내려감
					if(!canMoveWest) {
						boolean canMoveEast = moveEast(golem);
						if(!canMoveEast) break;
//						System.out.println("moveEast");
					}else {
//						System.out.println("moveWest");
					}
				}else {
//					System.out.println("moveSouth");
				}

			}
//			System.out.println("golem 상태");
//			System.out.println(golem);
			
			if(golem.isOut()) {
//				System.out.println("out 되었습니다.");
				resetMap();
			}
			else {
				// map에 기록하기 && 정령 이동
				map[golem.r][golem.c]= golem.idx;
				
				for(int d=0;d<4;d++) {
					map[golem.r+dr[d]][golem.c+dc[d]] = golem.idx;
				}
				
				// 정령 이동하기
				int splitR = moveSpirit(golem);
//				System.out.println("정령 이동: "+splitR);
				sumSplitr += (splitR+1);
			}
			
//			printMap();
//			System.out.println("==========================");
		}
		System.out.println(sumSplitr);
	}

	private static int moveSpirit(Golem golem) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new int[] {golem.r, golem.c});
		visited[golem.r][golem.c]= true;
		
		int maxr = golem.r;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			Golem curGolem = golemList[map[curr][curc]];
			
			int exitr = curGolem.r + dr[curGolem.d];
			int exitc = curGolem.c + dc[curGolem.d];
			
			boolean isExit = (curr == exitr) && (curc == exitc);
			
//			System.out.println("curr: "+curr+", curc: "+curc);
//			System.out.println("isExit: "+isExit);
			
			for(int d=0;d<4;d++) {
				int newr = curr+dr[d];
				int newc = curc+dc[d];
				
//				System.out.printf("newr: %d, newc: %d\n",newr, newc);
				if(newr < 0 || newc < 0 || newr >= R || newc >= C) continue;
				if(map[newr][newc] == EMPTY) continue;
				if(map[newr][newc] != map[curr][curc] && !isExit) {
//					System.out.println("달라서 움직임 불가");
					continue;
				}
				if(visited[newr][newc]) continue;
				visited[newr][newc] = true;
				maxr = Math.max(maxr, newr);
				q.offer(new int[] {newr, newc});
			}
		}
		
//		for(int i=0;i<R;i++) System.out.println(Arrays.toString(visited[i]));
		
		return maxr;
	}

	private static void printMap() {
		for(int i=0;i<map.length;i++) System.out.println(Arrays.toString(map[i]));
	}

	private static boolean moveEast(Golem golem) {
		int originr = golem.r;
		int originc = golem.c;
		
		int r = golem.r;
		int c = golem.c;
		
		r += dr[1];
		c += dc[1];
		
		if(!checkCenter(r, c)) return false;
		if(!checkEmpty(r, c)) return false;
		
		int d = 0;
		for(int i=0;i<3;i++) {
			int newr = r+dr[d];
			int newc = c+dc[d];
			
			if(!check(newr, newc)) return false;
			if(!checkEmpty(newr, newc)) return false;
			d += 1;
			d %= 4;
		}
		
		golem.move(r,c);
		boolean flag = moveSouth(golem);
//		System.out.println("moveEast flag"+flag);
		if(!flag) {
			golem.move(originr, originc);
			return false;
		}
		
		golem.rotateRight();
		
		return true;
	}

	private static boolean moveWest(Golem golem) {
		int originr = golem.r;
		int originc = golem.c;
		
		int r = golem.r;
		int c = golem.c;
		
		r += dr[3];
		c += dc[3];
		
		if(!checkCenter(r, c)) return false;
		if(!checkEmpty(r, c)) return false;
		
		int d = 2;
		for(int i=0;i<3;i++) {
			int newr = r+dr[d];
			int newc = c+dc[d];
			
			if(!check(newr, newc)) return false;
			if(!checkEmpty(newr, newc)) return false;
			d += 1;
			d %= 4;
		}
		
		golem.move(r,c);
		boolean flag = moveSouth(golem);
//		System.out.println("moveWest flag"+flag);
		if(!flag) {
			golem.move(originr, originc);
			return false;
		}
		golem.rotateLeft();
		
		return true;
	}

	private static boolean moveSouth(Golem golem) {
		int r = golem.r;
		int c = golem.c;

		r += dr[2];
		c += dc[2];

		if(!checkCenter(r, c)) return false;
		if(!checkEmpty(r, c)) return false;

		// 동남서를 확인
		for (int d = 1; d <= 3; d++) {
			int newr = r + dr[d];
			int newc = c + dc[d];

			if (!check(newr, newc))
				return false;
			if(!checkEmpty(newr, newc)) return false;
		}
		
		golem.move(r, c);

		return true;
	}

	private static boolean check(int r, int c) {
		if(r >= R || c < 0 || c >= C) return false;
		return true;
	}

	private static boolean checkCenter(int r, int c) {
		if(r < -2 || c < 1 || r >= R - 1 || c >= C-1) return false;
		return true;
	}

	private static boolean checkEmpty(int r, int c) {
		if(r >= 0 &&map[r][c] != EMPTY) return false;
		return true;
	}


	static void resetMap() {
		for (int i = 0; i < R; i++)
			Arrays.fill(map[i], EMPTY);
	}

	static class Golem {
		int idx; // golem의 번호
		int r, c; // golem의 행, 열
		int d; // 출구 위치

		public Golem(int idx, int c, int d) {
			super();
			this.idx = idx;
			this.r = -2;
			this.c = c;
			this.d = d;
		}

		public boolean isOut() {
			if (r <= 0)
				return true; // -2 ~ 0행에 있다면 일부가 밖으로 나간 것
			return false;
		}

		public void move(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void rotateLeft() {
			this.d += 3;
			this.d %= 4;
		}
		
		public void rotateRight() {
			this.d += 1;
			this.d %= 4;
		}

		@Override
		public String toString() {
			return "Golem [idx=" + idx + ", r=" + r + ", c=" + c + ", d=" + d + "]";
		}
		
	}
}