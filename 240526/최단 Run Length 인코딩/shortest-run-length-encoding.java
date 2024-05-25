import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();

		int smallRunLength = Integer.MAX_VALUE;
		for (int i = 0; i < str.length; i++) {
			shift(str);
			smallRunLength = Math.min(smallRunLength, getRunLength(str));
		}
		System.out.println(smallRunLength);

	}

	private static int getRunLength(char[] str) {
		int length = 0;
		char last = 'A';
		int cnt = 0;

		for (char c : str) {
			if (last != c) {
				length += getLength(cnt);
				last = c;
				cnt = 1;
			} else {
				cnt++;
			}
		}

		length += getLength(cnt);
//		System.out.println(Arrays.toString(str));
//		System.out.println(length);
		return length;
	}

	private static int getLength(int cnt) {
		if (cnt == 0)
			return cnt;
		String tmp = Integer.toString(cnt);
		return tmp.length() + 1;
	}

	private static void shift(char[] str) {
		char tmp = str[str.length - 1];
		for (int i = str.length - 1; i > 0; i--) {
			str[i] = str[i - 1];
		}
		str[0] = tmp;
	}
}