import java.util.*;
public class Main {
    
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[][] pattern = init(n);

        getPattern(pattern, n);
        printPattern(pattern);
    }

    static void printPattern(char[][] pattern){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<pattern.length;i++){
            for(int j=0;j<pattern[i].length;j++){
                sb.append(pattern[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void getPattern(char[][] pattern, int n){
        for(int i=0;i<n;i++){
            pattern[0][i] = '*';
            pattern[n-1][i] = '*';
            pattern[i][0] = '*';
            pattern[i][n-1] = '*';
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                pattern[i][j] = '*';
            }
        }
    }

    static char[][] init(int n){
        char[][] pattern =  new char[n][n];
        for(int i=0;i<n;i++) Arrays.fill(pattern[i], ' ');

        return pattern;
    }
}