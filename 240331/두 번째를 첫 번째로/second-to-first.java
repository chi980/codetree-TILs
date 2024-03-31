import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();

        char origin = a.charAt(1);
        char replace = a.charAt(0);

        String newS = a.replace(origin, replace);
        System.out.println(newS);
    }
}