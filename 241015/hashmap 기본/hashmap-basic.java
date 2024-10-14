import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 명령
 * 1. add k v -> hashmap에 k를 key로 v를 value로 저장 -> k가 겹친다면 덮어씀
 * 2. remove k -> k를 key를 가진 쌍을 삭제
 * 3. find k -> k를 key로 가진 쌍이 있는지 판단(있다면 value출력, 없다면 None)
 * @author 82108
 *
 */
public class Main {
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_FIND = "find";
	private static final String COMMAND_REMOVE = "remove";

	public static void main(String[] args) throws IOException{
		Map<Integer, Integer> map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] command = br.readLine().split(" ");
			
			if(command[0].equals(COMMAND_ADD)) {
				int k = Integer.parseInt(command[1]);
				int v = Integer.parseInt(command[2]);
				map.put(k, v);
			}else if(command[0].equals(COMMAND_FIND)) {
				int k = Integer.parseInt(command[1]);
				if(map.containsKey(k)) {
					System.out.println(map.get(k));
				}else {
					System.out.println("None");
				}
			}else if(command[0].equals(COMMAND_REMOVE)) {
				int k = Integer.parseInt(command[1]);
				map.remove(k);
			}
		}
	}
}