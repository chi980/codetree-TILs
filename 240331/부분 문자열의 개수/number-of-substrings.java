import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        int cnt = 0;

        for(int i=0;i+b.length()<=a.length();i++){
            String sub = a.substring(i, i+b.length());
            // System.out.println(sub);
            if(sub.equals(b)) cnt++;
        }
        System.out.println(cnt);
    }
}