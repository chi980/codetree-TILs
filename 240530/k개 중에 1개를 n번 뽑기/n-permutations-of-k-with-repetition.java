import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		arr = new int[n];

		Permutation(0, n, k);
	}

	private static void Permutation(int depth, int n, int k) {
		if (depth == n) {
			printArr(arr);
			return;
		}

		for (int i = 1; i <= k; i++) {
			arr[depth] = i;
			Permutation(depth+1, n, k);
		}
	}

	public static void printArr(int[] arr) {
		Arrays.stream(arr).mapToObj(value -> value + " ").forEach(System.out::print);
		System.out.println();
	}
}