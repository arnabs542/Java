package easy;

public class StrangeCounter {

//	https://www.hackerrank.com/challenges/strange-code/problem
	
	public static void main(String[]args) {
		System.out.println("Time\tInitVal\tCycleNo\tValue");
		for (long i = 1; i < 30; i ++ ) {
			System.out.printf("%d\t%d\t%d\t%d\n", i, getInitialValue( getCycleNo(i) ), getCycleNo(i), getValue(i) );
		}
	}
	
	//Pass in cycleNo which is the cycle that it is currently in, and will return the initial value of the cycle
	static long getInitialValue (long cycleNo) {
		return (long) (3 * Math.pow(2, cycleNo -1) );
	}
	
	//Pass in the time value, and will return the cycle number that the respective time will end in
	static long getCycleNo (long time) {
		for (int i = 1; true; i ++ ) {
			if (time - getInitialValue(i) <= 0)
				return i;
			time -= getInitialValue(i);
		}
	}
	
	static long getCumulativeTime (long cycleNo) {
		long time = 0;
		for (long i = 1; i <= cycleNo; i ++ ) {
			time = time + getInitialValue(i);
		}
		return time;
	}
	
	static long getValue (long time) {
		long timeLeft = time;
		//Find the time left over after minus all the previous cycle's time
		timeLeft -= getCumulativeTime( getCycleNo(time) - 1 );
		//Then get the initial value of the cycle, and minus the time left (displaced by 1) to get the value at that time
		return getInitialValue( getCycleNo(time) ) - (timeLeft - 1);
	}
	
	
}
