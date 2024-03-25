import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        double result = Math.round((a+b)/(a-b)*100.0)/100.0;
        System.out.printf("%.2f",result);
    }
}