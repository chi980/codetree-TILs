import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int time = BFS(n);
		System.out.println(time);
	}

	private static int BFS(int n) {
		Set<Integer> visited = new HashSet<>();
		Queue<int[]> q = new ArrayDeque<>();
		
		visited.add(n);
		q.offer(new int[] {n, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curn = cur[0];
			int curcnt = cur[1];
			
			if(curn == 1) return curcnt;
			
			if(!visited.contains(curn-1)) {
				visited.add(curn-1);
				q.offer(new int[] {curn-1, curcnt+1});
			}
			
			if(!visited.contains(curn+1)) {
				visited.add(curn+1);
				q.offer(new int[] {curn+1, curcnt+1});
			}
			
			if(curn%2==0 && !visited.contains(curn/2)) {
				visited.add(curn/2);
				q.offer(new int[] {curn/2, curcnt+1});
			}
			
			if(curn%3==0 && !visited.contains(curn/3)) {
				visited.add(curn/3);
				q.offer(new int[] {curn/3, curcnt+1});
			}
		}
		
		return 0;
	}

}