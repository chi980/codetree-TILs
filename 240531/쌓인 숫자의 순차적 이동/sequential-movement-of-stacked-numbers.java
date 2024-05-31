import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * n x n 격자판 -> 1~n*n수자들이 한번씩등장 m번에 걸쳐 숫자 이동 여덟방향으로 인접한 위치 중 가장 큰 값이 적혀있는 숫자가 있는
 * 곳으로 이동
 * 
 * 1~n*n숫자들은 한 번씩 등장하므로 이와 위치를 map으로 관리한다.
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int R, C;
	static Stack<Integer>[][] map;
	static Map<Integer, int[]> numberPosInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		R = C = n;
		map = new Stack[R][C];
		numberPosInfo = new HashMap<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = new Stack<Integer>();
				int number = Integer.parseInt(st.nextToken());
				map[r][c].push(number);
				numberPosInfo.put(number, new int[] { r, c });
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int curnumber = Integer.parseInt(st.nextToken());

			int[] curpos = numberPosInfo.get(curnumber);
			int curr = curpos[0];
			int curc = curpos[1];

			int maxnumber = 0;
			int maxr = -1;
			int maxc = -1;
			for (int d = 0; d < 8; d++) {
				int newr = curr + dr[d];
				int newc = curc + dc[d];

				if (!isInRange(newr, newc))
					continue;

				for (Map.Entry<Integer, int[]> entry : numberPosInfo.entrySet()) {
					int newnumber = entry.getKey();
					int[] newpos = entry.getValue();

					if (!isSamePos(newr, newc, newpos[0], newpos[1]))
						continue;
					if (newnumber < maxnumber)
						continue;
					
					maxnumber = newnumber;
					maxr = newr;
					maxc = newc;

				}
				
				
			}
			
			if(maxr == -1 && maxc == -1) continue;
			
			Stack<Integer> tmp =  new Stack<>();
			while(!map[curr][curc].isEmpty()) {
				int tmpnumber = map[curr][curc].pop();
				
				numberPosInfo.put(tmpnumber, new int[] {maxr, maxc});
				
				tmp.push(tmpnumber);
				
				if(tmpnumber == curnumber) break;
			}
			
			while(!tmp.isEmpty()) {
				map[maxr][maxc].push(tmp.pop());
			}
			
//			printStatus();
//			System.out.println("=========================");
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c].isEmpty()) System.out.println("None");
				else {
					while(!map[r][c].isEmpty()) {
						System.out.print(map[r][c].pop()+" ");
					}
					System.out.println("");
				}
			}
		}
	}

	private static void printStatus() {
		System.out.print("\n{");
		for(Map.Entry<Integer, int[]> entry : numberPosInfo.entrySet()) {
			System.out.print(entry.getKey() +"="+Arrays.toString(entry.getValue())+", ");
		}
		System.out.println("}");
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]+"\t");
			}
			System.out.println("");
		}
	}

	private static boolean isSamePos(int r1, int c1, int r2, int c2) {
		if (r1 == r2 && c1 == c2)
			return true;
		return false;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}
}