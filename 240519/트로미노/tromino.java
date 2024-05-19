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
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                int cost = BFS(R, C, map, r, c);
                maxCost = Math.max(maxCost, cost);
            }
        }
        System.out.println(maxCost);
    }

    static int BFS(int R, int C, int[][] map, int startr, int startc){
        int max = 0;

        int[][] visited = new int[R][C];
        Queue<int[]> q = new ArrayDeque<>();

        visited[startr][startc] = 1;
        q.offer(new int[]{startr, startc, map[startr][startc]});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curr = cur[0];
            int curc = cur[1];
            int cursum = cur[2];

            for(int d=0;d<4;d++){
                int newr = curr+dr[d];
                int newc = curc+dc[d];

                if(!check(R, C, newr, newc)) continue;
                if(visited[newr][newc]!=0) continue;

                visited[newr][newc] = visited[curr][curc]+1;
                if(visited[newr][newc] == 3){
                    max = Math.max(max, cursum + map[newr][newc]);
                }else{
                    q.offer(new int[]{newr, newc, cursum + map[newr][newc]});
                }
            }
        }
        return max;
    }

    static boolean check(int R, int C, int r, int c){
        if(r< 0 || c < 0 || r>=R || c >=C) return false;
        return true;
    }
}