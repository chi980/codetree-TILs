import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
	static final int RIGHT = 1;
	static final int LEFT = 0;

	static int R, C;
	static int[][] building;
	
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		building = new int[R][C];
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				building[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			char dInfo = st.nextToken().charAt(0); // L or R
			int d = dInfo == 'R'?RIGHT:dInfo=='L'?LEFT:-1;
			
			moveBuildingFloor(r, d);
			
			propagation(r, 1, d);
			propagation(r, -1, d);
			
		}
		

		printBuilding();
		
		bw.flush();
		bw.close();
	}

	private static void printBuilding() throws IOException{
		
		for(int r=0;r<building.length;r++) {
			for(int c=0;c<building[r].length;c++) {
				bw.write(building[r][c]+" ");
			}
			bw.write("\n");
		}
	}

	private static void propagation(int r, int value, int d){
		int newR = r + value;

		if(newR < 0 || newR >= R) return;
		if(!containsSameItem(building[r], building[newR])) return;
		
		int newD = (d+1)%2;
		moveBuildingFloor(newR, newD);
		propagation(newR, value, newD);
	}

	private static void moveBuildingFloor(int r, int d){
		if(d == RIGHT) moveArrRight(building[r]);
		else if(d==LEFT)moveArrLeft(building[r]);
		
		
	}

	private static boolean containsSameItem(int[] arr1, int[] arr2) {
		if(arr1.length!=arr2.length) return false;
		
		for(int i=0;i<arr1.length;i++) {
			if(arr1[i] == arr2[i]) return true;
		}
		
		return false;
	}

	private static void moveArrLeft(int[] arr) {
		int temp = arr[arr.length-1];
		for(int i=arr.length-1;i>0;i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = temp;
	}

	private static void moveArrRight(int[] arr) {
		int temp = arr[0];
		for(int i=0;i<arr.length-1;i++) {
			arr[i] = arr[i+1];
		}
		arr[arr.length-1] = temp;
	}
}