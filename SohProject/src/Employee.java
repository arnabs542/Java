
public class Employee implements Comparable<Employee> {

	private int id;
	private String name;
	private int age;
	private double wage;

	Employee(int id, String name, int age, double wage) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.wage = wage;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public double getWage() {
		return wage;
	}
	
	public boolean equals(Employee another) {
		return this.id == another.getID();
	}
	
	//This compareTo method does comparison with the id number of each employee, returns the positive int / negative int (distance between 2 ID)
	@Override
	public int compareTo(Employee another) {
		if (equals(another) ) {
			return 0;
		}
		else return id - another.getID();
		
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s | Age: %d | ID: %d | Wage: %.2f", name, age, id, wage);
	}

}
