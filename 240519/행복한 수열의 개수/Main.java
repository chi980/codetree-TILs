import java.util.Scanner;
/**
* 기본적인 로직은 내가 짠 코드와 비슷하나 반복되는 코드를 줄인 것이 특징 -> int를 멤버변수로 선언하는 것도 좋았을 듯 
* 배열을 매개변수로 넘기는 것도 생각해봤는데 공간낭비일 것 같아서 안했는데 이런 방법도 있음을 알았다
**/
public class Main {
    public static final int MAX_N = 100;
    
    public static int n, m;
    public static int[][] grid = new int[MAX_N][MAX_N];
    
    public static int[] seq = new int[MAX_N];
    
    public static boolean isHappySequence(){
        // 주어진 seq가 행복한 수열인지 판단하는 함수입니다.
        int consecutiveCount = 1, maxCcnt = 1;
        for(int i = 1; i < n; i++) {
            if (seq[i - 1] == seq[i])
                consecutiveCount++;
            else
                consecutiveCount = 1;
            
            maxCcnt = Math.max(maxCcnt, consecutiveCount);
        }
        
        // 최대로 연속한 회수가 m이상이면 true를 반환합니다. 
        return maxCcnt >= m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        int numHappy = 0;
        
        // 먼저 가로로 행복한 수열의 수를 셉니다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                seq[j] = grid[i][j];
            
            if(isHappySequence())
                numHappy++;
        }
        
        // 세로로 행복한 수열의 수를 셉니다.
        for(int j = 0; j < n; j++){
            // 세로로 숫자들을 모아 새로운 수열을 만듭니다.
            for(int i = 0; i < n; i++)
                seq[i] = grid[i][j];
            
            if(isHappySequence())
                numHappy++;
        }

        System.out.print(numHappy);
    }
}
