package PrimeNumGenSwing;

import java.math.BigInteger;

public class PrimeSys implements Runnable {

	private long n;
	private MainWindow parent;
	
	PrimeSys(long n, MainWindow mw){
		super();
		
		this.n = n;
		this.parent = mw;
	}
	
	static boolean isPrime (long n) {
		if (n < 2) return false;
		if (n == 2 || n == 3) return true;
		if (n % 2 == 0 || n % 3 == 0) return false;
		
		long sqrtN = (long)(Math.sqrt(n) + 1);
		for (long tester = 6; tester <= sqrtN; tester += 6) {
			if (n % (tester - 1) == 0 || n % (tester + 1) == 0 ) return false;
		}
		
		return true;
	}

	public void run() {
		long counter = 0;
		for (long i = 2; i <= n; i ++ ) {
			if (isPrime(i) ) {
				parent.getDisplayPane().addListText(i);
				parent.getInputPane().setCounter( ++counter);
			}
			parent.getInputPane().updatePG(n, i);
		}
		
		//Finished
		parent.getButtonPane().doFinishedJob();
	}
	
	public static void main(String[]args) {
		System.out.println(isPrime(1000000007));
		System.out.println(isPrime(100000003));
		System.out.println(isPrime(1000003));
	}
	
	
}
