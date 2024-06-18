import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * .(빈공간) S(시작점) E(도착점) 1~9(동전)
 * 
 * NxN격자 동전은 각 위치에 최대 하나씩, 한 번 획득한 동전은 다시 획득x, 굳이 동전 수집 안 해도 됨, 최소 3개 동전획득, 같은
 * 숫자를 지닌 동전은 주어지지 아니함
 * 
 * @author 82108
 *
 */
public class Main {
	static final char START = 'S';
	static final char END = 'E';
	static final char EMPTY = '.';

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static char[][] map;
	static List<int[]> coins;
	
	static int[] visited;
	static int[][] dist;
	static int minMove;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		R = C = N;

		map = new char[R][C];
		int startr, startc, endr, endc;
		startr = startc = endr = endc = -1;
		coins = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (isCoin(map[r][c])) {
					coins.add(new int[] { map[r][c] - '0', r, c });
				} else if (map[r][c] == START) {
					startr = r;
					startc = c;
				} else if (map[r][c] == END) {
					endr = r;
					endc = c;
				}
			}
		}

		Collections.sort(coins, (o1, o2) -> o1[0] - o2[0]);

		// 0번은 시작점, 마지막은 end
		dist = new int[11][11];
		for (int from = 0; from < coins.size(); from++) {
			for (int to = from + 1; to < coins.size(); to++) {
				int[] fromCoin = coins.get(from);
				int[] toCoin = coins.get(to);

				int fromCoinType = fromCoin[0];
				int toCoinType = toCoin[0];
				
				dist[fromCoinType][toCoinType] = BFS(fromCoin[1], fromCoin[2], toCoin[1], toCoin[2]);
				dist[toCoinType][fromCoinType] = dist[fromCoinType][toCoinType];
			}
		}
		
		for(int[] coin : coins) {
			int coinType = coin[0];
			
			dist[0][coinType] = BFS(startr, startc, coin[1], coin[2]);
			dist[coinType][0] = dist[0][coinType];
			
			dist[dist.length-1][coinType] = BFS(endr, endc, coin[1], coin[2]);
			dist[coinType][dist.length-1] = dist[dist.length-1][coinType];
		}
		
		visited = new int[coins.size()];
		
		minMove = Integer.MAX_VALUE;
		pickCoin(0, 0, 0, 0);
		
		if (minMove == Integer.MAX_VALUE) {
			minMove = -1;
		}
		
		System.out.println(minMove);
	}

	private static void pickCoin(int from, int startCoinIdx, int move, int coinCnt) {
		if(minMove != Integer.MAX_VALUE && minMove < move) return;
		if(coinCnt >= 3) {
//			System.out.println("move: "+move);
//			for(int i=0;i<coinCnt;i++) {
//				System.out.print(visited[i]+" ");
//			}
//			
//			System.out.println("");
			
			minMove = Math.min(minMove, move + dist[from][dist.length-1]);
		}
		
		if(coinCnt == coins.size()) {
			return;
		}
		
		for(int coinIdx = startCoinIdx;coinIdx<coins.size();coinIdx++) {
			int[] coin = coins.get(coinIdx);
			visited[coinCnt] = coin[0];
			pickCoin(coin[0], coinIdx+1, move + dist[from][coin[0]], coinCnt+1);
		}
	}

	private static int BFS(int startr, int startc, int endr, int endc) {
		int[][] visited = new int[R][C];
		Queue<int[]> q = new ArrayDeque<>();

		visited[startr][startc] = 1;
		q.offer(new int[] { startr, startc });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];

			if (curr == endr && curc == endc)
				return visited[curr][curc] - 1;

			for (int d = 0; d < 4; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!canGo(newr, newc, visited))
					continue;
				visited[newr][newc] = visited[curr][curc] + 1;
				q.offer(new int[] { newr, newc });
			}
		}

		return 0;
	}

	private static boolean canGo(int r, int c, int[][] visited) {
		if (isInRange(r, c) && map[r][c]!='E' &&visited[r][c] == 0)
			return true;
		return false;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static boolean isCoin(char coin) {
		if (coin - '1' >= 0 && coin - '1' < 9)
			return true;
		return false;
	}
	
	public static void printArr(int[][] array) {
		Arrays.stream(array).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
	private static void printArr(char[][] array) {
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				System.out.print(array[r][c]);
			}
			System.out.println("");
		}
	}
}