package Easy;

//https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
/*
 * 	First, this problem can be solved directly. In the worst case, every time it get divides by 2, 
 * 	it become odd, -1 will become even again. So time is O(2 Log N)
 * 
 * 	We can use bit manipulation here -
 * 		Check LSB for oddness
 * 		Right shift for divide by 2.
 */

public class Number_of_Steps_to_Reduce_A_Number_To_Zero {
	int numberOfSteps (int num) {
        int res = 0;
        while (num != 0) {
        	if (num % 2 == 0) num /= 2;
        	else num -= 1;
        	++res;
        }
        return res;
    }
	

	//	Quicker math, less iteration
	int numberOfSteps2(int num) {
		int res = 0;
		while (num != 0) {
			if (num % 2 != 0) ++res;
			++res;
			num /= 2;
		}
		return Math.max(0, res-1);
	}
	
	
	//	Bit Manip
	int numberOfSteps3(int num) {
		int res = 0;
		while (num != 0) {
			if ( (num & 1) != 0) ++res;
			++res;
			num >>= 1;
		}
		return Math.max(0, res-1);
	}
}
