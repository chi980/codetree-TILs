import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String result = "";
        for(char c : s.toCharArray()){
            if(Character.isLowerCase(c)){
                result += Character.toUpperCase(c);
            }else{
                result += Character.toLowerCase(c);
            }
        }
        System.out.println(result);

    }
}