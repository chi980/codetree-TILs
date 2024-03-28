import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        s1 = s1.replace(" ", "");
        s2 = s2.replace(" ", "");
        System.out.println(s1+s2);
    }
}