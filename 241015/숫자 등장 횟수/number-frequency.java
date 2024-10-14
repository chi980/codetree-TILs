import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> numberCntMap = new HashMap<>();
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(st.nextToken());
			numberCntMap.put(number, numberCntMap.getOrDefault(number, 0)+1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int number = Integer.parseInt(st.nextToken());
			if(numberCntMap.containsKey(number)) {
				System.out.print(numberCntMap.get(number)+" ");
			}else {
				System.out.print("0 ");
			}

		}
	}

}