package Easy;

//https://leetcode.com/problems/can-place-flowers/

public class Can_Place_Flowers {

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (flowerbed.length <= 2) {
			return ( ( (flowerbed[0] == 1)? 0:1 ) >= n);
		}
		int index = 2;
		int sum = 0;
		if (flowerbed[0] == 0 && flowerbed[1] == 0) 
			sum ++;
		
		while (index < flowerbed.length - 2) {
			if (flowerbed[index] + flowerbed[index - 1] + flowerbed[index + 1] == 0) {
				sum ++;
				if (sum >= n) return true;
			}
			index +=2;
		}
		if (flowerbed[flowerbed.length - 1] + flowerbed[flowerbed.length - 2] == 0)
			sum++;
		
		return sum >= n;
    }
	
}
