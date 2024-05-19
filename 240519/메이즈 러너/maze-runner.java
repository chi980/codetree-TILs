import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 미로는 NxN -> 1,1 ~ r,c 미로의 각 칸은 빈칸 or 벽 or 출구 벽은 내구도(1~9)를 가지고 있다. 내구도는 회전할 때
 * 1씩 깎인다.
 * 
 * 참가자는 1초마다 상하좌우로 한 칸씩 움직인다.(모든 참가자는 동시에 움직임) 참가자는 빈칸으로만 이동 가능하다 움직인 칸은 현재 머물러
 * 있던 칸보다 출구까지의 최단 거리가 가까워야 한다.(같다면 상하 우선) 참가자가 움직일 수 없는 상황이라면 정지 한 칸에 2명이 있을 수도
 * 있다.
 * 
 * 참가자 이동이 끝났다면 미로가 회전한다. 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형 만약 위의 조건을 만족하는 정사각형이
 * 2개 이상이라면 r좌표가 작은 것 우선, 그래도 같으면 c좌표가 작은 것 우선 선택된 정사각형은 시계방향으로 90됴 회전, 회전된 벽은
 * 내구도가 1씩 깎임
 * 
 * 이는 K번 반복, 단 모든 참가자가 탈출한다면 게임은 끝남
 * 
 * 모든 참가자의 이동 거리 합과 출구 좌표를 출력하자
 * 
 * @author 82108
 *
 */
public class Main {
		// 상하 우선
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static final int EMPTY = 0;
	static int R, C;
	static int[][] maze;
	static int participantCnt;
	static Participant[] participantList;
	static int exitR, exitC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		R = C = N;
		participantCnt = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		maze = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				maze[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		participantList = new Participant[participantCnt + 1];
		for (int idx = 1; idx <= participantCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			participantList[idx] = new Participant(r, c);
		}

		st = new StringTokenizer(br.readLine());
		exitR = Integer.parseInt(st.nextToken()) - 1;
		exitC = Integer.parseInt(st.nextToken()) - 1;
		
//		printStatus(maze, participantList);

//		K=1;
		for (int k = 0; k < K; k++) {
			// 1. 참가자 이동
			for (int idx = 1; idx < participantList.length; idx++) {
				Participant p = participantList[idx];
				if (p.isOut)
					continue;
				moveParticipant(p);
			}
//			System.out.println("1. 참가자 이동 후");
//			printStatus(maze, participantList);
			if (isAllOut(participantList)) {
				break;
			}
			// 2. 미로 회전
			rotateMaze(maze, participantList);
//			System.out.println("2. 미로 회전 후");
//			printStatus(maze, participantList);
			

		}
		
		int lenSum = getLenSum(participantList);
		System.out.println(lenSum);
		System.out.printf("%d %d", exitR+1, exitC+1);

	}

	private static int getLenSum(Participant[] participantList) {
		int sum = 0;
		for(int idx=1;idx<participantList.length;idx++) {
			sum+=participantList[idx].deg;
		}
		return sum;
	}

