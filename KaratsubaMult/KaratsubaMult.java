import java.io.*;
import java.util.*;

public class KaratsubaMult {
	public static void main(String[] args) throws IOException {
		String input = args[args.length-1];
		FileReader file = new FileReader(input);
		Scanner in = new Scanner(file);	
		
		int n = Integer.parseInt(in.nextLine());
		int[] a = new int[n+1];
		int[] b = new int[n+1]; 
		String[] aCo = in.nextLine().split(" ");
		String[] bCo = in.nextLine().split(" ");

		for (int i = 0; i < n + 1; i++) {
			a[i] = Integer.parseInt(aCo[i]);
			b[i] = Integer.parseInt(bCo[i]);
		}

		int[] result = multiply(a, b);

		for (int i = 0; i < result.length - 1; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static int[] multiply(int[] a, int[] b) {
		int[] result = new int[2*a.length];
		if (a.length <= 4) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < b.length; j++) {
					result[i+j] += a[i] * b[j];
				}
			}
		} else {
			int[] aLow = new int[a.length/2];
			int[] aHigh = new int[a.length/2];
			int[] aLowHigh = new int[a.length/2];
			int[] bLow = new int[b.length/2];
			int[] bHigh = new int[b.length/2];
			int[] bLowHigh = new int[a.length/2];

			for (int i = 0; i < a.length/2; i++) {
				aLow[i] = a[i];
				aHigh[i] = a[a.length/2 + i];
				aLowHigh[i] = aLow[i] + aHigh[i];
			}
			for (int i = 0; i < b.length/2; i++) {
				bLow[i] = b[i];
				bHigh[i] = b[b.length/2 + i];
				bLowHigh[i] = bLow[i] + bHigh[i];
			}

			int[] resultLow = multiply(aLow, bLow);
			int[] resultHigh = multiply(aHigh, bHigh);
			int[] resultLowHigh = multiply(aLowHigh, bLowHigh);

			int[] resultMid = new int[a.length];
			for (int i = 0; i < a.length; i++) {
				resultMid[i] = resultLowHigh[i] - resultLow[i] - resultHigh[i];
			}

			for (int i = 0; i < a.length; i++) {
				result[i] += resultLow[i];
				result[i + a.length] += resultHigh[i];
				result[i + a.length/2] += resultMid[i];
			}  	
		}
		return result;
	}
}