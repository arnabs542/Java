package Java_Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Steams bring functional programming to Java
 * Advantages of Streams:
 * 		-More efficient, less lines of code to perform multiple operations on data
 * 		-Heavy use on lambda expressions, like System.out::println, Character::isCharacter etc
 * 		-ParallelStreams to multithreading
 * 
 * 	A stream pipeline consists of a source (of data), followed by zero or more intermediate
 * 	operations (process and manipulate data) and ONLY ONE terminal operation (How you want to return processed data)
 * 
 * 	Source:
 * 		-Streams can be created from Collectons, Lists, Sets, ints, longs, doubles, arrays, or even lines of a file
 * 
 * 	Intermediate Operations:
 * 		-For large datasets we should apply filter operation first to improve performance
 * 		-Also consider parallelstream for large datasets
 * 		Eg of operations:
 * 			-anyMatch()
 * 			-distinct()
 * 			-filter()
 * 			-findFirst()
 * 			-flatmap()
 * 			-map()
 * 			-skip()
 * 			-sorted()
 * 
 * 	Terminal Operations:
 * 		-Only one terminal operation is allowed for streams
 * 		Eg of operations:
 * 			-forEach() applies same function to each element, like println
 * 			-collect() saves the elements into a collection
 * 			-count()
 * 			-max()
 * 			-min()
 * 			-reduce()
 * 			-summaryStatistics()
 * 
 */

public class Streams {
	
	static void example1 () {
		IntStream
			.range(1, 50)		//Source: range of integers from 1 to 49, 50 is exclusive unless use rangeClosed()
			.forEach(System.out::println);	//Terminal: apply println to all of the integers
	}
	
	static void example2 () {
		IntStream
			.range(1, 50)	//Source: range of integers from 1 to 49
			.skip(25)		//Intermediate: Skip over first 25 iterations of the stream, returns the stream of remaining elements, therefore start from 26
			.forEach(x -> System.out.print(x + " ") );	//Terminal: apply print function to each element, expressed in lambda expression
	}
	
	static void example3 () {
		int sum = IntStream
					.range(1, 50)	//Source: range of integers from 1 to 49
					.sum();			//Terminal: add up all the elements into a sum. Therefore this whole stream function returns an integer which is the sum
		System.out.println(sum); 	//Since sum is stored in variable, we just print it out
									//Alternatively, we could just nest the whole stream in a System.out.println( ) arguments bracket
		/* System.out.println( IntStream...); */
	}
	
	static void example4 () {
		Stream.of("CBA", "DCB", "ZAA", "EEE", "GFD")	//Source: using Stream.of, we put in the data in the arguments list
			.sorted( (x,y) -> {							//Intermediate: Apply a customized comparator in the sort function which sort the strings based on the
				return x.charAt(1) - y.charAt(1);		// 				second character
			})
			.findFirst()								//Attempts to find the first element in the stream and returns a Optional object describing the element returned
			.ifPresent(System.out::println);			//Terminal: if the first element is found, apply println() method to the element
	}
	
	static void example5 () {
		String[] arr = {"e","h","i","a","b","c","d","j","f","g"};	//A mock char array consisting of characters from a to j
		Arrays.stream(arr)				//Source: using Arrays.stream to create a Stream object from the String array as source
			.filter(x -> x.compareTo("e") > 0 )	//Intermediate: Filter those whose character which is lexicographically less than or equal to character e
			.sorted()							//Intermediate: Sort the remaining elements lexicographically
			.forEach(System.out::println);		//Terminal: println
	}
	
	static void example6 () {
		String[] arr = {"Audrey", "Allen","Nathan","Nicholas","Noah","Sabri","Frank","Harold","Farhan"};	//A string array of first names
		Arrays.stream(arr)					//Source: using Arrays.stream to create a Stream object from the String array as source
			.filter(x -> x.startsWith("N") )	//Intermediate: filter the names to only consist of first names starting with 'N'
			.sorted()							//Intermediate: sort the filtered names lexicographically
			.forEach(System.out::println);		//Terminal: println
	}
	
	static void example7 () {
		IntStream.range(1, 10)		//Source: Integers in the range 1 to 9
			.filter(x -> (x % 2 == 0) )		//Intermediate: Filter out odd numbers, resulting in 2,4,6,8 only
			.map(x -> x * x)				//Intermediate: map those even numbers to their squares
			.average()						//Terminal: Obtain the averages of the elements. Returns an optional double object
			.ifPresent(System.out::println);
	}
	
