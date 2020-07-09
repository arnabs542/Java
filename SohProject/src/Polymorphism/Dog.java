package Polymorphism;

public class Dog extends Animal {

	//Do remember that constructors are not inherited, and thus need to be defined again, preferably using super()
	Dog(String name) {
		super(name);
	}

	void sayIntro () {
		System.out.println("The Dog " + name + " goes: Bark!");
	}
	
	
}
