import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += sc.nextInt();
        }

        String s = Integer.toString(sum);
        // System.out.println(s);
        System.out.print(s.substring(1, s.length()));
        System.out.print(s.charAt(0));
    }
}