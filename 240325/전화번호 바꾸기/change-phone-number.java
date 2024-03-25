import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        char[] phoneNumber = sc.nextLine().toCharArray();
        char[] tmp = new char[4];
        for(int i=4;i<8;i++){
            tmp[i-4] = phoneNumber[i];
            phoneNumber[i] = phoneNumber[i+5];
            phoneNumber[i+5] = tmp[i-4];
        }
        // System.out.println(Arrays.toString(tmp));
        // System.out.println(Arrays.toString(phoneNumber));
        StringBuilder sb = new StringBuilder();
        for(char c : phoneNumber) sb.append(c);
        System.out.println(sb.toString());
    }
}