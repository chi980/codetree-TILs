import java.util.*;
import java.io.*;
public class Main {
    	static int[] dr= new int[]{-1,0,1,0};
	static int[] dc =new int[] {0,1,0,-1};
	
	static final int EMPTY = 0;
	static int R, C;
	static int artifactCnt;
	static int[][] map;
	static Queue<Integer> artifactList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int K = Integer.parseInt(st.nextToken()); // 반복 횟수
		artifactCnt = Integer.parseInt(st.nextToken()); // 벽면 유물 조각 갯수
		
		artifactList = new ArrayDeque<Integer>();
		R = C = 5;
		map = new int[R][C];
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<artifactCnt;i++) {
			artifactList.offer(Integer.parseInt(st.nextToken()));
		}
		
		for(int k=0;k<K;k++) {
			
			// 3x3 격자 선택과 1차 유물 획득
			int firstArtifactSum = selectRotateDimensionAndRetrieveArtifact();
			int sum = firstArtifactSum;
			// 2차 유물 획득
			while(true) {
				int multiArtifactSum = RetrieveArtifact();
				if(multiArtifactSum == 0) break;
				sum += multiArtifactSum;
			}
			
//			System.out.printf("k: %d, 유물 총합:%d\n", k, sum);
//			System.out.println("=======================================");
			if(sum == 0) break;
			System.out.print(sum+" ");
			
		}
	}
	private static int RetrieveArtifact() {
		List<Integer> curArtifactList = getArtifactList();
		int curArtifactSum = curArtifactList.size();
		for(int pos : curArtifactList) {
			map[pos/C][pos%C] = EMPTY;
		}
//		System.out.println("2차 유물 획득 후");
//		printMap();
//		System.out.println("------------------------------");
		putArtifact();
		
		return curArtifactSum;
	}
	private static void putArtifact() {
		for(int c=0;c<C;c++) {
			for(int r=4;r>=0;r--) {
				if(map[r][c]!=EMPTY) continue;
				if(artifactList.isEmpty()) return;
				map[r][c] = artifactList.poll();
			}
		}
//		System.out.println("유물 넣은 후");
//		printMap();
//		System.out.println("남은 유물: "+artifactList);
//		System.out.println("------------------------------");
	}
	private static int selectRotateDimensionAndRetrieveArtifact() {
		int maxArtifactSum = 0;
		int maxR = -1; 
		int maxC = -1;
		int maxRot = 4;
		List<Integer> maxArtifactList = null;
		
		for(int c=0;c<3;c++) {
			for(int r=0;r<3;r++) {
				// 90도 회전을 2번 하면 -> 180도 회전, 3번 하면 -> 270도 회전이 됨
				for(int rot=1;rot<=3;rot++) {
//					System.out.printf("현재 r, c, rot: %d, %d, %d\n", r, c, rot);
					rotateDimension(r, c);
//					printMap();
					List<Integer> curArtifactList = getArtifactList();
					int curArtifactSum = curArtifactList.size();
//					System.out.println();
//					System.out.println("curArtifactSum: "+curArtifactSum);
					
					if(maxArtifactSum < curArtifactSum || (maxArtifactSum == curArtifactSum && maxRot > rot)) {
//						System.out.println("maxsum: "+maxArtifactSum+", 현재sum:"+curArtifactSum);
//						System.out.println("기존 rot: "+maxRot+", 현재 rot: "+rot);
						maxArtifactSum = curArtifactSum;
						maxR = r;
						maxC = c;
						maxRot = rot;
						maxArtifactList = curArtifactList;
//						System.out.println("갱신되었어요!");
					}
//					System.out.println("----------------------------------------");
				}
				// 90도 회전을 3번 하면 360도 회전으로 원래대로 된다.
				rotateDimension(r,c);
			}
		}
		
//		System.out.printf("최종 sum, r, c, rot: %d %d, %d, %d\n",maxArtifactSum,maxR, maxC, maxRot);
//		System.out.println(maxArtifactList.size());
//		System.out.println(maxArtifactList);
		
		// 회전
		for(int rot=0;rot<maxRot;rot++) {
			rotateDimension(maxR, maxC);
		}
		
//		 초기화
		for(int pos : maxArtifactList) {
			map[pos/C][pos%C] = EMPTY;
		}
//		System.out.println("1차 유물 획득 후");
//		printMap();
//		System.out.println("------------------------------");
		putArtifact();
		
		return maxArtifactSum;
	}

	private static List<Integer> getArtifactList() {
		int valueSum = 0;
		boolean[][] visited = new boolean[R][C];
		
		List<Integer> artifactList = new ArrayList<>();
		
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c] == EMPTY) continue;
				if(visited[r][c]) continue;
				List<Integer> curArtifactList = BFS(r,c,visited);
				
				if(curArtifactList.size() >= 3) artifactList.addAll(curArtifactList);
			}
		}
		return artifactList;
	}
	private static List<Integer> BFS(int startr, int startc, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[startr][startc] = true;
		q.offer(new int[] {startr, startc});
		List<Integer> artifactList = new ArrayList<>();
		artifactList.add(startr*C+startc);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			for(int d=0;d<4;d++) {
				int newr = curr+dr[d];
				int newc = curc+dc[d];
				
				if(newr < 0 || newc < 0 || newr >= R || newc >= C) continue;
				if(map[newr][newc] == EMPTY) continue;
				if(visited[newr][newc]) continue;
				if(map[newr][newc] != map[curr][curc]) continue;
				
				visited[newr][newc] = true;
				q.offer(new int[] {newr, newc});
				artifactList.add(newr*C+newc);
			}
			
		}
		
		return artifactList;
	}
	private static void printMap() {
		for(int i=0;i<R;i++)System.out.println(Arrays.toString(map[i]));
	}
	/**
	 * 왼쪽 맨 위 r,c를 받고 90도 회전하는 함수
	 * @param topr 왼쪽 맨 위 r
	 * @param topc 왼쪽 맨 위 c
	 */
	private static void rotateDimension(int topr, int topc) {
		int[][] origin = new int[3][3];
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				origin[r][c] = map[topr+r][topc+c];
			}
		}
		
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				map[topr+c][topc+2-r] = origin[r][c];
			}
		}
		
	}
}