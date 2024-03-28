import java.util.*;
public class Main {
    static int[] dr = {-1,1};
    static int[] dc = {0, 0};

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] board = new int[n][n];

        int r = n-1;
        int c = n-1;
        int d = 0;

        int value = 1;
        int cnt = 0;
        while(check(r,c,n)){
            board[r][c] = value;
            value++;
            cnt++;
            if(cnt == n){
                cnt=0;
                c -=1;
                if(!(check(r,c,n))) break;
                board[r][c] = value;
                d = (d+1)%2;
            }else{
            r += dr[d];
            c += dc[d];

            }


        }
        // board[n-1][0] = value;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    static boolean check(int r, int c, int n){
        if(r<0||c<0||r>=n||c>=n)return false;
        return true;
    }
}