import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int tmp = 9;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sb.append(tmp);
                if(tmp <= 9) tmp--;
                if(tmp == 0) tmp = 9;
                
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}