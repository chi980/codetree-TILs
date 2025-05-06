import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        // Please write your code here.

        int cnt = 0;
        for(int i=A;i<=B;i++){
            if(isCorrect(i)) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean isCorrect(int n){
        if(n % 3 == 0) return true;

        char[] charArray = Integer.toString(n).toCharArray();
        for(char c:charArray){
            int tmp = c-'0';
            if(tmp % 3 == 0) return true;
        }
        return false;
    }

}