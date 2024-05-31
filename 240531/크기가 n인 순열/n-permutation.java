import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int N;
	static int[] num;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		visited = new boolean[N+1];
		
		Permutation(0);
	}

	private static void Permutation(int depth) {
		if(depth == N) {
			Arrays.stream(num).mapToObj(value -> value+" ").forEach(System.out::print);
			System.out.println("");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			num[depth] = i;
			Permutation(depth+1);
			visited[i] = false;
		}
	}
}