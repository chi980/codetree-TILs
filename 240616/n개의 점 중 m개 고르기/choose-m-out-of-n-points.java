import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	static int N,M;
	static int[][] points;
	static boolean[] visited;
	static double min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		points = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points[i][0] = x;
			points[i][1] = y;
		}
		
		visited = new boolean[N];
		min = Double.MAX_VALUE;
		pickPoints(0, 0);
		
		int result =(int) (min*min);
		System.out.println(result);
	}

	private static void pickPoints(int depth, int start) {
		if(depth == M) {
			double max = getMaxDiff();
			min = Math.min(min, max);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			pickPoints(depth+1, i+1);
			visited[i] = false;
		}
	}

	private static double getMaxDiff() {
		double max = 0;
		for (int i = 0; i < N; i++) {
			if(!visited[i]) continue;
			int[] point1 = points[i];
			for(int j=i+1;j<N;j++) {
				if(!visited[j]) continue;
				int[] point2 = points[j];
				
				double diff = getDiff(point1, point2);
				max = Math.max(max, diff);
			}
			
		}
		return max;
	}

	private static double getDiff(int[] point1, int[] point2) {
		int rDiff = point1[0]-point2[0];
		rDiff *= rDiff;
		
		int cDiff = point1[1]-point2[1];
		cDiff *= cDiff;
		
		double sum = rDiff + cDiff;
		
		sum = Math.sqrt(sum);
		
		return sum;
	}

}