import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i=n;i>=1;i--){
            for(int j=1;j<=i;j++){
                System.out.printf("%d * %d = %d", n-i+1, j, (n-i+1)*j);
                if(i!=j) System.out.print(" / ");
                else System.out.print("\n");
            }
            // System.out.println();
        }
    }
}