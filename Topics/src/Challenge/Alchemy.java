package Challenge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

//https://www.facebook.com/codingcompetitions/hacker-cup/2020/qualification-round/problems/B

public class Alchemy {
	
	public static char alchemy(String stones) {
		char prevRes = stones.charAt(0);
		boolean prevSuc = true;
		
		for (int i = 2; i < stones.length(); i += 2 ) {
			char s1 = stones.charAt(i);
			char s2 = stones.charAt(i - 1);
			
			//Three stones same
			if (s1 == s2 && s1 == prevRes) {
				prevSuc = false;
				prevRes = s1;
			}
			//Success prevent
			else if (!prevSuc && s1 == s2 && s1 != prevRes) {
				prevSuc = true;
			}
			else if (prevSuc) {
				prevRes = (s1 + s2 + prevRes > 'A'+'A'+'B'? 'B': 'A');
			}
		}
		
		return (prevSuc)? 'Y':'N';
	}

	
	public static void main(String[]args) throws FileNotFoundException {
		File file = new File("src/Challenge/alchemy.txt");
		Scanner scan = new Scanner(file);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		for (int i = 1; i <= numCases; i ++ ) {
			int no = scan.nextInt();
			scan.nextLine();
			String stones = scan.nextLine();
			
			System.out.println("Case #" + i + ": " + alchemy(stones) );
		}
		
		scan.close();
		
	}
	
}
