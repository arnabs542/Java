package KMKTeam2;

import java.util.Scanner;
public class Q4I_PROGRESS_REPORT {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numStudent = scan.nextInt();
		
		
		//Each loop completes a case
		for (int caseNum = 1; numStudent != 0; caseNum ++ ) {
			
			System.out.println("Case #" + caseNum + ": ");
			
			//Each loop processes a student
			for (int i = 0; i < numStudent; i ++ ) {
				
				//Initialize all documents to be non-submitted
				String [] status = {"-", "-", "-", "-", "-", "-"};
				int[] submitted = { scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt() };
				
				//Each loop checks whether the individual document is submitted on time, late or not submitted
				for (int docNum = 1; docNum <= 6; docNum ++ ) {
					
					//If the submitted document is right on time
					if ( submitted[docNum - 1] == docNum)
						status[docNum - 1] = "R" + docNum;
					//Otherwise, if the digit is non zero (showing a document is submitted but late), change the respective document to *
					//submitted[docNum - 1] returns the document number submitted late. However, since the array numbering start from 0,1,2,3,4,5
					//Additional - 1 at behind is needed
					else if ( submitted[docNum - 1] != 0)
						status[ submitted[docNum - 1] - 1 ] = "*";
				}
				
				System.out.print("Student" + (i + 1) + " ");
				for (String individualStatus : status)
					System.out.print( individualStatus + " ");
				
				//Current student line printing is done. Opening a new line
				System.out.println();
			}		//end of individual student process loop
			
			numStudent = scan.nextInt();
			
		}		//end of case building loop
		

	}		//end of main()

}		//end of class
