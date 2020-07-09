package LearningJava;

import java.util.EnumSet;

//All enum constants is a class that extends the class Enum in java.lang. Thus enum cannot extend any class, but can implement
enum Seasons {
										//The enum constants are always public, static and final. CANNOT BE CHANGED
	SPRING, SUMMER, FALL, WINTER;		//Default constructor is used, which actually does nothing
	
}		//end of enum Seasons

//----------------------------------------------------------------------------------------------------------------------------

enum SeasonsUpgrd {
	SPRING("Pink", "Cherry blossom is beautiful!"),				 //Think of these as a call to constructor, which create new objects
	SUMMER("Yellow", "Hot like in an oven!"),								//Here the constructor with 2 arguments are undefined by default
	FALL("Orange", "The leaves are falling and the trees are going bald!"),      //Therefore we have to create constructor ourselves
	WINTER("White", "Be careful not to become a popsicle!");
	
														//Just like an object can have data,
	private final String themeColour, description;		//Each of the constants will have its own themeColour and description String
	
	//CONSTRUCTOR(S) MUST BE DECLARED PRIVATE ONLY
	//The constructor that we've made so that each constant will have another 2 datas carried with them: themeColour and description
	//Take note that whenever an enum is created and we assign it to a specific constant, the constructor will still
	//run for all 4 possible seasons. You can test that by printing things in the constructor.
	private SeasonsUpgrd(String a, String b) {
		themeColour = a;
		description = b;
	}
	
	//To access those 2 data we have to make getter methods
	public String getThemeColour() {
		return themeColour;
	}
	
	public String getDesc() {
		return description;
	}
	
	public void overRide() {
	}
	
	//Notice that the default toString() can be overridden to return another String which suits the programmer taste
	@Override
	public String toString() {
		return "It's " + name() + ". The streets are becoming " + getThemeColour() + ". " + getDesc();
	}
}		//end of enum SeasonsUpgrd

//-------------------------------------------------------------------------------------------------------------------------------

enum SeasonsFinal {
	
	/*	With each of the enum constants, it can also override certain methods in the enum class to perform different task
	 */
	SPRING(){
		@Override
		void sayGreetings() { System.out.println("Wind breezes through the sakura tree");}
	},
	SUMMER(){
		@Override
		void sayGreetings() { System.out.println("Even the refrigerator is complaining about the heat"); }
	},
	FALL(){
		@Override
		void sayGreetings() { System.out.println("Frustrated trees are finding a way to slow down the balding process"); }
	},
	WINTER(){
		@Override
		void sayGreetings() { System.out.println("You can probably save money on buying ice cream"); }
	},
	DEFAULT;
	
	void sayGreetings() {
		System.out.println("I don't know the season yet!");
	}
}


//-----------------------------------------------------------------------------------------------------------------------------

public class Enumerations {

	public static void main(String[] args) {
		
		/*	To declare a simple enum instance, simply do like creating an new object, except that we don't use the 'new' keyword
		 *  Seasons sea1 = Seasons.SUMMER;	(Since the constructor is always private)
		 *  We can assume like the enum had created the object for us and ready to be taken
		 *  
		 *  By default, the toString() will return the enum constant's name, but we could also use sea1.name() to get name
		 *  the sea1.ordinal() returns the index of the constant in enum like arrays do, which SUMMER returns 1
		 * 
		 */
		
		//EnumSet is a class that can set the range of the enum we want to access
		for (SeasonsUpgrd a : EnumSet.range( SeasonsUpgrd.SUMMER, SeasonsUpgrd.FALL ) )
			System.out.println(a);
		
		//To test the Overriding function in the last enum
		SeasonsFinal.DEFAULT.sayGreetings();
		for (SeasonsFinal a : SeasonsFinal.values()) {
			a.sayGreetings();
		}
	}		//end of main

}		//end of class
