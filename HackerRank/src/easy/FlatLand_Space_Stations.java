package easy;

//https://www.hackerrank.com/challenges/flatland-space-stations/problem

public class FlatLand_Space_Stations {

	static int flatlandSpaceStations(int n, int[] c) {
		
		//Simulates the flat land where city with space station is true
		boolean[] hasSpaceS = new boolean[n];
		int firstCity = c[0], lastCity = c[0];
		for (int i: c) {
			hasSpaceS[i] = true;
			firstCity = (firstCity > i)? i: firstCity;
			lastCity = (lastCity < i)? i: lastCity;
		}
		
		//farCToC is the most farthest spaceStation to spaceStation distance. From this we can calculate the farthest middle city to city value
		int farCToC = 0;
		//The distCount is a counter for distance between cities, it remains -1 until the first city with space station is met, then it begins
		//counting
		int distCount = -1;
		for (int i = 0; i < n; i ++ ) {
			//If first city is met, start counting from now on
			if (hasSpaceS[i] && distCount == -1) {
				distCount = 0;
			}
			//Another city is met, compare this city to city distance with previous one
			else if (hasSpaceS[i] ) {
				distCount++;
				farCToC = (distCount > farCToC)? distCount: farCToC;
				distCount = 0;
			}
			//This city has no space station. Continue counting 
			else if (distCount != -1)
				distCount++;
		}
		
		//The midpoint between the most farthest spaceStation to spaceStation distance, rounded down is the intercity maximum distance
		farCToC /= 2;
		
		//Check if the city[0] to closest space station is farther, or if the last city to closest space station is farther, or intercity distance
		return Math.max( Math.max(firstCity, (n - lastCity) -1 ), farCToC);
		
	}
	
	public static void main(String[]args) {
		System.out.println( flatlandSpaceStations(10, new int[]{6,8} ) );
		System.out.println( flatlandSpaceStations(10, new int[]{3,6,8} ) );
		System.out.println( flatlandSpaceStations(10, new int[] {1,8} ) );
		System.out.println( flatlandSpaceStations(10, new int[]{8} ) );
		System.out.println( flatlandSpaceStations(4, new int[]{0,1,2,3} ) );
		
	}
	
}
