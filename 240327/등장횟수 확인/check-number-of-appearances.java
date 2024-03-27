import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int result = 0;
        for(int i=0;i<5;i++){
            int num = sc.nextInt();
            if(num%2==0) result++;

        }
        System.out.println(result);
    }
}