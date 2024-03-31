import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int idx = s.indexOf("e");

        String result = s.substring(0, idx) + s.substring(idx+1, s.length());
        System.out.println(result);
    }
}