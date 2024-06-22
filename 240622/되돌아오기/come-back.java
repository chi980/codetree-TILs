import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException{
		// (0,0) -> N번 움직이기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dInfo = new int['Z'-'A'];
		dInfo['N'-'A'] = 0;
		dInfo['E'-'A'] = 1;
		dInfo['S'-'A'] = 2;
		dInfo['W'-'A'] =3;
		
		int r = 0;
		int c = 0;
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int d = dInfo[st.nextToken().charAt(0)-'A'];
			int k = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < k; j++) {
				r += dr[d];
				c += dc[d];
				cnt++;
				
				if(r == 0 && c == 0) min = Math.min(min, cnt);
			}
		}
		
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
}