package easy;

//https://www.hackerrank.com/challenges/cats-and-a-mouse/problem?h_r=profile

public class Cat_and_a_Mouse {

	static String catAndMouse(int x, int y, int z) {

		int catADist = Math.abs(x - z);
		int catBDist = Math.abs(y - z);
		
		if (catADist == catBDist) return "Mouse C";
		else {
			return (catADist > catBDist)? "Cat B": "Cat A";
		}

    }
	
}
