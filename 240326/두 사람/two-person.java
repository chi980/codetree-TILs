import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        Scanner sc = new Scanner(System.in);

        boolean flag = false;

        for(int i=0;i<2;i++){
            int age = sc.nextInt();
            char gender = sc.next().charAt(0);
            
            // System.out.println(age+" "+gender);
            if(age >= 19 && gender == 'M') flag = true;
        }

        if(!flag) System.out.println(0);
        else System.out.println(1);
    }
}