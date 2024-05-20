import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 1. tabulation
		int[] tab = new int[n+1];
		tab[1] = 1;
		for(int i=2;i<=n;i++) {
			tab[i] = tab[i-1]+tab[i-2];
		}
		
		// 2. memoization
		int[] memo = new int[n+1];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		memo[1] = 1;
		int fibo = getFibo(n, memo);
		System.out.println(fibo);
	}

	private static int getFibo(int n, int[] memo) {
		if(memo[n] != -1) return memo[n];
		memo[n] = getFibo(n-1,memo) + getFibo(n-2, memo);
		return memo[n];
	}
}