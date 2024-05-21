import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		int[] triangles = new int[3*n];
		
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				triangles[i*n+j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[] newTriangles = new int[3*n];
		int len = 3*n;
		
		for(int i=0;i<len;i++) {
			newTriangles[(i+1*t)%len] = triangles[i];
		}
		

		printTriangle(newTriangles, n);
	}
	
	static void printTriangle(int[] triangle, int n) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<n;j++) {
				bw.write(triangle[i*n+j]+" ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}