import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());;
		int[] dirNum = new int['Z'-'A'];
		dirNum['N'-'A'] = 2;
		dirNum['E'-'A'] = 1;
		dirNum['S'-'A'] = 0;
		dirNum['W'-'A'] = 3;
		
		int r = 0;
		int c = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = dirNum[st.nextToken().charAt(0)-'A'];
			int k = Integer.parseInt(st.nextToken());
			
			r = r + k*dr[d];
			c = c + k*dc[d];
		}
		System.out.println(c + " " +r);
		
		
	}
}