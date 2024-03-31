import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        Deque<Character> dq = new ArrayDeque<>();

        String s = sc.nextLine();
        for(char c : s.toCharArray()) dq.offer(c);

        String command = sc.nextLine();
        for(char c : command.toCharArray()){
            if(c == 'L'){
                dq.offerLast(dq.pollFirst());
            }else{
                dq.offerFirst(dq.pollLast());
            }   
        }
        // System.out.println(dq);
        while(!dq.isEmpty()){
            System.out.print(dq.pollFirst());
        }

    }
}