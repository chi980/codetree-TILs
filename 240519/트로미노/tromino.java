import java.util.*;
import java.io.*;
public class Main {

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];

        for(int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<C;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int maxCost = 0;
        boolean[][] visited = new boolean[R][C];
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                visited[r][c] = true;
                int cost = DFS(R, C, map, r, c, 0, visited);
                maxCost = Math.max(maxCost, cost);
                visited[r][c] = false;
            }
        }
        System.out.println(maxCost);
    }

    static int DFS(int R, int C, int[][] map, int curr, int curc, int depth, boolean[][] visited){
        if(depth == 2){
            return map[curr][curc];
        }

        int maxCost = 0;
        for(int d=0;d<4;d++){
            int newr = curr+dr[d];
            int newc = curc+dc[d];

            if(!check(R, C, newr, newc)) continue;
            if(visited[newr][newc]) continue;

            visited[newr][newc] = true;
            int curCost = DFS(R, C, map, newr, newc, depth+1, visited);
            maxCost = Math.max(maxCost, curCost);
            visited[newr][newc] = false;
        }

        return maxCost+map[curr][curc];
    }


    static boolean check(int R, int C, int r, int c){
        if(r< 0 || c < 0 || r>=R || c >=C) return false;
        return true;
    }

    static void print(int[][] arr){
        for(int i=0;i<arr.length;i++)System.out.println(Arrays.toString(arr[i]));
        System.out.println();
    }
}