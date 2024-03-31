import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        boolean flag = false;
        for(int i=1;i<=a.length();i++){
            String result = a.substring(i, a.length()) + a.substring(0,i);
            
            if(result.equals(b)){
                flag = true;
                System.out.println(i);
                break;
            }
        }

        if(!flag) System.out.println(-1);
    }
}