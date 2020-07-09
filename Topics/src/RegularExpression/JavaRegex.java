package RegularExpression;

import java.util.Scanner;
import java.util.regex.*;
public class JavaRegex {

	/* Basic Regex
	 * 		abc				- Matches exactly "abc" in a string
	 * 		.				- Matches any single character (SINGLE ONLY UNLESS USED QUANTIFIERS)
	 * 		^aaa			- Matches aaa regex at the beginning of the line or the string
	 * 		aaa$			- Matches aaa regex at the end of the line or the string
	 * 		
	 * 		Eg: ^file\w*\.png$ matches all [file.png, file_1234.png, file10201.png]
	 */
	
	//Signs need to be escaped (to search exactly this character and not its metacharacter meaning) : .[{()\^$|?*+
	
	/* Regex Character class
	 * 		[abc]			- a, b or c once time for each only
	 * 		[^abc]			- (Must be used in the square brackets) any class except a, b or c (negation)
	 * 		[a-zA-Z]		- A through Z or a through z inclusive (Range)
	 * 		[abc][12]		- a, b, or c followed by 1 or 2
	 * 		[a-d[m-p]]		- a through d or m through p (union / Or)
	 * 		[a-z&&[def]]	- d, e or f once time for each only (Intersection / And) (a-z is ignored actually)
	 * 		[a-z&&[^bc]]	- A through z except b or c (Subtraction)
	 * 		[a-z&&[^m-p]]	- a through z except m through p (Subtraction)
	 * 		xx|yy			- xx or yy only
	*/
	
	/* Regex Quantifiers
	 * 		X?				- X occurs once or none at all, cannot exceed
	 * 		X+				- X occurs more than or equal to one time (X >= 1)
	 * 		X*				- X occurs zero or more times (X >= 0)
	 * 		X{n}			- X occurs exactly n times
	 * 		X{n,}			- X occurs n or more times only (X >= n)
	 * 		X{y,z}			- X occurs y or more times but less than or equal to z times (y <= X <= z)
	 */
	
	/* Regex Meta characters
	 * To use meta characters in Java, either:
	 * 	1. Precede the meta character with a backslash \
	 * 		.				- Represents any character (No need to be backslashed)
	 * 		d				- Represents any digits
	 * 		D				- Represents any non digit
	 * 		s 				- Represents any white space (space, tab or new line)
	 * 		S				- Represents any non white space character
	 * 		w				- Represents any word character (a-z, A-Z, 0-9, and _ )
	 * 		W				- Represents any non word character (any other non-word characters like whitespace, metacharacters...)
	 * 		b				- Represents a word boundary (Eg: Use regex \bHa in String "Ha HaHa HaHaHa" )
	 * 		B				- Represents a non word boundary
	 */
	
	/*			Greedy, Reluctant and Possessive Quantifiers
	 *  
	 *  -	GREEDY
	 *  -	Say, we want to match regex ".*foo" in the string xfooxxxxxfoo
	 *  -	What actualy this regex does is, firstly, the .* will match the entire string, only then proceed to find the 'f'
	 * 	 	However, since entire string is matched by .*, it has to backtrack to attempt to find the matching f.
	 * 	 	Therefore back 1 step, back 2 step and back 3 steps until xfooxxxxx foo which now matches the regex
	 * 	 	There will be only 1 matching result of "xfooxxxxxfoo"
	 * 	-	It's called greedy because at first it is greedily matching the entire string, only then if the next one doesn't match,
	 * 		it backtracks one by one to find the next matching element
	 * 	
	 * 	-	RELUCTANT
	 * 	-	To make a greedy regex to be reluctant, we add a relunctant quantifier ? behind the .* part
	 * 	-	Now we attempt regex ".*?foo" in string xfooxxxxxfoo
	 * 	- 	It will now first match "xfoo", followed by second result "xxxxxfoo"
	 * 	-	What it does is, instead of starting out swallowing the entire string, it instead start out matching nothing.
	 * 		Now it attempts to find the next matching 'f', but met with the beginning of string xfooxxxxxfoo, therefore
	 * 		only then the .*? backtracks to matching 1 character, x, which results in the foo now being matched correctly,
	 * 		returning "xfoo" as first result"
	 * 	-	Similarly in second result, it has to start out swallowing none, one, two, three, four and 5 characters until foo part
	 * 		being matched, returning "xxxxxfoo"
	 * 
	 * 	-	Possessive
	 * 	-	Possesive quantifiers do things a lot like greedy quantifier, but instead, it doesn't backtrack at all!
	 * 	-	Possesive quantifiers is made by using the + sign, therefore regex ".*+foo"
	 * 	-	Therefore the .* starts out matching the entire string, and never looked back. Since the next foo is not matched
	 * 		it will simply report the regex was not match
	 */
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.println("Enter your regex: ");
			Pattern pat = Pattern.compile(scan.nextLine() );
			
			System.out.println("Enter your string: ");
			String str = scan.nextLine();
			while (!str.equals("quit") ) {
				Matcher matcher = pat.matcher(str);
				System.out.println("------------- Direct Match Segment --------------------");
				System.out.println("\tYour String: " + str);
				System.out.println("\tYour Regex: " + pat.toString() );
				System.out.println("\tYour Result: " + matcher.matches() );
				System.out.println("------------- Regex Find Segment --------------------");
				while (matcher.find() ) {
					System.out.printf("Found \"%s\" at index %d to %d\n", matcher.group(), matcher.start(), matcher.end() );
				}
				
				System.out.println("To use new regex, type \"quit\", else, type your new string: ");
				str = scan.nextLine();
			}
		}
		
	}		//end of main()

}		//end of class
