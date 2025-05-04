import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.

        if(n <= m) System.out.println(gcd(n, m));
        else System.out.println(gcd(m, n));
    }

    public static int gcd(int a, int b){
        if(a == 0) return b;
        else return gcd(b%a, a);
    }
}