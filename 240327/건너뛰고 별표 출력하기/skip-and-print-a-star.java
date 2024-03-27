import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int flag = 1;
        for(int i=0;i<n*2;i++){
            for(int j=0;j<flag;j++){
                System.out.print("*");
            }
            System.out.println();
            System.out.println();

            // System.out.println(i);
            // System.out.println(flag);
            if(i < n-1) flag++;
            else flag--;
        }
    }
}