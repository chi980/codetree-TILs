import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 포탑 맵 최근 공격 시점 맵 공격 영향(항상 갱신)
 * 
 * 1. 공격자 선정 행과 열의 합이 같을 떄 열 값이 클려면 행이 적은 순대로 2. 공격자 공격 행과 열의 합이 같을 때 열 값이 작으려면
 * 행이 큰 순대로 2-1. 레이저 공격(BFS, 부서진 포탑 X, 가장자리에서 막힌 방향이라면 반대로) 경로의 길이가 똑같은 최단경로가 2개
 * 이상이다 -> 우하좌상..
 * 
 * 피해를 입은 포탑은 공격력 줄어들고, 레이저 경로 포탑은 공격력/2의 공격받음 2-2. 포탄 공격(레이저가 안될 떄) 공격자 공격력,
 * 8방향은 공격력/2(반대로)
 * 
 * 3. 포탑 부서짐 (<0인 포탑을 0으로 벼녁ㅇ)
 * 
 * 4. 포탑 정비(공격과 무관한 포탑은 공격력++), 공격자도 아니고 공격대상도 아닌 포탑들
 * 
 * 
 * @author 82108
 *
 */
public class Main {
		static int[] dr, dc;
	static int[] ddr, ddc;
//	static int[] dr = { -1, 0, 1, 0 };
//	static int[] dc = { 0, 1, 0, -1 };

	static final int DEAD = 0;

	static int R, C;
	static int[][] map;
	static int[][] attackTime;
	static boolean[][] isInAttack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		dr = new int[] { R-1, 0, 1, 0 };
		dc = new int[]  { 0, 1, 0, C-1 };
		
		ddr = new int[] {R-1 , R-1, R-1, 0, 1, 1, 1, 0};
		ddc = new int[] {C-1, 0, 1, 1, 1, 0, C-1, C-1};

		map = new int[R][C];
		attackTime = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
//		for(int r=0;r<R;r++) Arrays.fill(attackTime[r], -1);

