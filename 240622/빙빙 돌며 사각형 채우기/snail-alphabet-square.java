import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		final char EMPTY = '.';
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[r], EMPTY);
		}
		
		int cnt = 0;
		int r = 0;
		int c = 0;
		int d = 1;
		char value = 'A';

		while(cnt < R*C) {
			map[r][c] = value++;
			if(value == 'Z'+1) value = 'A';
			
			if(isInRange(r+dr[d], c+dc[d], R,C) && map[r+dr[d]][c+dc[d]]==EMPTY) {
				r+=dr[d];
				c+=dc[d];
			}else {
				d = (d+1)%4;
				r+=dr[d];
				c+=dc[d];
			}
			cnt++;
		}
		
		printArr(map);
	}

	private static boolean isInRange(int r, int c, int R, int C) {
		return !(r<0||c<0||r>=R||c>=C);
	}
	
	private static void printArr(char[][] array) {
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				System.out.print(array[r][c]+" ");
			}
			System.out.println("");
		}
	}
}