import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static int N, M, K;
	static int[][][] maze;
	static Participant[] participantList;
	static int exitR, exitC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		maze = new int[M + 1][N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maze[0][i][j] = Integer.parseInt(st.nextToken());
			}
		}

		participantList = new Participant[M + 1];
		for (int idx = 1; idx <= M; idx++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			participantList[idx] = new Participant(idx, r, c);
			maze[idx][r][c] = 1;
		}

		st = new StringTokenizer(br.readLine());
		exitR = Integer.parseInt(st.nextToken()) - 1;
		exitC = Integer.parseInt(st.nextToken()) - 1;

		for (int k = 0; k < K; k++) {
			moveParticipants();
//			printParitcipant();
//			printMaze();
//			System.out.println("============================1");
			if (checkAllOut()) {
				break;
			}
			rotateMaze();
//			printParitcipant();
//			printMaze();
//			System.out.println("============================2");
		}
		
		int degSum = getDegSum();
		System.out.println(degSum);
		System.out.printf("%d %d", exitR+1, exitC+1);
		
	}

	private static int getDegSum() {
		int sum = 0;
		for (int idx = 1; idx <= M; idx++) {
			Participant participant = participantList[idx];
			sum += participant.deg;
			sum -= 1;
		}
		
		return sum;
	}

	private static void moveParticipants() {
		for (int idx = 1; idx <= M; idx++) {
			Participant participant = participantList[idx];
			if (participant.is_out)
				continue;

//			System.out.println("이동전: " + participant);

			int sr = participant.r;
			int sc = participant.c;
			int sDeg = getDeg(sr, exitR, sc, exitC);
//			System.out.println("현재 거리: "+sDeg);

			for (int d = 0; d < 4; d++) {
				int nr = participant.r + dr[d];
				int nc = participant.c + dc[d];

//				System.out.println("새(r,c) " + nr + ", " + nc);

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				if (maze[0][nr][nc] != 0) {
//					System.out.println("벽");
					continue;
				}

				int nDeg = getDeg(nr, exitR, nc, exitC);
//				System.out.println("새 거리: " + nDeg);
				// 조건식 다시 체크하기
				if (nDeg < sDeg) {
//					System.out.println("새 거리가 짧으므로 갱신합니다.");
					sr = nr;
					sc = nc;
					sDeg = nDeg;
				}

			}

			if (sr != participant.r || sc != participant.c) {
				maze[idx][sr][sc] = maze[idx][participant.r][participant.c] + 1;
				participant.move(sr, sc);
				if (sr == exitR && sc == exitC)
					participant.exit();
			}else {
//				System.out.println("움직이지 않았습니다.");
			}

//			System.out.println("이동 후:" + participant);

		}
	}

	private static void rotateMaze() {
		// 정사각형 구하기
		// 가장 최소 거리에 있는 참가자
		PriorityQueue<Participant> pq = new PriorityQueue<>(new Comparator<Participant>() {

			@Override
			public int compare(Participant p1, Participant p2) {
				int deg1 = getDeg(p1.r, exitR, p1.c, exitC);
				int deg2 = getDeg(p2.r, exitR, p2.c, exitC);

				if (deg1 != deg2)
					return Integer.compare(deg1, deg2);
				else {
					if (p1.r != p2.r)
						return Integer.compare(p1.r, p2.r);
					else
						return Integer.compare(p1.c, p2.c);
				}
			}

		});
		for (int idx = 1; idx <= M; idx++) {
			if(participantList[idx].is_out) continue;
			pq.offer(participantList[idx]);
		}
		Participant nearestParticipant = pq.poll();
		int nearestDeg = getDeg(nearestParticipant.r, exitR, nearestParticipant.c, exitC);

		// 좌측 상단 r,c
		int topR = exitR;
		int topC = exitC;


		if (exitR != nearestParticipant.r) {
			topR = Math.min(nearestParticipant.r, exitR);
		} else {
			if (exitR - nearestDeg >= 0)
				topR = exitR - nearestDeg;
			else topR = 0;
		}

		if (exitC != nearestParticipant.c) {
			topC = Math.min(nearestParticipant.c, exitC);
		} else {
			if (exitC - nearestDeg >= 0)
				topC = exitC - nearestDeg;
			else topC = 0;
		}

		int bottomR = topR+nearestDeg;
		int bottomC = topC+nearestDeg;
//		System.out.println("선택한 참가자" + nearestParticipant);

//		System.out.println("현재 좌측상단(r,c): " + topR + ", " + topC);
//		System.out.println("현재 우측하다(r,c): " + bottomR + ", " + bottomC);

		// 회전
		// maze와 모든 참가자의 배열 회전
		// 참가자 r,c갱신, exit r,c갱신
		List<Integer> nodeToRotate = new ArrayList<>();
		nodeToRotate.add(0);
		for (int idx = 1; idx <= M; idx++) {
			Participant p = participantList[idx];
			if(p.is_out) continue;
			if (p.r < topR || p.c < topC || p.r > bottomR || p.c > bottomC)
				continue;
			nodeToRotate.add(idx);
		}
//		System.out.println("현재 회전판 안에 있는 사람: " + nodeToRotate);

		for (int idx : nodeToRotate) {

			int[][] tmp = new int[nearestDeg + 1][nearestDeg + 1];

			for (int nr = topR; nr <= bottomR; nr++) {
				for (int nc = topC; nc <= bottomC; nc++) {
					tmp[nr-topR][nc-topC] = maze[idx][nr][nc];
				}
			}
			
			tmp = rotateArr(tmp, nearestDeg+1);
			
			for(int nr=topR;nr<=bottomR;nr++) {
				for(int nc=topC;nc<=bottomC;nc++) {
					maze[idx][nr][nc] = tmp[nr-topR][nc-topC];
				}
			}
			
			if(idx == 0) {
				int [] newExitRC = rotateRC(new int[] {exitR, exitC}, topR, topC, nearestDeg+1);
				exitR = newExitRC[0];
				exitC = newExitRC[1];
			}else {
				Participant p = participantList[idx];
				int [] newRC = rotateRC(new int[] {p.r, p.c}, topR, topC, nearestDeg+1);
				p.rotate(newRC[0], newRC[1]);
			}
		}
		
		for(int nr = topR;nr<=bottomR;nr++) {
			for(int nc = topC;nc<=bottomC;nc++) {
				if(maze[0][nr][nc] > 0)maze[0][nr][nc]--;
			}
		}
		
		
	}
	
	private static int[] rotateRC(int[] rc, int topR, int topC, int N) {
		rc[0] -= topR;
		rc[1] -= topC;
		
		int tmp = rc[1];
		rc[1] = N-1-rc[0];
		rc[0] = tmp;
		
		rc[0] +=topR;
		rc[1] += topC;
		
		return rc;
	}

	private static int[][] rotateArr(int[][] arr, int N) {
		int newArr[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newArr[j][N - 1 - i] = arr[i][j];
			}
		}

