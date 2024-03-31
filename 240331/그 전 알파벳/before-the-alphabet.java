import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        char a = sc.nextLine().charAt(0);
        char[] alphabets = new char[26];
        char tmp = 'a';
        for(int i=0;i<alphabets.length;i++) {
            alphabets[i] = tmp;
            tmp++;
            }        

        int idx = a-'a';
        idx = (idx+25)%26;
        System.out.println(alphabets[idx]);
    }
}