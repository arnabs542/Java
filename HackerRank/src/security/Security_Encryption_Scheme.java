package security;



import java.util.Scanner;


public class Security_Encryption_Scheme {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int res = 1;
		
		for (int i = 1; i <= n; ++i)
			res *= i;
		System.out.println(res);
		scan.close();
	}
}
