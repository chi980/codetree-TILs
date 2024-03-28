import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int[] nums = new int[10];

        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();

        for(int i=2;i<nums.length;i++){
            nums[i] = nums[i-1]+2*nums[i-2];
            
        }

        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }
}