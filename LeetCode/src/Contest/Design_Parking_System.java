package Contest;

public class Design_Parking_System {

	class ParkingSystem {
		
		int[] lots;

	    public ParkingSystem(int big, int medium, int small) {
	        lots = new int[4];
	        lots[1] = big;
	        lots[2] = medium;
	        lots[3] = small;
	    }
	    
	    public boolean addCar(int carType) {
	        if (lots[carType] <= 0) return false;
	        lots[carType] --;
	        return true;
	    }
	}
	
	
}
