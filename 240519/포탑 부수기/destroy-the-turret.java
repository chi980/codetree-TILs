import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 1. 공격자 선정..(공격자는 R+C만큼의 공격력 증가)
 * 	 가장 약한 포탑 선정 -> 주의해야 할 점: 가장 최근 공격 -> 행과 열 합 큰 순 -> 열 큰순
 * 2. 공격자 공격
 *   가장 강한 포탑 공격 -> 주의해야 할 점: 가장 오래된 공격 -> 행과 열 함 작은 순 -> 열 작은 순
 * 3. 공격
 * 	3-1. 레이저 공격 우하좌상(좌상우하 순으로 다시 트래킹) 순으로 움직인 뒤, 경로 상 포탑은 -공격력/2, 공격대상 - 공격력
 *   // 단, 공격자는 공격을 입지 않는다!!
 *  3-2. 3-1이 불가능할 경우 8방향 주위 포탑 -공격력/2, 공격대상 - 공격자
 *   // 단, 공격자는 공격을 입지 않는다!!
 *   
 * 4. 공격이 끝났다면 공격과 무관한 포탑은 공격력+1(위 과정과 모두 상관 없는 포탑들)  
 * @author 82108
 *
 */
public class Main {
	static final int DEAD = 0;
	
	static int[] raizerDr;
	static int[] raizerDc;
	
	static int[] bombDr;
	static int[] bombDc;
	
	static int R, C, K;
	static int[][] map;
	static int[][] attackTime; // 가장 마지막에 공격한 시점
	static boolean[][] attackVisited; // 공격 대상자/피대상자인지

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		raizerDr = new int[]{0,1,0,R-1};
		raizerDc = new int[]{1, 0, C-1, 0};
		
		bombDr = new int[] {R-1,R-1,R-1,0,1,1,1,0};
		bombDc = new int[] {C-1,0,1,1,1,0,C-1,C-1};
		
		map = new int[R][C];
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		attackTime = new int[R][C];
		attackVisited = new boolean[R][C];
		// 궁금한 점..: 만약 모든 포탑이 부서졌다면? -> 그럴일은 없음 항상 공격자에게 R+C만큼의 공격력을 주기 때문
		for(int time=1;time<=K;time++) {
			reset(attackVisited);
			
			int[] attacker = selectAttacker(time, R+C);
			
//			System.out.println("1. 공격자 선정 후");
//			System.out.println("공격자");
//			System.out.println(Arrays.toString(attacker));
//			printStatus();
			
			int[] attacked = selectAttacked(time, attacker);
			if(attacked[0] == -1 && attacked[1] == -1) continue;
			
//			System.out.println("2. 피공격자 선정");
//			System.out.println("피공격자");
//			System.out.println(Arrays.toString(attacked));
//			printStatus();
//			
//			System.out.println("3. 공격");
			attack(attacker, attacked);
//			printStatus();
			
//			System.out.println("4. 포탑 정비");
			fixTop();
//			printStatus();
			
//			System.out.println("----------------------------------------");
		}
		
		int maxPower = getMaxPower();
		System.out.println(maxPower);
	}

	private static int getMaxPower() {
		int max = 0;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(max < map[r][c]) max = Math.max(max, map[r][c]);
			}
		}
		return max;
	}

	private static void fixTop() {
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c] == DEAD) continue;
				if(attackVisited[r][c]) continue;
				
				map[r][c] += 1;
			}
		}
	}

	private static void attack(int[] attacker, int[] attacked) {
		boolean canRaizerAttack = raizerAttack(attacker, attacked);
		if(!canRaizerAttack) bombAttack(attacker,attacked);
	}

	private static boolean bombAttack(int[] attacker, int[] attacked) {
//		System.out.println("포탄 공격을 시작합니다.");
		int curr = attacked[0];
		int curc = attacked[1];
		int power = map[attacker[0]][attacker[1]];
		
		for(int d=0;d<8;d++) {
			int newr =  curr + bombDr[d];
			newr %= R;
			int newc = curc + bombDc[d];
			newc %=C;
			
			attackTop(newr, newc, power/2);
		}
		
		attackTop(curr, curc, power);
		
		
		return true;
	}

	private static void attackTop(int r, int c, int power) {
		map[r][c] -= power;
		
		if(map[r][c] < 0) map[r][c] = DEAD;
	}

	private static boolean raizerAttack(int[] attacker, int[] attacked) {
//		System.out.println("레이저 공격을 시작합니다.");
		int power = map[attacker[0]][attacker[1]];
		
		int[][] visited = new int[R][C];
		Queue<int[]> q= new ArrayDeque<>();
		
		visited[attacker[0]][attacker[1]] = 1;
		q.offer(attacker);
		
		boolean isArrive = false;
		int len = 1;
		
		
		A: while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			for(int d=0;d<4;d++) {
				int newr = curr + raizerDr[d];
				newr %= R;
				int newc = curc + raizerDc[d];
				newc %= C;
				
				if(map[newr][newc] == DEAD) continue;
				if(visited[newr][newc] != 0) continue;
				
				visited[newr][newc] = visited[curr][curc] + 1;
				
				if(newr == attacked[0] && newc == attacked[1]) {
					isArrive = true;
					len = visited[newr][newc];
					break A;
				}
				q.offer(new int[] {newr, newc});
			}
		}
		
