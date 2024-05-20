import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	static int N,M;
	static int[] A, B;
	
	static boolean[] visited;
	static int[] resultB;
	static int[] resultA;
	static boolean[] isBeautiful;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine().trim());
		}

		M = Integer.parseInt(br.readLine());
		B = new int[M];
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(br.readLine().trim());
		}
		
		resultA = new int[M];
		resultB = new int[M];
		visited = new boolean[M];
		isBeautiful = new boolean[N];
		Permutation(0);
		
		int K = 0;
		for(int i=0;i<isBeautiful.length;i++) {
			if(isBeautiful[i]) K++;
		}
		System.out.println(K);
		for(int i=0;i<isBeautiful.length;i++) {
			if(isBeautiful[i]) System.out.println(i+1);
		}
	}
	private static void Permutation(int depth) {
		if(depth == M) {
			for(int start=0;start+M-1<N;start++) {
				for(int idx=0;idx<M;idx++) {
					resultA[idx] = A[start+idx];
				}
				if(same(resultA, resultB)) {
					isBeautiful[start] = true;
				}
			}
		}
		
		for(int i=0;i<M;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			resultB[depth] = B[i];
			Permutation(depth+1);
			visited[i] = false;
		}
		
	}
	private static boolean same(int[] arr1, int[] arr2) {
		int[] defs = new int[arr1.length];
		for(int i=0;i<arr1.length;i++) {
			defs[i] = arr1[i] - arr2[i];
		}

		for(int i=1;i<defs.length;i++) {
			if(defs[i] != defs[i-1]) return false;
		}
		return true;
	}
}