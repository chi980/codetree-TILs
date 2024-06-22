import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] commands = br.readLine().toCharArray();
		
		int r = 0;
		int c = 0;
		int d = 0;
		int time = 0;
		boolean flag = false;
		for (char command : commands) {
			if(command == 'L') d = (d+3)%4;
			else if(command == 'R') d = (d+1)%4;
			else if(command == 'F') {
				r += dr[d];
				c += dc[d];
			}
			time++;
			
			if(r == 0 && c == 0) {
				System.out.println(time);
				flag = true;
				break;
			}
		}
		
		if(!flag) System.out.println("-1");
	}
	
}