package Medium;

//https://leetcode.com/problems/angle-between-hands-of-a-clock/

/*
 * 	A Math problem.
 * 	To solve the problem in one go is complex and tiresome. Let's separate it into different questions and later combine the answer to
 * 	both questions. (Divide and conquer?)
 * 
 * 	Problem #1: Assuming perfect clock (The clock where the hour pointer is not affected by minutes pointer), what is the angle between
 * 				hour pointer and minutes pointer?
 * 	Problem #2: Find out the offset angle caused by minutes pointer on the hour pointer
 * 
 * 	Fact:	> Each hour, the angle difference is 30 degree. Find out by 360 deg / 12 hr
 * 			> Each minute, the angle difference is 6 degree. Find out by 30 deg / 5 min
 * 
 * 	Problem 1:
 * 		We can convert the hour pointer to the relevant position in minutes pointer, which is (hr % 12hr * 5 min). Notice the modulo there,
 * 		if the hr is 12, it would reduce it to 0, therefore meaning 0 minutes.
 * 		Then just take the absolute difference between minutes and hour, and multiply it by the angle of each minutes, which is 6 degree
 * 		
 * 		| (hr % 12hr * 5min) | * 6 deg
 * 		Therefore we obtained the base angle (not include offset angle)
 * 
 * 	Problem 2:
 * 		To find out the offset caused by minutes, we find the ratio of that minutes vs full minute. That ratio is the offset angle moved
 * 		by hour pointer, in one hour angle range, which is 30 degree.
 * 		
 * 		Offset: (minutes / 60 minutes) * 30 degree
 * 
 * 		Now, if the minute representation of hour is greater than the minute itself, the angle difference would be greater, as the hour
 * 		pointer offset would cause them to be further apart. Vice versa, if the minute representation of hour is lesser, then the angle
 * 		difference would be lesser.
 * 		Therefore the offset would be added or subtracted from base angle by the above condition.
 * 
 * 		Lastly, we shall return the angle in acute/obtuse range. Therefore if calculated angle is greater than 180, take the complement part
 * 		of it (360 - angle) and return.
 */

public class Angle_Between_Hands_Of_A_Clock {

	public double angleClock(int hour, int minutes) {
		int hrMinPos = hour % 12 * 5;
		int baseAngle = Math.abs( (hrMinPos - minutes) * 6 );
		
		double offset = (minutes / 60.0) * 30;
		double angle = Math.abs( baseAngle + (hrMinPos > minutes? offset: -offset) );
		angle = (angle > 180)? 360-angle: angle;
		return angle;
	}
	
}
