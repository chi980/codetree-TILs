import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        // Please write your code here.

        int cnt = 0;
        for(int i=A;i<=B;i++){
            if(is369Number(i)) cnt++;
        }

        System.out.println(cnt);
    }

    /**
    *. toString()은 내부적으로 문자열 객체 생성 + 문자 배열 생성까지 동반 → 오버헤드 있음
    **/
    public static boolean is369NumberByArray(int n){
        if(n % 3 == 0) return true;

        char[] charArray = Integer.toString(n).toCharArray();
        for(char c:charArray){
            int tmp = c-'0';
            if(tmp!=0 && tmp % 3 == 0) return true;
        }
        return false;
    }
    public static boolean is369Number(int n){
        if(n % 3 == 0) return true;

        while(n > 0){
            if(n % 10 == 3 || n % 10 == 6 || n % 10 == 9)
                return true;
            n /= 10;
        }
        return false;
    }
}