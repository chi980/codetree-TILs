import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		printNumberAsc(N);
		System.out.println("");
		printNumberDesc(N);
	}

	private static void printNumberAsc(int n) {
		if(n==0) return;
		
		printNumberAsc(n-1);
		System.out.print(n+" ");
	}

	private static void printNumberDesc(int n) {
		if(n==0) return;
		
		System.out.print(n+" ");
		printNumberDesc(n-1);
	}
}