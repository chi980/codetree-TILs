import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 반으로 접힌 부분을 편 모습
		int[]belt = new int[2*n];
		for(int i=0;i<2;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				belt[i*n+j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		System.out.println(Arrays.toString(belt));
		
		int[] newBelt = new int[2*n];
		for(int idx=0;idx<2*n;idx++) {
			int newIdx = (idx+k*1) % (2*n);
			newBelt[newIdx] = belt[idx];
		}
		
//		System.out.println(Arrays.toString(newBelt));
		
		printBelt(newBelt, n);
	}

	private static void printBelt(int[] belt, int len) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++) {
			sb.append(belt[i]).append(" ");
		}
		sb.append("\n");
		for(int i=len;i<belt.length;i++) {
			sb.append(belt[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}