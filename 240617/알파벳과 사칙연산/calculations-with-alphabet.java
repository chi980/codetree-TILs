import java.util.Arrays;
import java.util.Scanner;

/**
 * 식: 소문자알파벳 + 기호 -> 길이: n
 * 소문자 알파벳에 1~4 숫자를 집어넣어 식의 결과를 최대로 해보자
 * 단, 계산 우선순위는 동일함
 * @author 82108
 *
 */
public class Main {
	static int[] numbers;
	static char[] exp;
	
	static int maxResult;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		exp = sc.nextLine().toCharArray();
		numbers = new int[6];
		
		maxResult = Integer.MIN_VALUE;
		pickNumbers(0);
		System.out.println(maxResult);
	}

	private static void pickNumbers(int depth) {
		if(depth >= numbers.length) {
			int answer = getAnswer();
			maxResult = Math.max(maxResult, answer);
			return;
		}
		
		for (int number = 1; number <= 4; number++) {
			numbers[depth] = number;
			pickNumbers(depth+1);
		}
	}

	private static int getAnswer() {
		int expIdx = 0;
		
		int answer = numbers[exp[expIdx++]-'a'];
		if(exp.length == 1) return answer;
		char lastCommand = exp[expIdx++];
		
		while(expIdx < exp.length) {
			if(expIdx % 2 == 0) {
				int number = numbers[exp[expIdx] - 'a'];
				
				if(lastCommand == '+') answer += number;
				else if(lastCommand == '-') answer -= number;
				else if(lastCommand == '*') answer *= number;
				else break;
			}else {

				lastCommand = exp[expIdx];
			}
			expIdx++;
		}
		
		return answer;
	}
}