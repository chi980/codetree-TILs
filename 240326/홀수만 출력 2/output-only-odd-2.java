import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        Scanner sc = new Scanner(System.in);
        int b = sc.nextInt();
        int a = sc.nextInt();


        StringBuilder sb = new StringBuilder();
        for(int i=b;i>=a;i--){
            if(i%2 != 0) sb.append(i).append(" ");
            // if(i != a) sb.append(" ");
        }
        System.out.println(sb);
        // System.out.println(sb.reverse());
    }
}