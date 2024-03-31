import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();

        int aInt = getInt(a);
        int bInt = getInt(b);
        System.out.println(aInt+bInt);

    }

    static int getInt(String s){
        int result = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                result*=10;
                result+=Character.getNumericValue(c);
            }
        }
        return result;
    }
}