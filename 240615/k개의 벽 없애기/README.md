# [k개의 벽 없애기 ](https://www.codetree.ai/missions/2/problems/remove-k-walls)

|유형|문제 경험치|난이도|
|---|---|---|
|[Intermediate Low / BFS / 가중치가 동일한 그래프에서의 BFS](https://www.codetree.ai/missions?missionId=2)|70xp|![보통][medium]|


3차원 배열과 BFS로 풀었으나 BackTracking과 BFS로 푸는 방법도 존재함
```
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    // 전역 변수 선언:
    public static int n, k;
    
    public static int[][] a = new int[MAX_N][MAX_N];
    
    public static int r1, c1, r2, c2;
    
    public static ArrayList<Pair> stonePos = new ArrayList<>();

    // bfs에 필요한 변수들 입니다.
    public static Queue<Pair> q = new LinkedList<>();
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int[][] step = new int[MAX_N][MAX_N]; // step[i][j] : 시작점으로부터 
                            // (i, j) 지점에 도달하기 위한 
                            // 최단거리를 기록합니다.
    
    public static int ans = INT_MAX;
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    // 격자를 벗어나지 않으면서, 벽도 없고, 아직 방문한 적이 없는 곳이라면
    // 지금 이동하는 것이 최단거리임을 보장할 수 있으므로 가야만 합니다. 
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && a[x][y] == 0 && !visited[x][y];
    }
    
    // queue에 새로운 위치를 추가하고
    // 방문 여부를 표시해줍니다.
    // 시작점으로 부터의 최단거리 값도 갱신해줍니다.
    public static void push(int nx, int ny, int newStep) {
        q.add(new Pair(nx, ny));
        visited[nx][ny] = true;
        step[nx][ny] = newStep;
    }
    
    // bfs를 통해 최소 이동 횟수를 구합니다.
    public static int BFS() {
        // queue에 남은 것이 없을때까지 반복합니다.
        while(!q.isEmpty()) {
            // queue에서 가장 먼저 들어온 원소를 뺍니다.
            Pair currPos = q.poll();
            int x = currPos.x, y = currPos.y;
    
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};
    
            // queue에서 뺀 원소의 위치를 기준으로 4방향을 확인해봅니다.
            for(int dir = 0; dir < DIR_NUM; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
    
                // 아직 방문한 적이 없으면서 갈 수 있는 곳이라면
                // 새로 queue에 넣어줍니다.
                if(canGo(nx, ny))
                    // 최단 거리는 이전 최단거리에 1이 증가하게 됩니다. 
                    push(nx, ny, step[x][y] + 1);
            }
        }
    
        // 도착점에 가는 것이 가능할때만
        // 답을 갱신해줍니다.
        if(visited[r2][c2])
            return step[r2][c2];
        else
            return INT_MAX;
    }
    
    public static void findMin(int idx, int cnt) {
        if(idx == (int) stonePos.size()) {
            if(cnt == k) {
                // visited, step 값을 초기화 해줍니다.
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < n; j++) {
                        visited[i][j] = false;
                        step[i][j] = 0;
                    }
                
                // bfs를 이용해 최소 이동 횟수를 구합니다.
                // 시작점을 queue에 넣고 시작합니다.
                push(r1, c1, 0);
                int minDist = BFS();
                ans = Math.min(minDist, ans);
            }
            return;
        }
        
        a[stonePos.get(idx).x][stonePos.get(idx).y] = 0;
        findMin(idx + 1, cnt + 1);
        a[stonePos.get(idx).x][stonePos.get(idx).y] = 1;
        
        findMin(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                if(a[i][j] > 0)
                    stonePos.add(new Pair(i, j));
            }
        
        r1 = sc.nextInt();
        c1 = sc.nextInt();
        r1--; c1--;
        r2 = sc.nextInt();
        c2 = sc.nextInt();
        r2--; c2--;
        
        findMin(0, 0);

        // 출력:
        if(ans == INT_MAX)  // 불가능한 경우라면
            ans = -1;       // -1을 답으로 넣어줍니다.
        
        System.out.print(ans);
    }
}
```





[b5]: https://img.shields.io/badge/Bronze_5-%235D3E31.svg
[b4]: https://img.shields.io/badge/Bronze_4-%235D3E31.svg
[b3]: https://img.shields.io/badge/Bronze_3-%235D3E31.svg
[b2]: https://img.shields.io/badge/Bronze_2-%235D3E31.svg
[b1]: https://img.shields.io/badge/Bronze_1-%235D3E31.svg
[s5]: https://img.shields.io/badge/Silver_5-%23394960.svg
[s4]: https://img.shields.io/badge/Silver_4-%23394960.svg
[s3]: https://img.shields.io/badge/Silver_3-%23394960.svg
[s2]: https://img.shields.io/badge/Silver_2-%23394960.svg
[s1]: https://img.shields.io/badge/Silver_1-%23394960.svg
[g5]: https://img.shields.io/badge/Gold_5-%23FFC433.svg
[g4]: https://img.shields.io/badge/Gold_4-%23FFC433.svg
[g3]: https://img.shields.io/badge/Gold_3-%23FFC433.svg
[g2]: https://img.shields.io/badge/Gold_2-%23FFC433.svg
[g1]: https://img.shields.io/badge/Gold_1-%23FFC433.svg
[p5]: https://img.shields.io/badge/Platinum_5-%2376DDD8.svg
[p4]: https://img.shields.io/badge/Platinum_4-%2376DDD8.svg
[p3]: https://img.shields.io/badge/Platinum_3-%2376DDD8.svg
[p2]: https://img.shields.io/badge/Platinum_2-%2376DDD8.svg
[p1]: https://img.shields.io/badge/Platinum_1-%2376DDD8.svg
[passed]: https://img.shields.io/badge/Passed-%23009D27.svg
[failed]: https://img.shields.io/badge/Failed-%23D24D57.svg
[easy]: https://img.shields.io/badge/쉬움-%235cb85c.svg?for-the-badge
[medium]: https://img.shields.io/badge/보통-%23FFC433.svg?for-the-badge
[hard]: https://img.shields.io/badge/어려움-%23D24D57.svg?for-the-badge
