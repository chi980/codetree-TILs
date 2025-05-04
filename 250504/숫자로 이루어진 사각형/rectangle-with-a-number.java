import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.

        printRectangles(n);
    }

    public static void printRectangles(int N){
        int cnt =1;
        for(int r =0;r<N;r++){
            for(int c=0;c<N;c++){
                if(cnt == 10) cnt = 1;
                System.out.printf("%d ", cnt++);
            }

            System.out.println();
        }
    }
}