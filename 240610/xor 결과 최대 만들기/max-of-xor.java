import java.util.Scanner;
public class Main {
	static int n,m;
	static int[] arr;
	static int[] result;
	static int maxValue;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n];
		result = new int[m];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		maxValue = 0;
		Combination(0, 0);
		System.out.println(maxValue);
	}
	private static void Combination(int depth, int start) {
		if(depth == m) {
			
			int value = cal();
			maxValue = Math.max(maxValue, value);
			
			return;
		}
		
		for(int i=start;i<n;i++) {
			result[depth] = arr[i];
			Combination(depth+1, i+1);
		}
	}
	private static int cal() {
		int ans = 0;
		
		for (int number : result) {
			ans ^= number;
		}
		
		return ans;
	}
}