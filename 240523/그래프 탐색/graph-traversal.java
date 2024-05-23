import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			x--;
			y--;
			
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
		
		visited = new boolean[N];
		visited[0] = true;
		DFS(graph, 0, N);
		
		int cnt = 0;
		for (int i = 0; i < graph.length; i++) {
			if(visited[i]) cnt++;
		}
		
		cnt -= 1;
		
		System.out.println(cnt);
	}
	private static void DFS(int[][] graph, int vertex, int N) {
		for(int currV = 0;currV < N; currV++) {
			if(graph[vertex][currV]==0) continue;
			if(visited[currV]) continue;
			visited[currV] = true;
			DFS(graph, currV, N);
		}
	}
}