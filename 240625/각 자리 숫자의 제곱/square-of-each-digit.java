import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long answer = getPowNumber(N);
		System.out.println(answer);
	}

	private static long getPowNumber(int n) {
		if(n == 0) return 0;
		return getPowNumber(n/10) + (n%10)*(n%10);
	}

}