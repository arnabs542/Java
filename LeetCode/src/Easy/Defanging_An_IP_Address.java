package Easy;

//https://leetcode.com/problems/defanging-an-ip-address/

import java.util.regex.*;

public class Defanging_An_IP_Address {
	
	 public static String defangIPaddr(String address) {
	        return solutionOne(address);
	 }
	
	 //Basic solution using substring(), indexOf()..., can be further specified since Ipv4 always has 3 periods only
	 static String solutionOne(String address) {
		 int start = 0;
		 int index = address.indexOf(".");
		 String newAdd = "";
		 while (index != -1) {
			 newAdd += address.substring(start, index) + "[.]";
			 start = index + 1;
			 index = address.indexOf(".", index + 1);
		 }
		 newAdd += address.substring(start);
		 return newAdd;
	 }
	 
	 //Solution using Regular Expression
	 //Even better, using address.replaceAll(regex, replacement)
	 static String solutionTwo(String address) {
		 Matcher matcher = Pattern.compile("\\.").matcher(address);
		 return matcher.replaceAll("[.]");
	 }
	 
	 
	 public static void main(String[]args) {
		 String ip = "1.1.1.1";
		 System.out.println(solutionOne(ip));
		 System.out.println(solutionTwo(ip));
	 }
}
