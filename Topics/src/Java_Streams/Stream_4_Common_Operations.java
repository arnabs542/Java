package Java_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream_4_Common_Operations {
	
	
/*
 *	forEach() and peek()
 *
 *	forEach() is a terminal operation, which iterates over every elements, and apply a function to the elements
 *	which usually we passed in as lambda expression
 *
 *	peek() are like forEach(), but it is an intermediate operation, therefore we can easily chain operations
 *	after peek(). Chaining operations after forEach() is an error because stream will be closed at that time!
 */
static void S4_Example1() {
	IntStream.range(1, 10)
		.filter(x -> x % 2 == 0)
		.forEach( System.out::println ); 
	
	System.out.println();
	
	double avg = IntStream.range(1, 10)
		.filter(x -> x % 2 != 0)
		.peek( System.out::println )		//Since we want to perform stuff after this, use peek()
		.average()
		.orElse( -1 );			// orElse will return the value if present, otherwise return the value specified
	System.out.println("Average of odd numbers from 1 to 9: " + avg);
}


/*
 *	map()
 *
 * 	map() produces a new stream after applying a function to each element in the original stream. The new produced
 * 	stream can be of different type.
 * 	However, if we map from primitive types to object, or from object to primitive types, we shall use specialized
 * 	map function
 * 
 * 	>	mapToInt()
 * 	>	mapToDouble()
 * 	>	mapToLong()
 * 	>	mapToObj()
 */
static void S4_Example2() {
	String[] str = { "1", "2", "3" };
	int[] arr = { 10, 20, 30 };
	
	//Example from str to int array
	int[] res1 = Arrays.stream( str )
			.mapToInt( Integer::parseInt )
			.toArray();
	
	//Example from int to str array
	String[] res2 = IntStream.of( arr )
			.mapToObj( String::valueOf )
			.map( s -> s + " Apples" )
			.toArray( String[]::new );			//	in the toArray function, pass in the generator, which is the new
												//	function in String[] to allocate the string array, and store it

	System.out.println( Arrays.toString(res1) );
	System.out.println( Arrays.toString(res2) );
}





/*
 * 	collect()
 * 
 * 	We have explored how to convert stream back into arrays. How about Collections? We have to use collect()
 * 	function for that, passing in a Collector object
 * 	In depth, Collector will have 4 operations defined:
 * 		>	supplier
 * 		>	accumulator
 * 		>	combiner
 * 		>	finisher
 * 	However, most of the Collectors we use shall have those abstracted from us, unless we want to write our 
 * 	custom Collector
 */
static void S4_Example3() {
	Supplier< Stream<Integer> > streamsupplier = () -> {		//Use of Supplier to avoid having to repeat
																//source operation.
		return Stream.of(23,51,64,93,33);
	};
	
	List<Integer> li = streamsupplier.get()		
				.collect( Collectors.toList() );		//Stream to List
	
	Set<Integer> set = streamsupplier.get()
			.collect( Collectors.toSet() );
	
	System.out.println(li);
	System.out.println(set);
}
	


/*
 * 	collect() to Map (MORE ADVANCED)
 * 
 * 	To collect a stream into a map is a little more complicated.
 * 	
 * 	Collectors.groupingBy( lambda ) - If you want to have element of stream as value of the Map. Very similar
 * 								      to SQL's GROUP BY function.
 * 									  The map will be key -> List of values
 * 									  The lambda function passed in will return the key of the Map to be mapped to the respective element
 * 
 * 	Collectors.toMap( keyLambda, valueLambda, collisionResolver )
 * 			THe keyLambda and valueLambda is both lambda functions, returning the key and value to be mapped
 * 			collisionResolver is an optional lambda, specifying what action to perform when 2 values collision
 * 			(have the same key). This function takes in 2 VALUES that collided, which has same key
 */
static void S4_Example4() {
	class Person {
		String name;
		int age;
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
	
	Person[] person = {
		new Person("Matthew", 32),
		new Person("Edward", 20),
		new Person("Pierce", 32),
		new Person("Michael", 50)
	};
	
	
	//	Person as value in a list, age as key
	Map<String, List<Person> > map1 = Stream.of( person )
				.collect( Collectors.groupingBy(p -> p.name ) );
	
	//	Person name as value, age as key
	Map<Integer, String> map2 = Stream.of( person )
				.collect( Collectors.toMap(
						p -> p.age, 
						p -> p.name,
						(col1, col2) -> col1 + " & " + col2 ) );	//Matthew and Pierce shall collide. Their name
																	// will be appended with a &
	
	System.out.println( map1 );
	System.out.println( map2 );
}
	
	
public static void main(String[]args) {
	S4_Example4();
}

}
