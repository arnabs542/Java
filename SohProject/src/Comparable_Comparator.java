import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Comparable_Comparator implements Comparator<Employee>{

	public static void main(String[] args) {
		
		Employee[] employee = {new Employee(1, "Alex", 30, 2500),
								new Employee(3, "John", 43, 4000),
								new Employee(5, "Ethan", 19, 1900),
								new Employee(2, "Rantha", 25, 3000),
								new Employee(4, "Thanos", 50, 6000) };
		
		ArrayList<Employee> empList = new ArrayList<Employee>( Arrays.asList(employee) );
		
		//If we use the default compareTo, which is compare by ID number
		Arrays.sort( employee);
		Collections.sort(empList);
		
		for (Employee e: employee) {
			System.out.println(e);
		}
		System.out.println(empList);
		
		//Else if we use the compare method defined in this class, which compares by Wages
		System.out.println("------------------------------------------------------------------------");
		Arrays.sort( employee, new Comparable_Comparator() );
		Collections.sort(empList, new Comparable_Comparator() );
		
		for (Employee e: employee) {
			System.out.println(e);
		}
		System.out.println(empList);

		
		//Using a generic method, sort and print out the array
		System.out.println("---------------------------------------------------------------------------");
		sortPrintArr( employee);
	}

	//Unlike the compareTo method, this one compares by wage, and 
	@Override
	public int compare(Employee emp1, Employee emp2) {
		if (emp1.getWage() == emp2.getWage() ) return 0;
		
		return (int)(emp1.getWage() - emp2.getWage() );
		
	}
	
	//Generic method of printing the array
	static <T extends Comparable<T> > void sortPrintArr( T[] arr) {
		Arrays.sort(arr);
		for (T a: arr) System.out.println(a);
	}

}
