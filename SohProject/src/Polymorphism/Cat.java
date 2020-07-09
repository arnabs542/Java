package Polymorphism;

public class Cat extends Animal{

	//Do remember that constructors are not inherited, and thus need to be defined again, preferably using super()
	Cat(String name) {
		super(name);
	}
	
	void sayIntro () {
									//Returns Cat
		System.out.println("The " + this.getClass().getSimpleName() + " " + name + " goes: Meow");
	}
	
}
