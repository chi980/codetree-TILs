import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> nums = new ArrayList<>();
        for(int i=0;i<n;i++){
            int a = sc.nextInt();
            if(a % 2 == 0) {
                nums.add(a);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=nums.size()-1;i>=0;i--){
            sb.append(nums.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}