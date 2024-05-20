import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N,M;
	static int[] A, B;
	
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
		Arrays.sort(B);
		
		resultA = new int[M];
		isBeautiful = new boolean[N];
		
		for(int start=0;start+M-1<N;start++) {
			if(isBeautiful[start]) continue;
			for(int idx=0;idx<M;idx++) {
				resultA[idx] = A[start+idx];
			}
			
			Arrays.sort(resultA);
			
			if(same(resultA, B)) {
				isBeautiful[start] = true;
			}
		}
		
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

	private static boolean same(int[] arr1, int[] arr2) {
		int def = arr1[0] - arr2[0];
		for(int i=1;i<arr1.length;i++) {
			int curDef = arr1[i] - arr2[i];
			
			if(def != curDef) return false;
		}
		return true;
	}
}