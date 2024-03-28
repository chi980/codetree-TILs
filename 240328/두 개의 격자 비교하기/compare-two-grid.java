import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] board1 = new int[n][m];
        int[][] board2 = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                board1[i][j] = sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                board2[i][j] = sc.nextInt();
            }
        }

        int[][] isDiff = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board1[i][j] != board2[i][j]) isDiff[i][j]=1;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(isDiff[i][j]+" ");
            }
            System.out.println();
        }

    }
}