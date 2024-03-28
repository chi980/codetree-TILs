import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] board = new int[n][n];

        for(int c=0;c<n;c++) board[0][c] = 1;
        for(int r=0;r<n;r++) board[r][0] = 1;

        for(int r=1;r<n;r++){
            for(int c=1;c<n;c++){
                board[r][c] += board[r-1][c];
                board[r][c] += board[r][c-1];
                board[r][c] += board[r-1][c-1];
            }
        }

        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                System.out.print(board[r][c]+ " ");
            }
            System.out.println();
        }
    }
}