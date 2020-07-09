package easy;

//https://www.hackerrank.com/challenges/caesar-cipher-1/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Caesar_Cipher {

	static String caesarCipher(String s, int k) {
		String newStr = "";
		int shifting = k % 26;
		for (char a: s.toCharArray()) {
			//If this character is not alphabet
			if (!Character.isAlphabetic(a) ) newStr = newStr.concat(String.valueOf(a) );
			else {
				//If this character is upper case
				if (Character.isUpperCase(a) ) {
					newStr = newStr.concat( String.valueOf( (char)( (a + shifting <= 90)? a+shifting: a+shifting-26 ) ) );
				}
				//This character is of lower case
				else {
					newStr = newStr.concat( String.valueOf( (char)( (a + shifting <= 122)? a+shifting: a+shifting-26 ) ) );
				}
				
			}
			
		}
		return newStr;
    }
	
	public static void main(String[] args) {
		System.out.println(caesarCipher("abcdefghijklmnopqrstuvwxyz",1));
		System.out.println(caesarCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ",27));
	}

}
