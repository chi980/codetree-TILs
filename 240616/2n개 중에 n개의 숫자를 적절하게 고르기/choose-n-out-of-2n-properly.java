import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static int N;
	static int minDiff;
	static int[] numbers;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[2*N];
		
		for (int i = 0; i < 2*N; i++) {
			numbers[i] = sc.nextInt();
		}
		
		minDiff = 10001;
		visited = new boolean[2*N];
		pickNumber(0, 0);
		System.out.println(minDiff);
	}

	private static void pickNumber(int depth, int start) {
		if(minDiff == 0) return;
		
		if(depth == N) {
			int diff = getDiff();
			minDiff = Math.min(minDiff, diff);
			return;
		}
		
		for (int i = start; i < 2*N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			pickNumber(depth+1, i+1);
			visited[i] = false;
		}
		
	}

	private static int getDiff() {
		int sumPicked = 0;
		int sumNotPicked = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			if(visited[i]) sumPicked+=numbers[i];
			else sumNotPicked += numbers[i];
		}
		
		return Math.abs(sumPicked-sumNotPicked);
	}
}