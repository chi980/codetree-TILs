import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int K, N;
	
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		N = sc.nextInt();
		num = new int[N];
		
		BackTracking(0);
	}

	private static void BackTracking(int depth) {
		if(depth == N) {
			Arrays.stream(num).mapToObj(value -> value+" ").forEach(System.out::print);
			System.out.println("");
			return;
		}
		
		for (int i = 1; i <= K; i++) {
			if(depth>=2&& num[depth-1] == i && num[depth-2] == i) continue;
			num[depth] = i;
			BackTracking(depth+1);
		}
	}

}