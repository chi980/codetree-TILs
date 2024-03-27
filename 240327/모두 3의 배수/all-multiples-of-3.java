import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        boolean flag = true;

        for(int i=0;i<5;i++){
            if(sc.nextInt()%3 !=0) flag = false;
        }

        System.out.println(flag?"1":"0");
    }
}