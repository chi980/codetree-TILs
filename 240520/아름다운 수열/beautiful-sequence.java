import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int K = 0;
		for(int i=0;i<isBeautiful.length;i++) {
			if(isBeautiful[i]) K++;
		}
		bw.write(K+"\n");
		for(int i=0;i<isBeautiful.length;i++) {
			if(isBeautiful[i]) bw.write((i+1)+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	private static void Permutation(int depth) {
		if(depth == M) {
			for(int start=0;start+M-1<N;start++) {
				if(isBeautiful[start]) continue;
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
		int def = arr1[0] - arr2[0];
		for(int i=1;i<arr1.length;i++) {
			int curDef = arr1[i] - arr2[i];
			
			if(def != curDef) return false;
		}
		return true;
	}
}