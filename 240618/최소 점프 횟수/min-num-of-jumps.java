import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int n;
	static int[] jumps;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		jumps = new int[n];
		for (int i = 0; i < n; i++) {
			jumps[i] = sc.nextInt();
		}
		
		int[] dp = new int[n];
		Arrays.fill(dp, n+1);
		dp[0] = 0;
		
		for (int from = 0; from < n; from++) {
			for (int to = from+1; to <= from+jumps[from] && to < n; to++) {
				dp[to] = Math.min(dp[to], dp[from]+1);
			}
		}
		
		System.out.println(dp[n-1]==n+1?-1:dp[n-1]);

	}
}