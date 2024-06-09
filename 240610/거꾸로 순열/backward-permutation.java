import java.util.Scanner;
public class Main {
	static int n;
	static boolean[] visited;
	static int[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		visited = new boolean[n+1];
		result = new int[n];
		
		Permutation(0);
	}

	private static void Permutation(int depth) {
		if(depth == n) {
			for(int number : result) {
				System.out.print(number+" ");
			}
			System.out.println("");
			return;
		}
		
		for(int i=n;i>=1;i--) {
			if(visited[i]) continue;
			visited[i] = true;
			result[depth] = i;
			Permutation(depth+1);
			visited[i] = false;
		}
	}
}