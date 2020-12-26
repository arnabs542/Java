package Contest;

//https://leetcode.com/contest/biweekly-contest-42/problems/number-of-students-unable-to-eat-lunch/

public class Number_of_Students_Unable_to_Eat_Lunch {
	
	public int countStudents(int[] students, int[] sandwiches) {
        int s0 = 0;
        int s1 = 0;
        
        for (int s: students) {
        	if (s == 1) ++s0;
        	else ++s1;
        }
        
        for (int pt = 0; pt < sandwiches.length; ++pt) {
        	if (sandwiches[pt] == 0) {
        		if (s0 > 0) --s0;
        		else return students.length - pt;
        	} else {
        		if (s1 > 0) --s1;
        		else return students.length - pt;
        	}
        }
        return 0;
    }
}
