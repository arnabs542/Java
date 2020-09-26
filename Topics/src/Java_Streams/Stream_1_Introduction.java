package Java_Streams;

/*
 * 	Java stream is a major feature added in Java 8. Java stream are MONADS, thus it is a crucial part to bringing
 * 	functional programming into Java. To use it, import java.util.stream
 * 
 * 	A MONAD is a structure that represent computations defined as sequence of steps. 
 * 
 * 	A stream represent a sequence of elements and support different kind of operations to perform computations upon those elements
 *	In Stream, there are various operations that we can perform on a stream, which is mainly divided into 3 types:
 *		>	Source - Initiates a stream.
 *					 Streams can be created from Collections, Lists, Sets, Arrays, lines of file, or even individual objects
 *
 *		>	Intermediate - Returns a stream after performing its operation. This way we can chain operations together
 *							Examples: anyMatch()
 *								 	  distinct()
 *									  filter()
 *									  findFirst()
 *									  flatmap()
 *									  map()
 *									  skip()
 *									  sorted()
 *
 *		>	Terminal - Returns void or non-stream result. Closes the stream
 *						Examples:	forEach()
 *									collect()
 *									count()
 *									toArray()
 *									max()
 *									min()
 *									reduce()
 *									summaryStatistics()
 *
 *	When operations are chained, we call that "OPERATION PIPELINE"
 *
 *	Operations must be non-interfering and stateless.
 *		>	Non iterferring - No modifying the underlying data structure/ source. It will just create a new stream out of the one
 *							  passed in.
 *		>	Stateless - Deterministic. No relying on outer mutable variable or states from outer scope which might change during
 *						the execution of the program
 *
 *	Advantages of using stream:
 *		>	More efficient, less lines of code to perform multiple operations on data
 *		>	Heavy use on lambda expressions, which is much shorter and concise.
 *			Examples include System.out::println and Character::isCharacter
 *		>	Parallel streams allows for multithreading which speeds up the operation even more
 *
 *
 *	**	Java has a method referencing operator (::) which when used, is similar to passing in that function as argument into 
 *		another function parameter. This is much more shorter way of using lambda expressions, given that the function passed 
 *		in takes the same amount of paramter as the lambda expression shall be
 *
 *		Eg:	System.out::println
 *					
 *				is equivalent to
 *			
 *			(x) -> System.out.println(x);
 */

public class Stream_1_Introduction {

}
