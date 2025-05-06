import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.

        System.out.println(isCorrect(n)?"Yes":"No");
    }

    public static boolean isCorrect(int n){
        int a = n/10;
        int b = n%10;

        if((a+b)%5==0 && n%2==0) return true;
        else return false;
    }
}