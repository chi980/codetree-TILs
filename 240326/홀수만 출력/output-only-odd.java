import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i=a;i<=b;i++){
            if(i%2 !=0) sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}