package Easy;

///https://leetcode.com/problems/add-binary/

public class Add_Binary {
    
	public static String addBinary(String a, String b) {
		if (a.length() > b.length() ) {
			int diff = a.length() - b.length();
			for (int i = 0; i < diff; i++ ) {
				b = "0".concat(b);
			}
		}
		else if (b.length() > a.length() ) {
			int diff = b.length() - a.length();
			for (int i = 0; i < diff; i ++ ) {
				a = "0".concat(a);
			}
		}
		
		StringBuilder stb = new StringBuilder();
		boolean carry = false;
		for (int i = a.length() - 1; i >= 0; i -- ) {
			if (a.charAt(i) != b.charAt(i) ) {
				stb.append( (carry)? "0":"1");
			}
			else if (a.charAt(i) == '0') {
				stb.append( (carry)? "1":"0" );
				if (carry) carry = false;
			}
			else {
				stb.append( (carry)? "1":"0" );
				carry = true;
			}
		}
		if (carry)
			stb.append("1");
		return stb.reverse().toString();
    }
	
}