	static void example8 () {
		String[] arr = {"Audrey", "Allen","Nathan","Nicholas","Noah","Sabri","Frank","Harold","Farhan"};
		List<String> list = Arrays.asList(arr);
		list.stream()			//Source: The List of Strings of first names
			.filter(x -> x.startsWith("A") )	//Intermediate: filter to retain only names that start with A
			.map(String::toUpperCase)			//Intermediate: map the filtered names to only have upper case letters
			.forEach(System.out::println);		//Terminal: println
	}
	
	static void example9 () throws IOException{
		Path path = Paths.get("src/Java_Streams/bands.txt");
		Stream<String> bands = Files.lines(path);	//Source: The list of band names from the text file specified with the path. Stream is created
													//		  using Files.lines
		bands							
			.filter(x -> x.length() > 13)			//Intermediate: filter out those names whose length is less than or equal to 13
			.sorted()								//Intermediate: sort the filtered names
			.forEach(System.out::println);			//Terminal: println
		bands.close();								//Close the stream to prevent memory leak
	}
	
	static void example10 () throws IOException {
		List<String> band = Files.lines(Paths.get("src/Java_Streams/bands.txt") )	//Source: Stream created from Files.lines using specified path
				.filter(x -> x.startsWith("A") )		//Intermediate: Filter out the names which doesn't start with 'A'
				.sorted()								//Intermediate: Sort the filtered names
				.collect(Collectors.toList() );			//Terminal: Collect and return the List which consists of the filtered band names, using Collectors API
		band.forEach(System.out::println);			//Print out each element in the List
	}
	
	static void example11 () throws IOException {
		long count = Files.lines(Paths.get("src/Java_Streams/data.csv") )	//Source: data.csv which "good lines" are those consists of 3 datas only separated by ','
			.map(x -> x.split(",") )		//Intermediate: For each line, map those lines by splitting them into String arrays based on the splitting comma ','
			.filter(x -> x.length == 3)		//Intermediate: Filter the bad lines which when split based on ',', doesn't have exactly 3 data
			.count();						//Terminal: Return the number of 'good lines', which is the number of elements in the stream
		System.out.println(count);
	}
	
	static void example12 () throws IOException {
		Files.lines(Paths.get("src/Java_Streams/data.csv") )	//Source: data.csv
			.map(x -> x.split(",") )		//Intermediate: For each line, map those lines by splitting them into String arrays with commas as seperator
			.filter(x -> x.length == 3)		//Intermediate: Filter those arrays whose length is not exactly 3, which is considered "bad lines"
			.filter(x -> Double.valueOf(x[2]) >= 2.50)		//Intermediate: Filter those arrays whose data is less than 2.50, so those greater/equal to 2.50 remains
			.sorted( (x,y) -> ( Double.valueOf(x[2]) - Double.valueOf(y[2]) > 0)? 1: -1 )	//Intermediate: Sort them based on the double value at array index 2
			.forEach(x -> System.out.printf("ID: %s, Name: %s, Score: %s\n", x[0], x[1], x[2]));	//Terminate: For each array, print them out as formatted
	}
	
	static void example13 () {
		double sumSqrt = Stream.of(4.5, 13.2, 19.5)	//Source: double values
				.reduce(0.0, (Double a, Double b) -> a + Math.sqrt(b) );	//Terminate: reduce the values in the array such that 
																			//The first argument is the initial value passed in, and next is the
																			//function that defines how the data was reduced. 
																	//Here initially a = 0.0 and b = 4.5. 4.5 is square rooted and they are sum together
																	//the result then became the new a and b = 13.2. Process continues until the end
		System.out.println(sumSqrt);		//Result should be equivalent to sqrt(4.5) + sqrt(13.2) + sqrt(19.5)
	}
	
	static void example14 () {
		List<Integer> li = Arrays.asList(1,4,6,8,4,2,4,7,9,1,3);
		IntSummaryStatistics iss = li.stream()		//Source: List of Integers
				.mapToInt(Integer::valueOf)			//Intermediate: map those integers into a IntStream Object so that we can use the terminal operation
				.summaryStatistics();		//Terminate: Returns a summaryStatistics object which carries statistics of the stream of integer just now
		
		System.out.println(iss);
	}
	
	
	public static void main(String[]args) throws IOException {
		example14();
	}
}
