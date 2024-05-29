import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n x n 격자판 특정 위치 선택 시, 그 위치 중심으로 십자 모양 폭탄 십자 모양의 크기는 선택된 위치에 적혀있는 숫자로 정해짐 ->
 * 터진 이후에는 중력 작용 십자 모양의 크기는 선택된 숫자에 비례하여 커짐 최적의 십자 모양 폭발이란, 특정 위치를 선택하여 폭탄이 터진 뒤
 * 중력이 작용한 후 상하좌우로 인접한 격자끼리 적혀있는 숫자가 동일한 쌍의 수가 최대가 되도록하는 폭발 이 때 정확히 2개의 칸으로 쌍이
 * 이루어진 경우에 대해서만 생각해야 함
 * 
 * @author 82108
 *
 */
public class Main {
	static final int EMPTY = 0;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		R = C = n;
		int[][] map = new int[R][C];

		for (int r = 0; r < R; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int maxPairCnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int[][] newMap = bomb(r, c, map);
				newMap = gravity(newMap);

				int pairCnt = getPairCnt(newMap);
				maxPairCnt = Math.max(maxPairCnt, pairCnt);
				
//				System.out.println("r: "+r+", c: "+c+", pairCnt: "+pairCnt);
//				printArr(newMap);
//				System.out.println("==========================");
			}
		}
		
		System.out.println(maxPairCnt);
	}

	private static int[][] bomb(int curr, int curc, int[][] map) {
		int[][] newMap = copy(map);

		int K = newMap[curr][curc];
		newMap[curr][curc] = EMPTY;

		for (int k = 1; k < K; k++) {
			for (int d = 0; d < 4; d++) {
				int newr = curr + k * dr[d];
				int newc = curc + k * dc[d];

				if (!isInMap(newr, newc))
					continue;

				newMap[newr][newc] = EMPTY;
			}
		}

		return newMap;
	}

	private static boolean isInMap(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	private static int[][] copy(int[][] map) {
		int[][] newMap = new int[R][C];

		for (int r = 0; r < R; r++) {
			newMap[r] = Arrays.copyOf(map[r], map[r].length);
		}

		return newMap;
	}

	private static int[][] gravity(int[][] map) {
		int[][] newMap = new int[R][C];
		
		for (int c = 0; c < C; c++) {
			int newr = R-1;
			for (int r = R - 1; r >= 0; r--) {
				if(map[r][c] == EMPTY) continue;
				newMap[newr--][c] = map[r][c];
			}
		}
		
		return newMap;
	}

	private static int getPairCnt(int[][] map) {
		
		int cnt = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == EMPTY) continue;
				
//				현 자리에서 오른쪽과 아래쪽만 확인한다.(중복 피함)
				for(int d=1;d<3;d++) {
					int newr = r + dr[d];
					int newc = c + dc[d];
					
					if(!isInMap(newr, newc)) continue;
					if(map[r][c] != map[newr][newc]) continue;
					
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}
}