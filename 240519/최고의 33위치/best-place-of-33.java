import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int max = 0;
        for(int r=0;r<=N-3;r++){
            for(int c=0;c<=N-3;c++){
                int cnt = count(r, c, map);
                max = Math.max(max, cnt);
            }
        }
        System.out.println(max);
    }

    static int count(int r, int c, int[][] map){
        int cnt = 0 ;
        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                if(map[i][j] == 1) cnt++;
            }
        }

        return cnt;
    }
}