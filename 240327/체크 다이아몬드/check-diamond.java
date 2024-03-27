import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int empty = n-1;
        int star = 1;
        for(int i=0;i<2*n-1;i++){
            for(int j=0;j<empty;j++){
                System.out.print(" ");
            }
            for(int j=0;j<star;j++){
                System.out.print("*"+" ");
            }

            System.out.println();
            if(i < n-1){
                empty--;
                star++;
            }else{
                empty++;
                star--;
            }
        }
    }
}