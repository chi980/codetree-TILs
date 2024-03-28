import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = new int[10];

        for(int i=0;i<10;i++){
            nums[i] = sc.nextInt();
            if(nums[i]%3==0){
                System.out.println(nums[i-1]);
                break;
            }
        }
    }
}