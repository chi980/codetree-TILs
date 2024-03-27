import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tmp ;
        double sum = 0.0;
        int cnt = 0;
        while((tmp = sc.nextInt())/10 == 2){
            // System.out.println(tmp);
            sum += tmp;
            cnt++;
        }

        double avg = sum / cnt;
        System.out.printf("%.2f", avg);
    }
}