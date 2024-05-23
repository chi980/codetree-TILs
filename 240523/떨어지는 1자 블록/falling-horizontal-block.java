import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static final int BLOCK = 1;
	
	static int R, C;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = R;
		map = new int[R][C];

		
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		k--;
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		down(k, m);
		
		Arrays.stream(map).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
		
	}
	private static void down(int pos, int len) {
		int r = 0;
		for(;r<R;r++) {
			if(isStock(r, pos, len)) break;
		}
		r--;
		
		mark(r, pos, len);
	}
	private static boolean isStock(int r, int pos, int len) {
		for(int c=pos;c<pos+len;c++) {
			if(map[r][c] == BLOCK) return true;
		}
		return false;
	}
	private static void mark(int r, int pos, int len) {
		for(int c=pos;c<pos+len;c++) {
			map[r][c] = BLOCK;
		}
	}
}