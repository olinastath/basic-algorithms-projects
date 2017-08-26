import java.util.*;

public class StringGenerator {

	public String StringGenerator(int length){
		char[] letters = {'A','C','G','T'};
		String s = "";
		Random r = new Random();
		int a;
		while (length > 0) {
			s += letters[r.nextInt(10000)%4];
			length--;
		}
		
		return s;
	}

}
