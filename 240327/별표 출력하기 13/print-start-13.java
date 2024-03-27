import java.util.*;
public class Main {
    static char[][] pattern;
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        pattern = new char[2*n][n];
        for(int i=0;i<pattern.length;i++) Arrays.fill(pattern[i], ' ');


        getPattern(0, 2*n-1, n, -1);
        getPattern(1, 2*n-2, 1, 1);

        printPattern();

    }

    static void getPattern(int start, int end, int tmp, int value){
        while(start<=end){
            for(int i=0;i<tmp;i++){
                pattern[start][i] = '*';
                pattern[end][i] = '*';
            }
            start+=2;
            end-=2;
            tmp += value;
        }
    }

    static void printPattern(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<pattern.length;i++){
            for(int j=0;j<pattern[i].length;j++){
                sb.append(pattern[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}