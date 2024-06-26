import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] items = new int[N];
		for (int i = 0; i < N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		int maxLen = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i-1; j >=0; j--) {
				if(items[j] >= items[i]) continue;  
				dp[i] = Math.max(dp[i], dp[j]+1);
				maxLen = Math.max(maxLen, dp[i]);
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(maxLen);
	}
}