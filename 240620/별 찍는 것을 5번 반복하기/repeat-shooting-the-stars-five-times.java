public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        final int n = 10;
        for(int i=0;i<5;i++){
            printStars(n);
        }
    }

    public static void printStars(int n){
        for(int i=0;i<n;i++){
            System.out.print("*");
        }
        System.out.println();
    }
}