import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		printHelloWorld(N);
	}

	private static void printHelloWorld(int n) {
		if(n==0) {
			return;
		}
		
		System.out.println("HelloWorld");
		printHelloWorld(n-1);
	}

}