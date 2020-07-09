package Polymorphism;

//An abstract class: A class whose function is so general that the class is only meant to be inherited (extended) / create subclasses.
//Concrete class: Any non abstract class which is able to create objects of
public abstract class Animal {

	String name;
	
	//Abstract method: When a class extends this class, the method sayIntro() must be overridden in that said class
	//An abstract method does not specify a body, but instead end with semicolon instead ;
	abstract void sayIntro ();
	
	Animal (String name){
		this.name = name;
	}
	
	//Override: Overwriting the method that takes exactly the same form (same number and type of arguments, same return type)
	//			as in the superclass
	
	//Overloading: Writing 2 or more methods of the same identifier, but take different number or type of arguments, and may return
	//			   different type of data
	
}
