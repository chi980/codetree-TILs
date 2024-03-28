import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            int a = sc.nextInt();
            sb.append(" ");
            if(a % 2 == 0) sb.append(a);
        }
        System.out.println(sb.reverse());
    }
}