import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        String[] str = new String[n];
        for(int i=0;i<n;i++){
            str[i] = sc.nextLine();
        }

        String alphabet = sc.nextLine();

        int cnt = 0;
        double sum = 0.0;
        for(String s : str){
            if(s.startsWith(alphabet)){
                cnt++;
                sum += s.length();
            }
        }
        System.out.printf("%d %.2f", cnt, sum/cnt);
    }
}