	private static void printStatus(int[][] maze, Participant[] participantList) {
		System.out.println("maze 상황");
		for(int i=0;i<maze.length;i++) System.out.println(Arrays.toString(maze[i]));
		System.out.println("-----------------------------------------");
		System.out.println("participantList 상황");
		System.out.println(Arrays.toString(participantList));
		StringBuilder sb = new StringBuilder();
		List[][] participantStatus = new ArrayList[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				participantStatus[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int idx=1;idx<participantList.length;idx++) {
			Participant p = participantList[idx];
			if(p.isOut) continue;
			
			participantStatus[p.r][p.c].add(idx);
		}
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.printf("%s\t",participantStatus[i][j].size()==0?i!=exitR || j!=exitC?"-":"E":participantStatus[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("=========================================");
	}

	private static void moveParticipant(Participant participant) {
		int curr = participant.r;
		int curc = participant.c;

		int minr = curr;
		int minc = curc;
		int minDeg = getDeg(minr, minc, exitR, exitC);
		for (int d = 0; d < 4; d++) {
			int newr = curr + dr[d];
			int newc = curc + dc[d];

			if (!check(newr, newc))
				continue;
			if(maze[newr][newc] != EMPTY) continue;

			int newDeg = getDeg(newr, newc, exitR, exitC);

			if (newDeg < minDeg) {
				minr = newr;
				minc = newc;
				minDeg = newDeg;
			}
		}
		
		
		if(minr != curr || minc != curc)participant.move(minr, minc, participant.deg+1);
		else participant.move(minr, minc, participant.deg);
		
		if(participant.r == exitR && participant.c == exitC) participant.isOut = true;
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static void rotateMaze(int[][] maze, Participant[] participantList) {
		int minr = -1;
		int minc = -1;
		int minLen = 11; // R, C 는 1~10까지 수 가장 작은 정사각형은 10X10
		
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				for(int len = 1;r+len<R && c+len<C;len++) {
					if(!contains(r, c, r+len, c+len, exitR, exitC)) continue;
					
					boolean flag = false;
					for(int idx = 1; idx<participantList.length;idx++) {
						Participant p = participantList[idx];
						if(p.isOut)
							continue;
						
						if(contains(r, c, r+len, c+len, p.r, p.c)) {
							flag = true;
							break;
						}
					}
					if(!flag) continue;
					
					if(len < minLen) {
						minLen = len;
						minr = r;
						minc = c;
					}
					
				}
			}
		}
		rotate(maze, minr, minc, minLen+1);
	}

	private static void rotate(int[][] maze, int topr, int topc, int len) {
		
		int[][] tmpMaze = new int[len][len];
		

		
		for(int r=0;r<len;r++) {
			for(int c=0;c<len;c++) {
				tmpMaze[r][c] = maze[topr+r][topc+c];
				if(tmpMaze[r][c] >= 1) tmpMaze[r][c]--;
			}
		}
		
		for(int r=0;r<len;r++) {
			for(int c=0;c<len;c++) {
				maze[topr+c][topc+len-1-r]= tmpMaze[r][c];
			}
		}
		
		Participant exit = new Participant(exitR, exitC);
		exit.rotate(topr, topc, len);
		exitR = exit.r;
		exitC = exit.c;
		
		for(int idx=1;idx<participantList.length;idx++) {
			Participant p = participantList[idx];
			if(p.isOut) continue;
			if(!contains(topr, topc, topr+len-1, topc+len-1, p.r, p.c)) continue;
			p.rotate(topr, topc, len);
		}
	}

	private static boolean contains(int topr, int topc, int bottomr, int bottomc, int itemr, int itemc) {
		boolean flag = true;
		
		flag = flag && (topr <= itemr);
		flag = flag && (itemr <= bottomr);
		flag = flag && (topc <= itemc);
		flag = flag && (itemc <= bottomc);
		
		return flag;
	}

	private static boolean isAllOut(Participant[] participantList) {
		for (int idx = 1; idx < participantList.length; idx++) {
			if (!participantList[idx].isOut)
				return false;
		}
		return true;
	}

	static int getDeg(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

	static class Participant {
		int r, c;
		int deg;
		boolean isOut;

		public Participant(int r, int c) {
			this.r = r;
			this.c = c;
			this.deg = 0;
			this.isOut = false;
		}

		public void move(int r, int c, int deg) {
			this.r = r;
			this.c = c;
			this.deg = deg;
		}
		
		public void rotate(int topr, int topc, int len) {
			
			int newr = this.r - topr;
			int newc = this.c - topc;
			
			int tmp = newc;
			newc = topc + len - 1 - newr;
			newr = topr + tmp;
			
			this.r = newr;
			this.c = newc;
		}

		@Override
		public String toString() {
			return "Participant [r=" + r + ", c=" + c + ", deg=" + deg + ", isOut=" + isOut + "]";
		}
	}
}