import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N,M;
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		num = new int[M];
		
		Combination(0, 1);
	}
	private static void Combination(int depth, int start) {
		if(depth == M) {
			Arrays.stream(num).mapToObj(value -> value+" ").forEach(System.out::print);
			System.out.println("");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			num[depth] = i;
			Combination(depth+1, i+1);
		}
	}
}