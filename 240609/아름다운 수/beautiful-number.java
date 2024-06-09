import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] number;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		number = new int[n];
		cnt = 0;
		
		Permutation(0);
		System.out.println(cnt);
	}
	private static void Permutation(int depth) {
		
		if(depth == number.length) {
			if(isBeautiful(number)) {
//				System.out.println(Arrays.toString(number));
				cnt++;
				}
			return;
		}
		
		for (int i = 1; i <= 4; i++) {
			number[depth] = i;
			Permutation(depth+1);
		}
	}
	private static boolean isBeautiful(int[] number) {
		int last = number[0];
		int cnt = 1;
		for (int idx=1;idx<number.length;idx++) {
			int value = number[idx];
			if(last == value) cnt++;
			else {
//				System.out.println("last: "+last+", cnt: "+cnt);
				if(cnt != 0) {
					if(cnt % last != 0) return false;
				}
				
				last = value;
				cnt = 1;
			}
		}
		if(cnt != 0) {
			if(cnt % last != 0) return false;
		}
		
		return true;
	}
}