//		System.out.println("레이저 공격 결과");
		if(isArrive) {
//			System.out.println("공격 성공");
//			print(visited);
			
			int r = attacker[0];
			int c = attacker[1];
			
			while(visited[r][c] != len) {
				
				for(int d=0;d<4;d++) {
					int newr = r+raizerDr[d];
					newr %=R;
					int newc = c+raizerDc[d];
					newc %=C;
					
					if(visited[r][c] == visited[newr][newc]-1) {
						if(visited[newr][newc]!=len) {
							attackTop(newr, newc, power/2);
							attackVisited[newr][newc] = true;
						}
						r = newr;
						c = newc;
						break;
					}
				}
			}
			
			attackTop(attacked[0], attacked[1], power);
		}else {
//			System.out.println("공격 실패");
		}
//		System.out.println("공격 끝");
		return isArrive;
	}

	private static int[] selectAttacked(int time, int[] attacker) {
		int maxPower = DEAD;
		int minTime = time+1;
		int minSum = R+C;
		int[] pos = new int[] {-1,-1};

		for(int r=R-1;r>=0;r--) {
			for(int c=0;c<C;c++) {
				if(map[r][c] == DEAD) continue;
				if(r == attacker[0] && c == attacker[1]) continue;
				
				if(maxPower < map[r][c]) {
					maxPower = map[r][c];
					minTime = attackTime[r][c];
					minSum = r+c;
					pos[0] = r;
					pos[1] = c;
				}else if(maxPower == map[r][c]) {
					if(minTime > attackTime[r][c]) {
						maxPower = map[r][c];
						minTime = attackTime[r][c];
						minSum = r+c;
						pos[0] = r;
						pos[1] = c;
					}else if(minTime == attackTime[r][c]) {
						if(r+c <= minSum) {
							maxPower = map[r][c];
							minTime = attackTime[r][c];
							minSum = r+c;
							pos[0] = r;
							pos[1] = c;
						}
					}
				}
			}
		}
		
		if(pos[0] == -1 && pos[1] == -1) return pos; 
		attackVisited[pos[0]][pos[1]] = true;
		
		return pos;
	}

	private static void printStatus() {
		// TODO Auto-generated method stub

		System.out.println("map");
		print(map);
		System.out.println("attacktime");
		print(attackTime);
		System.out.println("visited");
		print(attackVisited);
		System.out.println("=======================");
	}

	private static void print(boolean[][] arr) {
		for(int r=0;r<arr.length;r++) System.out.println(Arrays.toString(arr[r]));
	}

	private static void print(int[][] arr) {
		for(int r=0;r<arr.length;r++) System.out.println(Arrays.toString(arr[r]));
	}

	private static int[] selectAttacker(int time, int power) {
		int minPower = 5001; // 최대 5000
		int maxTime = -1;
		int maxSum = -1;
		int[] pos = new int[] {-1,-1};
		
		// 행과 열의 합이 가장 큰 포탑
		// 같은 값일 때 열값이 가장 클려면 행값이 작아야 함
		for(int r=0;r<R;r++) {
			// 행과 열의 합이 같다면 열갑이 큰 순
			for(int c=C-1;c>=0;c--) {
				if(map[r][c] == DEAD) continue;
				
				if(map[r][c] < minPower) {
					minPower = map[r][c];
					maxTime =  attackTime[r][c];
					maxSum = r+c;
					pos[0]=r;
					pos[1]=c;
				}else if(map[r][c] == minPower) {
					if(maxTime < attackTime[r][c]) {
						minPower = map[r][c];
						maxTime =  attackTime[r][c];
						maxSum = r+c;
						pos[0]=r;
						pos[1]=c;
					}else if(maxTime == attackTime[r][c]) {
						if(maxSum <= r+c) {
							minPower = map[r][c];
							maxTime =  attackTime[r][c];
							maxSum = r+c;
							pos[0]=r;
							pos[1]=c;
						}
					}
				}
			}
		}
		
		attackTime[pos[0]][pos[1]] = time;
		attackVisited[pos[0]][pos[1]] = true;
		map[pos[0]][pos[1]] += power;
		
		return pos;
	}

	private static void reset(boolean[][] arr) {
		for(int r=0;r<arr.length;r++) Arrays.fill(arr[r], false);
	}
}