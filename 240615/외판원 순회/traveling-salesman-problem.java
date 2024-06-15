import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 외판원순회문제
 * 1번 지점에서 출발 -> 모든지점을 정확히 한 번 방문하고 다시 1번 지점으로 돌아와야 함
 * 모든 정점을 겹치지 않게 방문하고 되돌아오는데 필요한 최소 비용의 합을 구해보자
 * 
 * BackTracking할만한 것
 * -> 연결x부분은 고려x, 이미 방문한 곳은 고려x
 * @author 82108
 *
 */
public class Main {
	static final int NOT_CONNECTED = 0;
	
	static int N;
	static int[][] cost;
	
	static boolean[] visited;
	static int[] order;
	
	static int minCost;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N];
		order = new int[N];
		visited[0] = true;
		
		minCost = Integer.MAX_VALUE;
		visit(0, 0, 0);
		System.out.println(minCost);
	}

	private static void visit(int depth, int sumOfCost, int last) {
		if(depth == N-1) {
			if(cost[last][0] !=NOT_CONNECTED) {
				minCost = Math.min(minCost, sumOfCost+cost[last][0]);
			}
			return;
		}
		
		for (int i = 1; i < N; i++) {
			if(cost[last][i] == NOT_CONNECTED) continue;
			if(visited[i]) continue;
			
			order[depth] = i;
			visited[i] = true;
			visit(depth+1, sumOfCost+cost[last][i], i);
			visited[i] = false;
		}
	}
}