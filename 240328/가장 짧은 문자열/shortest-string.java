import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int min = 21;
        int max = 0;

        for(int i=0;i<3;i++){
            String s = sc.nextLine();
            min = Math.min(min, s.length());
            max = Math.max(max, s.length());
        }

        System.out.println(max-min);
    }
}