package Medium;
import java.util.regex.*;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3362/

/*
 * Either we use a regex or use a divide and conquer (Split the string based on the dots or colons, and validate each chunk)
 * 
 */

public class Validate_IP_Address {
	
	public String validIPAddress(String IP) {
		String ipv4chunk = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
		String ipv6chunk = "[a-fA-F0-9]{1,4}";
		
		boolean isIPv4 = Pattern.matches("^(" + ipv4chunk + "\\.){3}" + ipv4chunk + "$", IP);
		boolean isIPv6 = Pattern.matches("^(" + ipv6chunk + ":){7}" + ipv6chunk + "$", IP);
		
		if (isIPv4) return "IPv4";
		if (isIPv6) return "IPv6";
		return "Neither";
    }
}
