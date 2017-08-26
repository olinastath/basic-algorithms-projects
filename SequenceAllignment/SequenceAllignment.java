import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class SequenceAllignment {
	public static void main(String[] args) throws IOException {
		String input = args[args.length-1];
		FileReader file = new FileReader(input);
		Scanner in = new Scanner(file);	
		
		String a = in.nextLine();
		String b = in.nextLine();
		int n = a.length();
		int m = b.length();
		a = "_" + a;
		b = "_" + b;
		String s = "";
		String t = "";
		int dif;
		int[][] scores = new int[n+1][m+1];
		for (int i = 1; i <= n; i++){
			scores[i][0] = -1 * i;
		}
		
		for (int i = 1; i <= m; i++){
			scores[0][i] = -1 * i;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j= 1; j <= m; j++) {
				scores[i][j] = max(scores[i-1][j-1] + d(a.charAt(i), b.charAt(j)), scores[i][j-1] + d('_', b.charAt(j)), scores[i-1][j] + d(a.charAt(i), '_'));
			}
		}
		System.out.println(scores[n][m]);
		
		int i = n; int j = m;
		while (i > 0 && j > 0) {
			dif = scores[i][j] - d(a.charAt(i), b.charAt(j));
			if (dif == scores[i-1][j-1]) {
				s += a.charAt(i);
				t += b.charAt(j);
				i--; j--;
			} else if (dif == scores[i][j-1]) {
				t += b.charAt(j);
				s += '_';
				j--;
			} else if (dif == scores[i-1][j]) {
				s += a.charAt(i);
				t += '_';
				i--;
			}
		}				
		while (i > 0) {
			s += a.charAt(i);
			t += '_';
			i--;
		}
		while (j > 0) {
			s += '_';
			t += b.charAt(j);
			j--;
		}
		
		char[] S = s.toCharArray();
		char[] T = t.toCharArray();
		for (i = S.length - 1; i >= 0; i--) {
			System.out.print(S[i]);
		}
		System.out.println();
		for (i = T.length - 1; i >= 0; i--) {
			System.out.print(T[i]);
		}
	}

	private static int max(int i, int j, int k) {
		int l = Math.max(i, j); 
		return Math.max(k, l);
	}

	private static int d(char a, char b) {
		if (a == b) {
			return 2;
		} else {
			return -1;
		}

	}

}
