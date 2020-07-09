package easy;

//https://www.hackerrank.com/challenges/fair-rations/problem?h_r=next-challenge&h_v=legacy

public class Fair_Rations {

	static int fairRations(int[] B) {

		//Make a model of the peoples in line as a boolean
		//True: The person has bread of even number
		//False: The person has bread of odd number
		
		//counterOdd counts the number of people having odd number breads initially
		int counterOdd = 0;
		
		boolean[] queue = new boolean[B.length];
		for (int i = 0; i < B.length; i ++ ) {
			queue[i] = B[i] % 2 == 0;
			counterOdd += !queue[i]? 1: 0;
		}
		
		//If odd number of people is having odd number loaf of bread initially, this is impossible!
		if (counterOdd % 2 != 0) {
			System.out.println("NO");
			return -1;
		}
		//Otherwise even number of ppl is having odd number loaf of bread
		else {
			
			//The number of bread needed
			int breadNeeded = 0;
			boolean inMiddle = false;
			int distance = 0;
			
			//Loop through the queue and start distribution process
			for (boolean thisPpl : queue) {
				
				//If a people with odd number bread is met
				if (!thisPpl) {
					//If another ppl with odd num bread is met before
					if (inMiddle) {
						breadNeeded += getBreadNeeded(distance);
						distance = 0;
						inMiddle = false;
					}
					//Else if this is the new set of people with odd num bread
					else {
						inMiddle = true;
					}
						
				}
				//Else if a people with even number is met, and we are in a middle of counting the distance between 2 odd bread people
				else if (inMiddle) {
					distance ++;
				}
			}
			
			return breadNeeded;
		}
    }
	
	//A little helper method to return the number of bread needed given distance between 2 odd number bread holder
	private static int getBreadNeeded(int dist) {
		return (dist + 1) * 2;
	}
	
	public static void main(String[]args) {
		System.out.println( fairRations(new int[] {1,2}) );
	}
}
