package Medium;


//https://leetcode.com/problems/complex-number-multiplication/
/*
 *  No complex algorithms here. Simply parse the string, extract out the real and imiginary part,
 *  do the math and output properly
 *  
 *  The regex is to determine the delimiter. Example in 1+-1i, we want to always treat the '+' and 'i' as delimiter.
 *  So what's left is 1 and -1
 */

public class Complex_Number_Multiplication {
	public String complexNumberMultiply(String num1, String num2) {
		String[] split1 = num1.split("\\+|i");
        String[] split2 = num2.split("\\+|i");
        int real1 = Integer.parseInt( split1[0] );
        int real2 = Integer.parseInt( split2[0] );
        int im1 = Integer.parseInt( split1[1] );
        int im2 = Integer.parseInt( split2[1] );
        int finalIm = real1 * im2 + real2 * im1;
        
        String res = (real1 * real2 - im1 * im2) + "+" + finalIm  + "i";
        return res;
    }
}
