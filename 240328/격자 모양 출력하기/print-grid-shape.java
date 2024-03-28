import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] board = new int[n][n];

        for(int i=0;i<m;i++){
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;

            board[r][c] = (r+1)*(c+1);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        
    }
}