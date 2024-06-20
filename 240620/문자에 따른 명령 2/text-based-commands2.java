import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] dNums = br.readLine().toCharArray();
		
		int r = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < dNums.length; i++) {
			if(dNums[i] == 'L') {
				d += 3;
				d %= 4;
			}else if(dNums[i] == 'R') {
				d += 1;
				d %= 4;
			}else if(dNums[i] == 'F') {
				r = r+dr[d];
				c = c + dc[d];
			}
		}
		System.out.println(c+" "+r);
		
	}
}