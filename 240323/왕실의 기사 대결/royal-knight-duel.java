import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

		static int L, N, Q;
	static final int EMPTY = 0;
	static final int TRAP = 1;
	static final int WALL = 2;

	static int[][] chess;
	static int[][] knightStatus;
	static Knight[] knightList;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		chess = new int[L][L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < L; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		knightStatus = new int[L][L];
		knightList = new Knight[N + 1];
		int[] originK = new int[N+1];
		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			originK[idx] = k;

			knightList[idx] = new Knight(idx, r, c, h, w, k, 0,true);
			for (int tmpr = r; tmpr < r + h; tmpr++) {
				for (int tmpc = c; tmpc < c + w; tmpc++) {
					knightStatus[tmpr][tmpc] = idx;
				}
			}

		}

//		for (int i = 0; i < L; i++)
//			System.out.println(Arrays.toString(knightStatus[i]));

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			moveKnight(idx, d);
		}
		
		int totalDem = 0;
		for(int idx=1;idx<knightList.length;idx++) {
			Knight knight = knightList[idx];
			if(!knight.is_alive) continue;
			
			totalDem += knight.dem;
		}
		
		System.out.println(totalDem);
		

	}

	private static void moveKnight(int idx, int d) {
		boolean[] isMove = new boolean[knightList.length];
		isMove[idx] = true;

		Queue<Knight> q = new ArrayDeque<>();
		q.offer(knightList[idx]);
		
		boolean flag = true;
		List<Knight> movedKnightList = new ArrayList<>();

		A: while (!q.isEmpty()) {
//			System.out.println("is_move: "+Arrays.toString(isMove));
			Knight curKnight = q.poll();

			// 기사를 이동시킬 수 없는 경우: 격자판 밖 or 벽
			int nr = curKnight.r + dr[d];
			int nc = curKnight.c + dc[d];
			

			// 기사가 밖으로 나가는 경우 -> 움직이지 못함 & 갱신 불가
			if (nr < 0 || nc < 0 || nr + curKnight.h > L || nc + curKnight.w > L) {
//				System.out.println(curKnight.idx+"번째 기사가 밖으로 나갔어요");
				flag = false;
				break A;
			}

			
			int ndem = 0;
			for (int tmpr = nr; tmpr < nr + curKnight.h; tmpr++) {
				for (int tmpc = nc; tmpc < nc + curKnight.w; tmpc++) {
					// 기사가 벽에 부딪히는 경우 -> 움직이지 못함 && 갱신 불가
					if(chess[tmpr][tmpc] == WALL) {

//						System.out.println(curKnight.idx+"번째 기사가 벽으로 부딪혔어요");
						flag = false;
						break A;
					}
					else if(chess[tmpr][tmpc] == TRAP) ndem++;
					
					if(knightStatus[tmpr][tmpc] != 0 && !isMove[knightStatus[tmpr][tmpc]]) {
						isMove[knightStatus[tmpr][tmpc]] = true;
						q.offer(knightList[knightStatus[tmpr][tmpc]]);
					}
				}
			}
			
			if(curKnight.idx == idx) ndem = 0;
			Knight newKnight = new Knight(curKnight.idx, nr, nc,curKnight.h, curKnight.w, curKnight.k,curKnight.dem + ndem,true);
			if(newKnight.k - newKnight.dem <= 0) newKnight.is_alive = false;
			movedKnightList.add(newKnight);
			
		}
		
		// 갱신
		if(flag){
//			System.out.println(Arrays.toString(knightList));
//			System.out.println(movedKnightList);
			for(Knight knight : movedKnightList) {
				knightList[knight.idx] = knight;
			}
			
//			System.out.println("갱신 후");
//			System.out.println(Arrays.toString(knightList));
			
			for(int i=0;i<L;i++) Arrays.fill(knightStatus[i], 0);
			for(int i=1;i<knightList.length;i++) {
				Knight knight = knightList[i];
				if(!knight.is_alive) continue;
				for (int tmpr = knight.r; tmpr < knight.r + knight.h; tmpr++) {
					for (int tmpc = knight.c; tmpc < knight.c + knight.w; tmpc++) {
						knightStatus[tmpr][tmpc] = knight.idx;
					}
				}
				
			}
			

//			for (int i = 0; i < L; i++)
//				System.out.println(Arrays.toString(knightStatus[i]));
		}
//		System.out.println("-----------------------------------------");

	}

	static class Knight {
		int idx;
		int r, c, h, w, k;
		int dem;
		boolean is_alive;

		public Knight(int idx, int r, int c, int h, int w, int k, int dem, boolean is_alive) {
			this(r, c, k, is_alive);
			this.idx = idx;
			this.h = h;
			this.w = w;
			this.dem = dem;
		}
		
		public Knight(int r, int c, int k, boolean is_alive) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.is_alive = is_alive;
		}

		public void move(int nr, int nc) {
			this.r = nr;
			this.c = nc;
		}

		@Override
		public String toString() {
			return "Knight [idx=" + idx + ", r=" + r + ", c=" + c + ", h=" + h + ", w=" + w + ", k=" + k + ", dem="
					+ dem + ", is_alive=" + is_alive + "]";
		}

	}
}