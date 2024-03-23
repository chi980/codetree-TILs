import java.util.*;
import java.io.*;
public class Main {
static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int[] santaDr = { -1, 0, 1, 0 };
	static int[] santaDc = { 0, 1, 0, -1 };

	static int N, M, P, C, D;

	static Node rudolf;
	static Node[] santaList;
	static int[][] santaStatus;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		santaStatus = new int[N][N];

		st = new StringTokenizer(br.readLine());
		int Rr = Integer.parseInt(st.nextToken()) - 1;
		int Rc = Integer.parseInt(st.nextToken()) - 1;
		rudolf = new Node(Rr, Rc, C, -1, -2, 0, false);

		santaList = new Node[P + 1];
		for (int p = 1; p <= P; p++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int Sr = Integer.parseInt(st.nextToken()) - 1;
			int Sc = Integer.parseInt(st.nextToken()) - 1;
			santaList[idx] = new Node(Sr, Sc, 0, idx, -2, 0, false);
			santaStatus[Sr][Sc] = idx;
		}

		for (int m = 0; m < M; m++) {

			moveRudolf(m);
			moveSantas(m);

			// 게임종료(산타가 없는 경우 즉시 종료)
			boolean flag = false;
			for (int p = 1; p <= P; p++) {
				if (!santaList[p].is_out) {
					flag = true;
					// 탈락하지 않은 산타들에게 1점씩 추가로 부여
					santaList[p].score++;
				}
			}

			if (!flag)
				break;
		}
		
		StringBuilder sb =  new StringBuilder();
		for(int i=1;i<=P;i++) {
			sb.append(santaList[i].score).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static void printStatus() {
		System.out.println("루돌프(r, c): " + rudolf.r + ", " + rudolf.c);
		for (int i = 0; i < santaStatus.length; i++) {
			System.out.println(Arrays.toString(santaStatus[i]));
		}
		System.out.println();
		
		System.out.println(Arrays.toString(santaList));
	}

	private static void moveSantas(int turn) {
		for (int p = 1; p <= P; p++) {
			Node santa = santaList[p];
			if (santa.is_out)
				continue;
			if (turn - santa.lastFaint < 2)
				continue;

			int minD = -1;
			int minDeg = rudolf.getDeg(santa);
			int minR = santa.r;
			int minC = santa.c;

			for (int d = 0; d < 4; d++) {
				int nr = santa.r + santaDr[d];
				int nc = santa.c + santaDc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				if (santaStatus[nr][nc] != 0)
					continue;

				int deg = rudolf.getDeg(new Node(nr, nc));
				if (deg < minDeg) {
					minD = d;
					minDeg = deg;
					minR = nr;
					minC = nc;
				}
			}

			if(santaStatus[santa.r][santa.c] == santa.idx) santaStatus[santa.r][santa.c] = 0;
			santa.move(minR, minC, minD);
			santaStatus[santa.r][santa.c] = santa.idx;

			if (santa.r == rudolf.r && santa.c == rudolf.c) {
				interaction(santa.idx, (santa.d + 2) % 4, santaDr, santaDc, D, turn);
			}
		}

	}

	private static void moveRudolf(int turn) {
		// 가장 가까운 산타를 고른다.
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				int deg1 = rudolf.getDeg(o1);
				int deg2 = rudolf.getDeg(o2);

				if (Integer.compare(deg1, deg2) != 0)
					return Integer.compare(deg1, deg2);
				else {
					if (Integer.compare(o2.r, o1.r) != 0)
						return Integer.compare(o2.r, o1.r);
					else
						return Integer.compare(o2.c, o1.c);
				}
			}

		});
		for (int p = 1; p <= P; p++) {
			if (santaList[p].is_out)
				continue;

			pq.offer(santaList[p]);
		}
		Node nearestSanta = pq.poll();

		int minD = -1;
		int minDeg = Integer.MAX_VALUE;
		int minR = rudolf.r;
		int minC = rudolf.c;
		for (int d = 0; d < 8; d++) {
			int nr = rudolf.r + dr[d];
			int nc = rudolf.c + dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;

			int deg = nearestSanta.getDeg(new Node(nr, nc));
			if (deg < minDeg) {
				minD = d;
				minDeg = deg;
				minR = nr;
				minC = nc;
			}
		}

		rudolf.move(minR, minC, minD);

		if (santaStatus[rudolf.r][rudolf.c] != 0) {
			// 시작 산타, 방향, 방향dr, 방향dc, 점수
			interaction(santaStatus[rudolf.r][rudolf.c], rudolf.d, dr, dc, C, turn);
		}
	}

	private static void interaction(int startSantaIdx, int d, int[] dr, int[] dc, int score, int turn) {
		Node santa = santaList[startSantaIdx];
		santa.score += score;
		santa.lastFaint = turn;
		int deg = score;
		
		while (true) {
			int nr = santa.r + deg * dr[d];
			int nc = santa.c + deg * dc[d];
			deg = 1;

			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				if(santaStatus[santa.r][santa.c] == santa.idx) santaStatus[santa.r][santa.c] = 0;
				santa.move(nr, nc, d);
				santa.is_out = true;
				break;
			}

			if (santaStatus[nr][nc] == 0) {
				if(santaStatus[santa.r][santa.c] == santa.idx) santaStatus[santa.r][santa.c] = 0;
				santa.move(nr, nc, d);
				santaStatus[santa.r][santa.c] = santa.idx;
				break;
			} else {
				Node nextSanta = santaList[santaStatus[nr][nc]];
				if(santaStatus[santa.r][santa.c] == santa.idx) santaStatus[santa.r][santa.c] = 0;
				santa.move(nr, nc, d);
				santaStatus[santa.r][santa.c] = santa.idx;
				santa = nextSanta;
			}
		}
	}

	static class Node {

		int r, c, d, power, idx;
		int lastFaint;
		int score;
		boolean is_out;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Node(int r, int c, int power, int idx, int lastFaint, int score, boolean is_out) {
			this(r, c);
			this.d = 0;
			this.power = power;
			this.idx = idx;
			this.lastFaint = lastFaint;
			this.score = score;
			this.is_out = is_out;
		}

		public int getDeg(Node o) {
			int deg = (this.r - o.r) * (this.r - o.r) + (this.c - o.c) * (this.c - o.c);
			return deg;
		}

		public void move(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + ", power=" + power + ", idx=" + idx + ", lastFaint="
					+ lastFaint + ", score=" + score + ", is_out=" + is_out + "]";
		}



	}
}