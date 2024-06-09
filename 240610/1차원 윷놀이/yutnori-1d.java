import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int n; // 턴 수
	static int[] markers;
	static int[] degsPerTurn;

	static int m; // 판의 오른쪽 숫자
	static int k; // 말 번호
	static int[] degsPerMarker;
	
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		markers = new int[n];
		degsPerTurn = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			degsPerTurn[i] = Integer.parseInt(st.nextToken());
		}

		degsPerMarker = new int[k + 1];
		Arrays.fill(degsPerMarker, 1);
		maxScore = 0;
		Permutation(0);
		System.out.println(maxScore);
	}

	private static void Permutation(int depth) {
		int score = getMarkersScore();
		maxScore = Math.max(maxScore, score);
		if (depth == n) {
			return;
		}

		for (int marker = 1; marker <= k; marker++) {
			if (!canGo(marker))
				continue;
			degsPerMarker[marker] += degsPerTurn[depth];
			markers[depth] = marker;
			Permutation(depth + 1);
			degsPerMarker[marker] -= degsPerTurn[depth];
		}
	}

	private static int getMarkersScore() {
		int score = 0;
		for (int idx = 1; idx < degsPerMarker.length; idx++) {
			if(degsPerMarker[idx] >= m) score++;
		}
		return score;
	}

	private static boolean canGo(int marker) {
		if (degsPerMarker[marker] >= m)
			return false;
		return true;
	}
}