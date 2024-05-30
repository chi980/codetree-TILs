import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n x n크기 격자판 주사위 - 특정 위치에 올린 뒤, m번에 걸쳐 상하좌우 한 방향으로 딱 한 칸 이동하도록 구림 각 주사위가 격자판에
 * 닿을 때마다 격자판에는 해당 위치에 주사위의 아랫면에 적혀있던 숫자가 적히게 됨 해당 위치에 숫자가 적혀있었더라도 새로 숫자가 적히게 됨
 * 처음 주사위가 놓여져 있는 방향은 1이 위, 2가 앞, 3이 오른쪽
 * 
 * @author 82108
 *
 */
public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int startr = Integer.parseInt(st.nextToken()) - 1;
		int startc = Integer.parseInt(st.nextToken()) - 1;

		R = C = n;
		map = new int[R][C];
		Dice dice = new Dice(startr, startc);
		map[dice.r][dice.c] = dice.getBottom();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			char dInfo = st.nextToken().charAt(0);

			int newr = -1;
			int newc = -1;
			switch (dInfo) {
			case 'L':
				newr = dice.r + dr[3];
				newc = dice.c + dc[3];
				if (!checkInRange(newr, newc))
					continue;
				dice.rotateLeft();
				dice.move(newr, newc);
				break;
			case 'R':
				newr = dice.r + dr[1];
				newc = dice.c + dc[1];
				if (!checkInRange(newr, newc))
					continue;
				dice.rotateRight();
				dice.move(newr, newc);
				break;
			case 'U':
				newr = dice.r + dr[0];
				newc = dice.c + dc[0];
				if (!checkInRange(newr, newc))
					continue;
				dice.rotateBack();
				dice.move(newr, newc);
				break;
			case 'D':
				newr = dice.r + dr[2];
				newc = dice.c + dc[2];
				if (!checkInRange(newr, newc))
					continue;
				dice.rotateFront();
				dice.move(newr, newc);
				break;
			}


			map[newr][newc] = dice.getBottom();
//			System.out.println(dice);
//			printArr(map);
//			System.out.println("======================");
		}
		int sum = Arrays.stream(map).flatMapToInt(Arrays::stream).filter(value -> value >= 0).sum();
		System.out.println(sum);
	}

	private static boolean checkInRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C)
			return false;
		return true;
	}

	public static void printArr(int[][] arr) {
		Arrays.stream(arr).forEach(row -> {
			Arrays.stream(row).mapToObj(value -> value + " ").forEach(System.out::print);
			System.out.println();
		});
	}

	static class Dice {
		int r, c;// 현재 위치
		int[] x, y, z; // x: 왼쪽, 오른쪽, y: 앞쪽, 뒷쪽, z: 아랫쪽, 윗쪽

		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
			this.x = new int[] { 2, 5 };
			this.y = new int[] { 4, 3 };
			this.z = new int[] { 6, 1 };
		}

		public void move(int newr, int newc) {
			r = newr;
			c = newc;
		}

		public void rotateRight() {
			int[] tmp = y;
			this.y = z;
			this.z = tmp;
			reserve(this.z);
		}

		public void rotateLeft() {
			int[] tmp = z;
			this.z = y;
			this.y = tmp;
			reserve(this.y);
		}

		public void rotateFront() {
			int[] tmp = z;
			this.z = x;
			this.x = tmp;
			reserve(this.x);
		}

		public void rotateBack() {
			int[] tmp = x;
			this.x = z;
			this.z = tmp;
			reserve(this.z);
		}

		public int getBottom() {
			return this.z[0];
		}

		private void reserve(int[] arr) {
			int start = 0;
			int end = arr.length - 1;
			while (start <= end) {
				int tmp = arr[start];
				arr[start] = arr[end];
				arr[end] = tmp;
				start++;
				end--;
			}
		}

		@Override
		public String toString() {
			return "Dice [r=" + r + ", c=" + c + ", x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + ", z="
					+ Arrays.toString(z) + "]";
		}

	}

}