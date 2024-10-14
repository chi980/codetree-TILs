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
		
		Map<String, String> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			map.put(Integer.toString(i+1), s);
			map.put(s, Integer.toString(i+1));
		}
		
		for(int i=0;i<m;i++) {
			String s = br.readLine();
			System.out.println(map.get(s));
		}
	}
}