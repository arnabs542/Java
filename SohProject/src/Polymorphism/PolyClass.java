package Polymorphism;

public class PolyClass {

	public static void main(String[]args) {
		
		//Although the type is of Animal, it can contain object of its subclasses
		Animal[] animals = new Animal[3];
		
		Dog d1 = new Dog("Turbo");
		Cat c1 = new Cat("Chungus");
		Dog d2 = new Dog("Zander");
		
		animals[0] = d1;
		animals[1] = c1;
		animals[2] = d2;
		
		for (Animal a: animals) {
			a.sayIntro();
		}
		
	}
}
