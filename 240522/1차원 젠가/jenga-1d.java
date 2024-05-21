import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		List<Integer> blocks = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(br.readLine());
			blocks.add(tmp);
		}
		
		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken())-1;
			int e1 = Integer.parseInt(st.nextToken())-1;
			
			blocks = popBlocks(blocks, s1, e1);
		}
		System.out.println(blocks.size());
		for (Iterator iterator = blocks.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
	}

	private static List<Integer> popBlocks(List<Integer> blocks, int s1, int e1) {
		List<Integer> newBlocks = new ArrayList<>();
		for(int i=0;i<s1;i++) {
			newBlocks.add(blocks.get(i));
		}
		
		for (int i = e1+1; i < blocks.size(); i++) {
			newBlocks.add(blocks.get(i));
		}
		
		return newBlocks;
	}
}