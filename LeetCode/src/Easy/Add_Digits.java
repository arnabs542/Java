package Easy;

public class Add_Digits {

//	public int addDigits(int num) {
//        int res = num;
//        
//        while (res / 10 != 0) {    	
//        	int curr = res;
//        	res = 0;
//        	
//        	while (curr / 10 != 0 ) {
//        		res += curr % 10;
//        		curr /= 10;
//        	}
//        	res += curr;
//        }
//        
//        return res;
//    }
	
	public int addDigits(int num) {
		return (num == 0)? 0: (num - 1) % 9 + 1;
	}
	
}
