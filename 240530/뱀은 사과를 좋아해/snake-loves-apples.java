import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * nxn 격자에 사과와 뱀이 있다. 뱀 처음 상태: 위치 -> (0,0), 길이 -> 1 이동(1초 소요) 빈칸: 머리를 특정방향으로 한 칸
 * 옮긴 뒤, 가장 끝에 있는 꼬리가 사라짐 사과: 꼬리 안 사라짐, 길이++, 사과 사라짐
 * 
 * 뱀이 전부 움직였거나 움직이는 도중 격자를 벗어나거나 움직이는 도중 몸이 꼬여 겹쳐질 때 종료
 * 
 * @author 82108
 *
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static boolean[][] apples;
	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		R = C = n;
		apples = new boolean[R][C];
		
		

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int appler = Integer.parseInt(st.nextToken()) - 1;
			int applec = Integer.parseInt(st.nextToken()) - 1;

			apples[appler][applec] = true;
		}

		Map<Character, Integer> dStatus = new HashMap<>();
		dStatus.put('U', 0);
		dStatus.put('R', 1);
		dStatus.put('D', 2);
		dStatus.put('L', 3);

		Snake snake = new Snake(R, C, 0, 0);
		time = 0;

		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int d = dStatus.get(st.nextToken().charAt(0));
			int p = Integer.parseInt(st.nextToken());

			if (!moveSnake(snake, d, p))
				break;
			
		}
		System.out.println(time);
	}

	private static boolean moveSnake(Snake snake, int d, int p) {
		for (int i = 0; i < p; i++) {
			int newr = snake.headr + dr[d];
			int newc = snake.headc + dc[d];
			
			time++;
			if (!isInBoard(newr, newc) || !snake.canMove(newr, newc))
				return false;
			if(!apples[newr][newc]) {
				snake.move(newr, newc);
			}else {
				snake.eatApple(newr, newc);
				apples[newr][newc] = false;
			}
		}
		return true;
	}

	private static boolean isInBoard(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	static class Snake {
		int headr, headc;
		Queue<int[]> body;
		boolean[][] visited;

		public Snake(int R, int C, int headr, int headc) {
			this.headr = headr;
			this.headc = headc;

			this.body = new ArrayDeque<int[]>();
			this.visited = new boolean[R][C];

			this.body.offer(new int[] { headr, headc });
			this.visited[headr][headc] = true;
		}

		public int[] move(int headr, int headc) {
			this.headr = headr;
			this.headc = headc;

			this.body.offer(new int[] { headr, headc });
			visited[headr][headc] = true;

			int[] tail = this.body.poll();
			visited[tail[0]][tail[1]] = false;

			return tail;
		}

		public void eatApple(int headr, int headc) {
			this.headr = headr;
			this.headc = headc;

			this.body.offer(new int[] { headr, headc });
			visited[headr][headc] = true;
		}

		public boolean canMove(int headr, int headc) {
			int tailr = this.body.peek()[0];
			int tailc = this.body.peek()[1];
			if (!this.visited[headr][headc] || (headr == tailr && headc == tailc))
				return true;
			return false;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (boolean[] row : visited) {
				for (boolean value : row) {
					sb.append(value ? "1" : "0").append(" ");
				}
				sb.append("\n");
			}
			return "Snake [body=" + body.toString() + "\n, visited=\n" + sb.toString() + "\n]";
		}
	}
}