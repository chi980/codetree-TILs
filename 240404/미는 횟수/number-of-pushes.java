import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        boolean flag = false;
        
        // String result = a;
        for(int i=1;i<=a.length();i++){
            a = a.charAt(a.length()-1)+a.substring(0, a.length()-1)
            
            if(a.equals(b)){
                flag = true;
                System.out.println(i);
                break;
            }
        }

        if(!flag) System.out.println(-1);
    }
}