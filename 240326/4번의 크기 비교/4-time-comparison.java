import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        int[] nums = new int[4];
        for(int i=0;i<4;i++) nums[i] = sc.nextInt();

        for(int i=0;i<4;i++){
            if(nums[i] < a) System.out.println(1);
            else System.out.println(0);
        } 
    }
}