import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int sum = getSum(N);
		System.out.println(sum);
	}

	private static int getSum(int n) {
		if(n==1) {
			return 1;
		}
		return n + getSum(n-1);
	}
}