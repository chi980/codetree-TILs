import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int[][] nums = new int[4][4];

        int result = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)nums[i][j] = sc.nextInt();
            for(int j=0;j<i+1;j++){
                result += nums[i][j];
            }
        }

        System.out.println(result);
    }
}