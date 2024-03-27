import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int flag = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n*2;j++){
                if(j < n-flag) System.out.print("*");
                else if(j > n+flag-1) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
            flag++;
        }

    }
}