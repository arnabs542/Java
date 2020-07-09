package easy;

//https://www.hackerrank.com/challenges/beautiful-binary-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Beautiful_Binary_String {
	static int beautifulBinaryString(String b) {
		int i = 0;
		int count = 0;
		while (i < b.length() - 2) {
			if (b.substring(i, i + 3).equals("010") ) {
				count ++;
				i += 3;
			}
			else i ++;
		}
		return count;
	}
}
