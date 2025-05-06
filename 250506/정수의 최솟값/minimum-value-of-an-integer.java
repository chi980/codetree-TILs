import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        // Please write your code here.

        int result = getResult(a,b,c);

        System.out.println(result);
    }

    public static int getResult(int a, int b, int c){
        int min = Math.min(a,b);
        min = Math.min(min, c);
        return min;
    }
}