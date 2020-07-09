package KMKProgChallenge;

import java.util.Scanner;
public class LogicGateTime {

	public static void main(String[]args) {
			Scanner scan = new Scanner(System.in);
			
			int numCases = scan.nextInt();
			
			for (int caseNo = 1; caseNo <= numCases; caseNo++ ) {
				boolean a = scan.nextBoolean();
				boolean b = scan.nextBoolean();
				boolean c = scan.nextBoolean();
				boolean d = scan.nextBoolean();
				boolean e = scan.nextBoolean();
				boolean f = scan.nextBoolean();
				
				boolean g = a && b;
				boolean h = b && c && d;
				boolean i = !e;
				boolean j = e || f;
				
				boolean k = !(g == h);
				boolean l = !h;
				
				boolean m = !(k || l);
				boolean n = d || e;
				
				boolean o = !(i && j);
				
				//p has inputs: m, n, o
				//p will be true if exactly 1 input is true, or 3 of inputs are true
				boolean p = (m && !n && !o) || (!m && n && !o) || (!m && !n && o) || (m && n && o);
			
				System.out.println("Case #" + caseNo + ": " + p);
			}
	}
	
	
}
