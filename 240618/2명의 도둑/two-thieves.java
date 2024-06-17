import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N, M, K, R, C;
	static int[][] map;
	static int[] picked;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		R = C = N;
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		picked = new int[2];
		answer = 0;
		pickStuff(0, 0);
		System.out.println(answer);
	}

	private static void pickStuff(int depth, int start) {
		if (depth == 2) {
			int sum = 0;
			for (int i = 0; i < 2; i++) {
				int curSum = getSum(i);
//				System.out.println("curSum: " + curSum);
				sum += curSum;
			}

//			System.out.println(Arrays.toString(picked));
//			System.out.println(sum);
//			System.out.println("-----------------");

			answer = Math.max(answer, sum);
			return;
		}
		for (int i = start; i < R * C; i++) {
			int r = i / C;
			int c = i % C;

			if (!isInRange(r, c + M - 1))
				continue;

			picked[depth] = i;
			if (isInRange(r, c + M + M - 1))
				pickStuff(depth + 1, r * C + (c + M));
			else
				pickStuff(depth + 1, (r + 1) * C + 0);
		}
	}

	private static int getSum(int idx) {
		int pos = picked[idx];
		int curr = pos / C;
		int curc = pos % C;

		int cursum = 0;
		int result = 0;
		for (int c = curc; c < curc + M; c++) {
			cursum += map[curr][c];
			result += (map[curr][c] * map[curr][c]);
		}

		if (cursum < K)
			return result;

		int maxsum = selectStuff(pos, 0, 0, 0);
		return maxsum;
	}

	private static int selectStuff(int curPos, int totalSum, int cnt, int result) {
		if (cnt == M ) {
			return result;
		}

		int curr = curPos / C;
		int curc = curPos % C;

		int maxSum = result;
		if (totalSum + map[curr][curc] <= K) {
			int selectedSum = selectStuff(curPos + 1, totalSum + map[curr][curc], cnt + 1,
					result + map[curr][curc] * map[curr][curc]);
			maxSum = Math.max(maxSum, selectedSum);
		}
		int notSelectedSum = selectStuff(curPos + 1, totalSum, cnt + 1, result);
		maxSum = Math.max(maxSum, notSelectedSum);

		return maxSum;
	}

	private static boolean isInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

}