import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        // 행먼저 확인
        for(int i=0;i<n;i++){
            int value = 0; // 1~100 사이의 숫자
            int cnt = 0;
            for(int j=0;j<n;j++){
                if(value == map[i][j]) cnt++;
                else{
                    if(cnt >= m){
                        answer++;
                        break;
                    }else{
                        value = map[i][j];
                        cnt = 1;
                    }
                }
            }
            if(cnt >= m){
                answer++;
            }
        }
        // 열먼저 확인
        for(int i=0;i<n;i++){
            int value = 0; // 1~100 사이의 숫자
            int cnt = 0;
            for(int j=0;j<n;j++){
                if(value == map[j][i]) cnt++;
                else{
                    if(cnt >= m){
                        answer++;
                        break;
                    }else{
                        value = map[j][i];
                        cnt = 1;
                    }
                }
            }
            if(cnt >= m){
                answer++;
            }
        }

        System.out.println(answer);
    }
}