package Easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/unique-email-addresses/
/*
 * This is a string problem.
 * 
 * For '.', we ignore it. For '+', we will ignore '+' and everything after it. This rule applies for local name.
 * This is the way to uniformize the email addresses.
 * Then, we store in HashSet, so we can know if current email is unique, or already been sent email before.
 */

public class Unique_Email_Addresses {
	
	public int numUniqueEmails(String[] emails) {
		Set<String> exist = new HashSet<>();

		for (String s: emails) {
			// Split into local and domain
			String[] split = s.split("@");
			String local = split[0], domain = split[1];
			StringBuilder sb = new StringBuilder();
			
			// Uniform the local name - Ignore ., ignore everything after +
			for (char c: local.toCharArray()) {
				if (c == '.') continue;
				if (c == '+') break;
				sb.append(c);
			}
			
			exist.add(sb.toString() + "@" + domain);
		}
		return exist.size();
    }
	
}
