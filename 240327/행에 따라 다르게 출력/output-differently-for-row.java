import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int value = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(value+" ");
                if(i%2==0) value++;
                else value += 2;
            }
            if(i%2 == 0) value++;
            else value--;
            System.out.println();
        }
    }
}