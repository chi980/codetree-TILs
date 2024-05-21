import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());


		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			
			tornado(map, r1, c1, r2, c2);
			normalization(map, r1, c1, r2, c2);
		}
		printArr(map, bw);
		bw.flush();
		bw.close();
	}
	
	private static void normalization(int[][] map, int r1, int c1, int r2, int c2) {
		Map<Integer, Integer> avgs = new HashMap<Integer, Integer>();
		
		for(int r=r1;r<=r2;r++) {
			for(int c=c1;c<=c2;c++) {
				int sum = map[r][c];
				int cnt = 1;
				for(int d=0;d<4;d++) {
					int newr = r+dr[d];
					int newc = c+dc[d];
					
					if(!check(newr, newc)) continue;
					sum += map[newr][newc];
					cnt++;
				}
				avgs.put(r*C+c, sum/cnt);
			}
		}
		for(int pos : avgs.keySet()) {
			int r = pos/C;
			int c = pos%C;
			
			map[r][c] = avgs.get(pos);
		}
		
	}

	private static boolean check(int r, int c) {
		if(r < 0 || c <0 || r>=R||c>=C) return false;
		return true;
	}

	private static void tornado(int[][] map, int r1, int c1, int r2, int c2) {
		int temp = moveRight(map, c1, c2, r1, map[r1+1][c1]);
		temp = moveDown(map, r1, r2, c2, temp);
		temp = moveLeft(map, c1, c2, r2, temp);
		moveUp(map, r1, r2, c1, temp);
	}

	private static void moveUp(int[][] map, int r1, int r2, int c, int last) {
		
		for(int r=r1+1;r<r2-1;r++) {
			map[r][c] = map[r+1][c];
		}
		
		map[r2-1][c] = last;
	}

	private static int moveLeft(int[][] map, int c1, int c2, int r, int first) {
		int temp = map[r][c1];
		
		for(int c=c1;c<c2-1;c++) {
			map[r][c] = map[r][c+1];
		}
		
		map[r][c2-1] = first;
		
		return temp;
	}


	private static int moveDown(int[][] map, int r1, int r2, int c, int first) {
		int temp = map[r2][c];
		
		for(int r=r2;r>r1+1;r--) {
			map[r][c] = map[r-1][c];
		}
		
		map[r1+1][c] = first;
		
		return temp;
	}

	private static int moveRight(int[][] map, int c1, int c2, int r, int first) {
		int temp = map[r][c2];
		
		for(int c=c2;c>c1;c--) {
			map[r][c] = map[r][c-1];
		}
		map[r][c1] = first;
		return temp;
	}

	public static void printArr(int[][] map, BufferedWriter bw) throws IOException{
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				bw.write(map[i][j]+" ");
			}
			bw.write("\n");
		}
	}
}