		for (int time = 1; time <= K; time++) {
			isInAttack = new boolean[R][C];
			int[] attacker = pickAttacker(time);
//			System.out.println("attacker: " + Arrays.toString(attacker));
//			printMap(attackTime);
			int[] attacked = pickAttacked(attacker);
//			printArr(isInAttack);

//			System.out.println("공격전");
//			printMap(map);
			attack(attacker, attacked);
			
//			System.out.println("공격후");
//			printMap(map);
			
			crack();
			
			
//			System.out.println("부서진후");
//			printMap(map);
//			printArr(isInAttack);
			
			fix();
//			System.out.println("정비후");
//			printMap(map);
			
		}
		int strongest = getStrong();
		System.out.println(strongest);
	}

	private static int getStrong() {
		int result = 0;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				result = Math.max(result, map[r][c]);
			}
		}
		return result;
	}

	private static void fix() {
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c] == DEAD || isInAttack[r][c]) continue;
				
				map[r][c]++;
			}
		}
	}

	private static void crack() {
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c] < DEAD) map[r][c] = DEAD;
			}
		}
	}

	private static void attack(int[] attacker, int[] attacked) {
		// 레이저 공격을 먼저 시도 한뒤
		// 안되면 포탄 공격을 시도한다.

		boolean canLaserAttack = laserAttack(attacker, attacked);
		if (!canLaserAttack)
			cannonAttack(attacker, attacked);
	}

	private static void cannonAttack(int[] attacker, int[] attacked) {
		map[attacked[0]][attacked[1]] -= attacker[2];
		
		for(int d=0;d<8;d++) {
			int newr = (attacked[0] + ddr[d])%R;
			int newc = (attacked[1] + ddc[d])%C;
			
			map[newr][newc] -= (attacker[2]/2);
			isInAttack[newr][newc] = true;
		}
	}

	private static boolean laserAttack(int[] attacker, int[] attacked) {
		// 상하좌우, 포탄이 있는 곳으로 이동 가능
		// 우하좌상 우선순위대로, 경로를 찾기 위해서는 상

		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new ArrayDeque<>();
		int[][][] prev = new int[R][C][2];

		visited[attacker[0]][attacker[1]] = true;
		q.offer(new int[] { attacker[0], attacker[1] });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			// 도착지면 break;
			if (curr == attacked[0] && curc == attacked[1])
				break;


			int d = 1;
			for (int i = 0; i < 4; i++) {
				int newr = (curr+dr[d])%R;
				int newc = (curc+dc[d])%C;

				d+=1;
				d%=4;
				if (map[newr][newc] == DEAD)
					continue;
				if (visited[newr][newc])
					continue;

				visited[newr][newc] = true;
				q.offer(new int[] { newr, newc });
				prev[newr][newc][0] = curr;
				prev[newr][newc][1] = curc;

//				printArr(visited);
			}
		}

		if (!visited[attacked[0]][attacked[1]])
			return false;

		// 경로 찾기
		
		int curr = attacked[0];
		int curc = attacked[1];
		map[curr][curc] -= attacker[2];
		while(prev[curr][curc][0] != attacker[0] || prev[curr][curc][1] != attacker[1]) {
			int newr = prev[curr][curc][0];
			int newc = prev[curr][curc][1];
			
			map[newr][newc] -= (attacker[2]/2);
			isInAttack[newr][newc] = true;
			
			curr = newr;
			curc = newc;
		}

		return true;
	}

	private static int[] pickAttacked(int[] attacker) {
		// 공격대상 선정(단, 공격자 제외)
		// isInAttack에 표시

		// 차례대로 r,c, 공격력(커야함), 공격시점(작아야함)
		int[] attacked = new int[] { -1, -1, 0, Integer.MAX_VALUE };

		for (int r = R - 1; r >= 0; r--) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == DEAD || (r == attacker[0] && c == attacker[1]))
					continue;

				if (attacked[2] < map[r][c]) {
					attacked[0] = r;
					attacked[1] = c;
					attacked[2] = map[r][c];
					attacked[3] = attackTime[r][c];
				} else if (attacked[2] == map[r][c]) {
					if (attacked[3] > attackTime[r][c]) {
						attacked[0] = r;
						attacked[1] = c;
						attacked[2] = map[r][c];
						attacked[3] = attackTime[r][c];
					} else if (attacked[3] == attackTime[r][c]) {
						if (attacked[0] + attacked[1] > r + c) {
							attacked[0] = r;
							attacked[1] = c;
							attacked[2] = map[r][c];
							attacked[3] = attackTime[r][c];
						}
					}
				}
			}
		}

		isInAttack[attacked[0]][attacked[1]] = true;

		return attacked;
	}

	private static int[] pickAttacker(int curTime) {
		// 공격자를 선정한뒤
		// isInAttack에 표시
		// attackTime에 표시
		// 공격력 증가

		// 차례대로 r,c,공격력(작아야함), 공격시점(커야함)
		int[] attacker = new int[] { -1, -1, Integer.MAX_VALUE, -1 };

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == DEAD)
					continue;

				if (attacker[2] > map[r][c]) {
					attacker[0] = r;
					attacker[1] = c;
					attacker[2] = map[r][c];
					attacker[3] = attackTime[r][c];
				} else if (attacker[2] == map[r][c]) {
					if (attacker[3] < attackTime[r][c]) {
						attacker[0] = r;
						attacker[1] = c;
						attacker[2] = map[r][c];
						attacker[3] = attackTime[r][c];
					} else if (attacker[3] == attackTime[r][c]) {
						if (attacker[0] + attacker[1] < r + c) {
							attacker[0] = r;
							attacker[1] = c;
							attacker[2] = map[r][c];
							attacker[3] = attackTime[r][c];
						}
					}
					// 열값이 가잔 큰 포탑은 처리하지 않아도 된다.
					// 왜냐하면 행과 열의 합이 같을 때 열 값이 가장 크려면 행값이 가장 작아야 하는데
					// 현재 함수에서 r을 0 -> R로 돌기 때문에 OK
				}
			}
		}

		isInAttack[attacker[0]][attacker[1]] = true;
		attackTime[attacker[0]][attacker[1]] = curTime;
		map[attacker[0]][attacker[1]] += (R + C);
		attacker[2] = map[attacker[0]][attacker[1]];
		return attacker;
	}

	static void printMap(int[][] map) {
		Arrays.stream(map).forEach(row -> System.out.println(Arrays.toString(row)));
		System.out.println("----------------------");
	}

	static void printArr(boolean[][] arr) {
		Arrays.stream(arr).forEach(row -> System.out.println(Arrays.toString(row)));
		System.out.println("--------------------------");
	}
}