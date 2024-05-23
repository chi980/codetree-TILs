import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		
		int listSize = list.size();
		
		while(true) {
			list = bomb(list, M);
			if(listSize == list.size()) break;
			else listSize = list.size();
		}
		
		if(list.size() ==0) System.out.println("0");
		else {
			System.out.println(list.size());
			list.stream().forEach(System.out::println);
		}
	}

	private static List<Integer> bomb(List<Integer> list, int M) {
		Stack<Integer> stack = new Stack<>();
		
		int cnt = 0;
		for (Integer integer : list) {
			if(stack.isEmpty()) {
				stack.push(integer);
				cnt = 1;
			}else {
				if(stack.peek() == integer) {
					cnt++;
				}else {
					
					if(cnt >= M) {
						while(cnt > 0) {
							stack.pop();
							cnt--;
						}
					}
					cnt = 1;
				}
				stack.push(integer);
			}

		}
		
		if(cnt >= M) {
			while(cnt > 0) {
				stack.pop();
				cnt--;
			}
		}
		
		List<Integer> result = stack.stream().collect(Collectors.toList());
		
		return result;
	}
}