//		System.out.println("회전전");
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(arr[i]));
//		System.out.println("회전후");
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(newArr[i]));

		return newArr;
	}

	private static boolean checkAllOut() {
		for (int idx = 1; idx <= M; idx++) {
			Participant participant = participantList[idx];
			if (!participant.is_out)
				return false;
		}
		return true;
	}

	public static int getDeg(int r1, int r2, int c1, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

	static class Participant {
		int idx;
		int r, c;
		int deg;
		boolean is_out;

		public Participant(int idx, int r, int c) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.deg = 1;
			this.is_out = false;
		}

		public void move(int nr, int nc) {
			this.r = nr;
			this.c = nc;
			this.deg++;
		}

		public void rotate(int nr, int nc) {
			this.r = nr;
			this.c = nc;
		}
		
		public void exit() {
			this.is_out = true;
		}

		@Override
		public String toString() {
			return "Participant [idx=" + idx + ", r=" + r + ", c=" + c + ", deg=" + deg + ", is_out=" + is_out + "]";
		}

	}

	public static void printMaze() {
		System.out.println("*****************************************");
		System.out.println("출구(r,c): " + exitR + ", " + exitC);
		for (int i = 0; i < N; i++)
			System.out.println(Arrays.toString(maze[0][i]));
		System.out.println("*****************************************");
	}

	public static void printParitcipant() {
		for (int idx = 1; idx <= M; idx++) {
			Participant p = participantList[idx];
			System.out.println("참가자" + idx + "의 현재 상태");
			System.out.println(p);
			System.out.println("출구와의 거리: " + getDeg(p.r, exitR, p.c, exitC));
			for (int i = 0; i < N; i++)
				System.out.println(Arrays.toString(maze[idx][i]));
			System.out.println("-----------------------------------------");
		}
	}
}